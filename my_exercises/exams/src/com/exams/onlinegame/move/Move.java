package com.exams.onlinegame.move;

import java.io.Serializable;

public class Move implements Serializable {

    private static final long serialVersionUID = 1;

    private int x1;
    private int x2;
    private String str;

    public Move(int x1, int x2, String str){
        this.x1 = x1;
        this.x2 = x2;
        this.str = str;
    }

    public String getStr(){return this.str;}

    public String toString(){
        return "{"+this.x1+","+this.x2+","+this.str+"}";
    }

}