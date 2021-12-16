package com.socketAdvanced.es1.operations;

public class OperationRequest {

    private static final long serialVersionUID = 1;

    private int CCnumber;
    private int amount;
    private String request;

    public OperationRequest(int CCnumber, int val, String what){
        this.CCnumber = CCnumber;
        this.amount = val;
        this.request = what;
    }

    public int getCCnumber(){return CCnumber;}
    public int getAmount(){return amount;}
    public String getRequest(){return request;}


}
