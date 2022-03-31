package com.socketAdvanced.es3.message;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1l;

    private String msg;
    private String sender;

    public Message(String msg, String sender){
        this.msg = msg;
        this.sender = sender;
    }

    public String toString(){return this.sender + " :"+this.msg;}

}
