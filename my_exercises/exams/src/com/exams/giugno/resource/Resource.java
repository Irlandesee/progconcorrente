package com.exams.giugno.resource;

import java.io.Serializable;
import java.sql.Timestamp;

public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Timestamp creationTimeStamp;
    private String str;
    private Object obj;

    public Resource(String str, Object obj){
        this.str = str;
        this.obj = obj;
        this.creationTimeStamp = new Timestamp(System.currentTimeMillis());
    }

    public String getStr(){return this.str;}
    public Object getObj(){return this.obj;}
    public Timestamp getCreationTimeStamp(){return this.creationTimeStamp;}

    public String toString(){
        return this.creationTimeStamp.toString() + ", " + this.str;
    }

}
