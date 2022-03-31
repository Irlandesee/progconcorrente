package com.socketAdvanced.es1.bank;

import com.socketAdvanced.es1.operations.OperationRequest;
import com.socketAdvanced.es1.operations.Result;
import com.socketAdvanced.es1.BankInterface.BankInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Bank {

    private int[] ccAmounts = {0,0,0};
    private LinkedList<Thread> slaves;
    private int serverPort = BankInterface.PORT;

    public static void main(String[] args){
        ServerSocket ss = null;
        Socket sock = null;

        Bank bank = new Bank();
        bank.slaves = new LinkedList<Thread>();
        int i = 0;
        try{
            ss = new ServerSocket(bank.serverPort);
            while((sock = ss.accept()) != null){
                System.err.println("Nuova connessione in entrata");
                Thread slave = new SkeletonSlave(i, sock, bank);
                System.err.println("Nuovo slave creato"+ i);
                bank.addSlave(slave);
                i++;
            }
        }catch(IOException io){
            io.printStackTrace();
        }finally{
            try{
                ss.close();
            }catch(IOException ioe){ioe.printStackTrace();}
        }
    }

    public Result executeOperation(OperationRequest op){
        int ccNum = op.getCCnumber();
        String opType = op.getRequest();
        if(ccNum < 0 || ccNum > 2)
            return new Result(-1, 0, opType, false);
        if(opType.equalsIgnoreCase("Deposit")){
            ccAmounts[ccNum] += op.getAmount();
            return new Result(ccNum, ccAmounts[ccNum], opType, true);
        }else if(opType.equalsIgnoreCase("Withdraw")){
            if(op.getAmount() > ccAmounts[ccNum]){
                return new Result(ccNum, 0, opType, false);
            }else{
                ccAmounts[ccNum] -= op.getAmount();
                return new Result(ccNum, ccAmounts[ccNum], opType, true);
            }
        }else
            return new Result(ccNum, 0, "unknown", false);
    }

    public List<Thread> getSlaves(){
        return this.slaves;
    }

    public void addSlave(Thread slave){
        this.slaves.add(slave);
    }

}
