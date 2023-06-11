package com.melnyk.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Command {

    private CommandType commandType;
    private String commandText;
    private boolean attemptsNumberExhausted;
    private boolean timeToSend;
    private int sendCounter;
    private Date sendDate;

    public void incSendCounter(){
        sendCounter++;
    }
}
