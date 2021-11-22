package com.matrix;

import java.util.Random;

public class Matrix {

    private int rows;
    private int columns;
    private int[][] matrix;

    private Random randGen;

    private int rowIndex; //indice per sapere che riga restituire
    private int columnIndex; //indice per sapere che colonna restituire

    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;

        matrix = new int[rows][columns];
        randGen = new Random();
        initMatrix();

        rowIndex = 0;
        columnIndex = 0;
    }

    private int nextInt(){
        return randGen.nextInt(100);
    }

    private void initMatrix(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = this.nextInt();
            }
        }
    }

    public void printMatrix(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++) {
                if(j == columns - 1)
                    System.out.printf("[%d]\n", matrix[i][j]);
                else
                    System.out.printf("[%d]", matrix[i][j]);
            }
        }
    }

    public synchronized int[] getNextRow(){
        int[] ris = matrix[rowIndex];
        rowIndex++;
        if(rowIndex == rows)
            rowIndex = 0;
        return ris;
    }

    public synchronized int[] getNextColumn(){
        int[] ris = new int[columns];
        for(int i = 0; i < rows; i++)
            ris[i] = matrix[i][columnIndex];
        columnIndex++;
        if(columnIndex == columns)
            columnIndex = 0;
        return ris;
    }

}
