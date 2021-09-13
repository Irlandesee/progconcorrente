package client;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import serverinterface.ServerInterface_close;
import tavola.Tavola;

public class ProxyClient extends Thread implements ServerInterface_close{

    private static int proxyID = 0;
    private Socket s;
    private Tavola t;
    private char segnapostoAssegnato;

    private Scanner sc;
    private ObjectInput inputStreamObject;
    private ObjectOutput outputStreamObject;
    private BufferedReader inStream;
    private PrintWriter outStream;

    private final String NEW_GAME = "NEW_GAME";
    private final String QUIT_GAME = "QUIT_GAME";
    private final String WAIT = "WAIT";
    private final String NEXT_MOVE = "NEXT_MOVE";

    private boolean runCondition;

    public ProxyClient(Tavola _t, Socket _s, char _segnapostoAssegnato){
        t = _t;
        s = _s;
        segnapostoAssegnato = _segnapostoAssegnato;
        runCondition = true;
    }

    private void init(){
        System.out.printf("%s: inizializzazione degli stream IO", this.getName());
        try{
            inputStreamObject = new ObjectInputStream(s.getInputStream());
            outputStreamObject = new ObjectOutputStream(s.getOutputStream());
            inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
            sc = new Scanner(System.in);
        }catch(IOException e){
            System.out.printf("%s: Errore nella creazione stream IO");
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void printHelp(){
        System.out.println("1. Connettiti a un server locale e inizia un nuovo gioco.");
        System.out.println("2. Connettiti a un server locale. Attenzione! \n un altro client deve aver gia' avviato un gioco!");
        System.out.println("3. Inserisci coordinate prossima mossa.");
        System.out.println("4. Disconnessione dal server.");
        System.out.println("5. Disconnessione dal server e uscita dall applicazione");
    }

    private String leggiComandoClient(){
        String comandoClient;
        if((comandoClient = sc.nextLine()) != null)
            return comandoClient;
        return "";
    }

    private int[] leggiCoordinate(){
        String coordinate;
        int[] xy = new int[2];
        System.out.printf("%s: Inserire coordinate. esempio: 1 2\n", this.getName());
        if((coordinate = sc.nextLine()) != null){
            String[] temp = coordinate.split(" ");
            xy[0] = Integer.parseInt(temp[0]);
            xy[1] = Integer.parseInt(temp[1]);
            return xy;
        }
        System.out.printf("%s: inserire formato coordinate corretto!\n", this.getName());
        return leggiCoordinate();
    }


    public char getSegnaposto(){
        return this.segnapostoAssegnato;
    }

    public void setSegnaposto(char _segnaposto){
        segnapostoAssegnato = _segnaposto;
    }

    public boolean getRuncondition(){
        return this.runCondition;
    }

    public void setRuncondition(boolean _runcondition){
        this.runCondition = _runcondition;
    }

    public void run(){
        while(getRuncondition()){
            //controlla input utente
            String cmd = leggiComandoClient();
            int comando = -1;
            if(!(cmd.isBlank())){
                try{
                    comando = Integer.parseInt(cmd);
                }catch(NumberFormatException ne){
                    System.out.printf("%s Number format exception\n", this.getName());
                    ne.printStackTrace();
                }
                switch(comando){
                    case 1:
                        System.out.printf("%s: connessione al server e creazione nuovo gioco\n", this.getName());
                        init();
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.printf("%s: lettura coordinate per la prossima mossa\n", this.getName());
                        int[] coordinate = leggiCoordinate();
                        //check coordinate
                        //if(!((coordinate[0] < -1 && coordinate[0] >= 3) && (coordinate[1] < -1 && coordinate[1] >= 3)))

                        break;
                    case 4:
                        System.out.printf("%s: Disconnessione in corso dal server\n", this.getName());
                        break;
                    case 5:
                        System.out.printf("%s: uscita in corso\n", this.getName());
                        this.close();
                        setRuncondition(false);
                    default:
                        System.out.println("Comando non valido: 1-2-3-4-5");
                        printHelp();
                        break;
                }
            }


        }
    }

    public void reset(){

    }

    public boolean set(int r, int c, char s){

    }

    public void show(){

    }

    public Winner cellOwner(char x){

    }

    public Winner whoWins(){

    }

    public void close(){
        System.out.println("Chiusura stream IO!");
        try{
            inputStreamObject.close();
            outputStreamObject.close();
            inStream.close();
            outStream.close();
        }catch(IOException e1){
            System.out.println("Errore nella chiusura degli stream IO");
            e1.printStackTrace();
        }
    }

}
