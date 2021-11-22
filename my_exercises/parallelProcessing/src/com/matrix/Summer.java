package com.matrix;

public class Summer extends Thread{

    private static int summerID = 0;

    private int[] row;
    private int[] column;

    private Matrix m;

    public Summer(Matrix m){
        summerID++;
        this.m = m;
    }

    public void run(){
        row = m.getNextRow();
        int ris = 0;
        for(int i = 0; i < row.length; i++)
            ris += row[i];

        System.out.printf("Summer %d calcola %d\n", Summer.summerID, ris);

    }

}
