package com.chat.message;

import java.io.Serializable;

public class Message implements Serializable{

    private static final long serialVersionUID = 1;

    private String msg;

    public Message(String _msg){
        msg = _msg;
    }

    public String getMessage(){
        return msg;
    }

    public void setMessage(String _msg){
        msg = _msg;
    }


}
