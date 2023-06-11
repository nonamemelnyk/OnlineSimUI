package com.melnyk.chat;

import java.net.InetSocketAddress;
import java.util.Date;

public class CommandExecutor {
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

    public void deleteCommand(Command command) {
        deleteCommand(command.getCommandType());
    }

    public void deleteCommand(CommandType commandType) {

    }

    public void sendCommandToContext(ChannelHandlerContext channelHandlerContext,
                                     InetSocketAddress inetSocketAddress, String text) {

    }
}

