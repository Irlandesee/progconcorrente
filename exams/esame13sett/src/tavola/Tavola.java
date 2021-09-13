package tavola;

import serverinterface.ServerInterface;
import java.io.Serializable;

public class Tavola implements ServerInterface, Serializable{

    private static final long serialVersionUID = 1;

    private char celle[][]=
        {
                { ' ', ' ', ' ' } ,
                { ' ', ' ', ' ' } ,
                { ' ', ' ', ' ' } } ;

    public Tavola(){}

    public synchronized void reset() {
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                celle[i][j]=' ';
    }

    public synchronized boolean set(int r, int c, char s) {
        if(celle[r][c]==' ') {
            celle[r][c]=s;
            return true;
        } else
            return false;
    }

    public synchronized void show() {
        System.out.println("Situation:");
//        System.out.println("-----");
        for(int r=0; r<3; r++) {
            System.out.print("|");
            for(int c=0; c<3; c++) {
                System.out.print(celle[r][c]);
                System.out.print("|");
            }
            System.out.println();
//            System.out.println("-----");
        }
    }

    public synchronized Winner cellOwner(char x) {
        if(x=='x')
            return Winner.PLAYER1;
        else
            return Winner.PLAYER2;
    }

    public synchronized Winner whoWins() {
        if(celle[0][0]!=' ') {
            if((celle[0][0]==celle[0][1] && celle[0][0]==celle[0][2]) ||
               (celle[0][0]==celle[1][1] && celle[1][1]==celle[2][2]) ||
               (celle[0][0]==celle[1][0] && celle[1][0]==celle[2][0]))
                return cellOwner(celle[0][0]);
        }
        if(celle[1][1]!=' ') {
            if((celle[1][1]==celle[0][1] && celle[1][1]==celle[2][1]) ||
               (celle[1][1]==celle[0][2] && celle[1][1]==celle[2][0]) ||
               (celle[1][1]==celle[1][0] && celle[1][1]==celle[1][2]))
                return cellOwner(celle[1][1]);
        }
        if(celle[2][2]!=' ') {
            if((celle[2][2]==celle[0][2] && celle[2][2]==celle[1][2]) ||
               (celle[2][2]==celle[2][1] && celle[2][2]==celle[2][0]))
                return cellOwner(celle[2][2]);
        }

        for(int r=0; r<3; r++) {
            for(int c=0; c<3; c++) {
                if(celle[r][c]==' ')
                    return Winner.NONE;
            }
        }
        return Winner.EVEN;
    }

    public synchronized char[][] getCelle(){
        return this.celle;
    }

    public synchronized void setCelle(char[][] _celle){
        celle = _celle;
    }
}