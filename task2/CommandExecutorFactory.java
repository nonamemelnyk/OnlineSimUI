package com.melnyk.chat;

import java.net.InetSocketAddress;

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
