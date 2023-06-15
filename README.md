## Task1


## Run Locally

Clone the project

```bash
  git clone https://github.com/nonamemelnyk/simui.git
```

Go to the project directory

```bash
  cd simui/task1/
```

Start app

```bash
  java -jar OnlineSimUI.jar  
```



## Task2

Some samples at task2 dir
## Code


```
  void process(ChannelHandlerContext channelHandlerContext) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(getIpAddress(), getUdpPort());

        for (Command it : getAllCommands()) {
            boolean isSend = it.getCommandType() == CommandType.REBOOT_CHANNEL
                    ? !it.isAttemptsNumberExhausted()
                    : !currentCommand.isAttemptsNumberExhausted();
            var commandExecutor = CommandExecutorFactory.getInstance().getByCommandType(it.getCommandType());
            commandExecutor.sendCommandToContextOrDelete(channelHandlerContext, inetSocketAddress, it, isSend);
        }

        sendKeepAliveOkAndFlush(channelHandlerContext);
    }
```


CommandExecutorFactory.java 

```
  public class CommandExecutorFactory {
    private final static CommandExecutorFactory instance = new CommandExecutorFactory();

    private CommandExecutorFactory() {
    }

    private final CommandExecutor rebootCommandExecutor = new CommandExecutor();
    private final CommandExecutor defaultCommandExecutor = new CommandExecutor() {
        @Override
        public void sendCommandToContext(ChannelHandlerContext channelHandlerContext, InetSocketAddress inetSocketAddress, Command command) {
            if (command.isTimeToSend()) {
                super.sendCommandToContext(channelHandlerContext, inetSocketAddress, command);
            }
        }
    };

    public CommandExecutor getByCommandType(CommandType commandType) {
        switch (commandType) {
            case REBOOT_CHANNEL:
                return rebootCommandExecutor;
            default:
                return defaultCommandExecutor;
        }
    }

    public static CommandExecutorFactory getInstance() {
        return instance;
    }

}
```

CommandExecutor.java

```
    public void sendCommandToContextOrDelete(ChannelHandlerContext channelHandlerContext,
                                             InetSocketAddress inetSocketAddress, Command command,
                                             boolean isSend) {
        if (isSend) {
            sendCommandToContext(channelHandlerContext, inetSocketAddress, command);
        } else {
            deleteCommand(command.getCommandType());
        }
    }

    public void sendCommandToContext(ChannelHandlerContext channelHandlerContext,
                                     InetSocketAddress inetSocketAddress, Command command) {
        sendCommandToContext(channelHandlerContext, inetSocketAddress, command.getCommandText());

        try {
            AdminController.getInstance()
                    .processUssdMessage(new DblIncomeUssdMessage(
                            inetSocketAddress,
                            EnumGoip.getByModel(getGoipModel()),
                            command.getCommandText()
                    ), false);
        } catch (Exception ignored) {
        }
        logger.write("send message : {}", command.getCommandText());
        command.setSendDate(new Date());
        command.incSendCounter();
    }
```
