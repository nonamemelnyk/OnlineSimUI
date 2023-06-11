package com.melnyk.chat;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public abstract class Logic {

    private Command currentCommand;

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

    public abstract Iterable<Command> getAllCommands();
    public abstract void sendKeepAliveOkAndFlush(ChannelHandlerContext context);
    public abstract String getIpAddress();
    public abstract int getUdpPort();
}
