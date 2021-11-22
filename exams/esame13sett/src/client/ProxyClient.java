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
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import tavolainterface.TavolaInterface_close;
import tavola.Tavola;

public class ProxyClient implements TavolaInterface_close {

    private static int proxyID = 0;
    private Socket s;
    private char segnaposto;
    private boolean turn;

    private ObjectInput inputStreamObject;
    private ObjectOutput outputStreamObject;
    private BufferedReader inStream;
    private PrintWriter outStream;

    private final String NEW_GAME = "NEWGAME";

    public ProxyClient(InetAddress address, char _segnaposto){
        segnaposto = _segnaposto;
        try{
            s = new Socket(address, serverPort);
            inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
            inputStreamObject = new ObjectInputStream(s.getInputStream());
            outputStreamObject = new ObjectOutputStream(s.getOutputStream());
        }catch(IOException e){
            System.out.println("Errore nella creazione degli Stream!.");
            e.printStackTrace();
        }

    }

    public void nuovoGioco(){
        System.out.println(NEW_GAME);
        outStream.println(NEW_GAME);
        //aspetto risposta del server
        String risposta = "";
        try{
            risposta = inStream.readLine();
            System.out.println(risposta);
            if(risposta.equals("OK")){
                System.out.println("Gioco creato correttamente");
                setTurno(false);
            }
        }catch(IOException e){
            System.out.println("Eccezione io mentre leggevo risposta!");
            e.printStackTrace();
        }
        System.out.println("Esiste gia un gioco in corso!");
        setTurno(true);
    }

    public void reset(){
        outStream.println("RESET");
    }

    public void connect(){
        outStream.println("CONNECT");
        //attendo risposta
        String risposta = "";
        try{
            risposta = inStream.readLine();
            if(risposta.equals("OK")){
                System.out.println("Gioco joinato correttamente.");
                setTurno(true);
            }
            else{
                System.out.println("nessun gioco in corso!");
            }
        }catch(IOException e){
            System.out.println("Eccezione io mentre leggevo risposta!");
            e.printStackTrace();
        }
    }

    public Tavola getTavola(){
        outStream.println("GET_TAVOLA");
        //attendo risposta
        String risposta;
        Tavola t;
        try{
            if((risposta = inStream.readLine()).equals("OK")){
                t = (Tavola) inputStreamObject.readObject();
            }
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Eccezione IO  mentre richiedevo tavola");
            e.printStackTrace();
        }
        return null;
    }

    public boolean set(int r, int c, char s){
        outStream.println("NEXT_MOVE");
        outStream.println(r);
        outStream.println(c);
        outStream.println(s);
        //attendo risposta
        String risposta;
        try{
            risposta = inStream.readLine();
        }catch(IOException e){
            return false;
        }
        setTurno(false);
        return (risposta.equals("MOVE_OK"));
    }

    public Winner whoWins(){
        outStream.println("WHO_WINS");
        //attendo risposta
        String risposta = "";
        try{
            risposta = inStream.readLine();
        }catch(IOException e){
            System.out.println("Errore IO nel determinare vincitore");
            e.printStackTrace();
        }
        if(risposta.equals("PLAYER1"))
            return Winner.PLAYER1;
        else if(risposta.equals("PLAYER2"))
            return Winner.PLAYER2;
        else if(risposta.equals("NONE"))
            return Winner.NONE;
        return Winner.EVEN;
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

    public void quit(){
        outStream.println("QUIT");
    }

    public char getSegnaposto(){
        return this.segnaposto;
    }

    public void setSegnaposto(char _segnaposto){
        segnaposto = _segnaposto;
    }

    public synchronized boolean getTurno(){
        return this.turn;
    }

    public synchronized void setTurno(boolean _mioTurno){
        turn = _mioTurno;
    }

}
