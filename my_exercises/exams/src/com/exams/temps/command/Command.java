package com.exams.temps.command;

import java.io.Serializable;

public class Command implements Serializable {

    private static final long serialVersionUID = 1;

    private String command;
    private String room;
    private String clientType;

    public Command(String command, String room, String clientType){
        this.command = command;
        this.room = room;
        this.clientType = clientType;
    }

    public String getCommand(){return this.command;}
    public String getRoom(){return this.room;}
    public String getClientType(){return this.clientType;}

    public String toString(){
        return "cType: " + this.clientType +", "+ this.command + " : " + this.room;
    }

}
