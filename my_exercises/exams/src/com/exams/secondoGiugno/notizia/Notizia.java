package com.exams.secondoGiugno.notizia;

import java.io.Serializable;

public class Notizia implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tipoNotizia;
    private String notizia;

    public Notizia(String tipoNotizia, String notizia){
        this.tipoNotizia = tipoNotizia;
        this.notizia = notizia;
    }

    public String getTipo(){return this.tipoNotizia;}
    public String getNotizia(){return this.notizia;}

    public String toString(){
        return this.tipoNotizia + " : " + this.notizia;
    }

}
