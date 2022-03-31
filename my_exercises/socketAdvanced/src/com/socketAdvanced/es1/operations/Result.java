package com.socketAdvanced.es1.operations;

import java.io.Serializable;

public class Result implements Serializable {

    private static final long serialVersionUID = 1l;

    private int CCnumber;
    private int amount;
    private String opType;
    private boolean successful;
    private boolean completed;

    public Result(int cc, int val, String ot, boolean ok){
        CCnumber = cc;
        amount = val;
        opType = ot;
        successful = ok;
    }

    public int getCCnumber(){return CCnumber;}
    public int getAmount(){return amount;}
    public boolean isSuccessful(){return successful;}

    public synchronized void setComplete(Result r){
        this.CCnumber = r.getCCnumber();
        this.amount = r.getAmount();
        this.opType = r.getType();
        this.successful = r.isSuccessful();
        completed = true;
    }

    public String getType(){return opType;}
    public String toString() {
        return "op. "+opType+"on CC num. "+ CCnumber+ (successful?"OK":"KO")+" resulting amount: "+amount;
    }

}
