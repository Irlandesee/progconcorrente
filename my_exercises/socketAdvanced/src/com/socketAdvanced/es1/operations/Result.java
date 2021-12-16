package com.socketAdvanced.es1.operations;

public class Result {

    private static final long serialVersionUID = 1;

    private int CCnumber;
    private int amount;
    private String opType;
    private boolean successful;

    public Result(int cc, int val, String ot, boolean ok){
        CCnumber = cc;
        amount = val;
        opType = ot;
        successful = ok;
    }

    public int getCCnumber(){return CCnumber;}
    public int getAmount(){return amount;}
    public boolean isSuccessful(){return successful;}
    public String getType(){return opType;}
    public String toString() {
        return "op. "+opType+"on CC num. "+ CCnumber+ (successful?"OK":"KO")+" resulting amount: "+amount;
    }

}
