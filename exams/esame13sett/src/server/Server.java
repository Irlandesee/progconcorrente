package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import tavolainterface.TavolaInterface;
import tavola.Tavola;

import java.util.List;
import java.util.LinkedList;

public class Server{

    private static final int MAX_CLIENTS = 2;
    private final int SERVER_PORT = TavolaInterface.serverPort;

    private List<Slave> slavesList;
    private Tavola tavola; //copia salvata valida fino al momento di una nuova copia
    private final char[] segnaPosto = {'X', 'O'};

    public Server(){
        slavesList = new LinkedList<Slave>();
    }

    public static void main(String[] args){
        Server serv = new Server();
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(serv.SERVER_PORT);
            System.out.println("ServerSocket lanciato @"+ serv.SERVER_PORT);
            while(true){
                Socket sock = serverSocket.accept();
                System.out.println("Nuova connession in entrata");
                Slave slave = new Slave(serv, sock, serv.getTavola());
                System.out.println("Nuovo slave lanciato");
                serv.addSlave(slave);
            }
        }catch(IOException e){
            System.err.println("Errore durante la creazione di ServerSocket @"+ serv.SERVER_PORT);
            return;
        }
    }

    /**
     * Controlla che non più di una cella sia stata modificata
     * rispetto all'ultima copia salvata
     * @param t
     * @return
     */
    public String checkTavola(Tavola t){
        int modifiedPosCounter = 0; //se >= 2 => la tavola non sarà valida
        char[][] oldCelle = tavola.getCelle();
        char[][] newCelle = t.getCelle();

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(oldCelle[i][j] != newCelle[i][j])
                    modifiedPosCounter++;
            }
        }
        if(modifiedPosCounter == 1)
            return "OK";
        else
            return "NOT_OK";
    }

    public void initGame(){
        System.out.println("Server1 > Initializing Tavola");
        tavola = new Tavola();
        tavola.reset();
        System.out.println("Serve1 > Done");
    }

    private void addSlave(Slave s){
        slavesList.add(s);
    }

    public int getSlaveListSize(){
        return slavesList.size();
    }

    public Tavola getTavola(){
        return this.tavola;
    }

    public void setTavola(Tavola _tavola){
        tavola = _tavola;
    }

    public String checkWinner(){
        TavolaInterface.Winner winner = tavola.whoWins();
        if(winner.equals(TavolaInterface.Winner.PLAYER1))
            return "PLAYER1";
        else if(winner.equals(TavolaInterface.Winner.PLAYER2))
            return "PLAYER2";
        else if(winner.equals(TavolaInterface.Winner.NONE))
            return "NONE";
        else
            return "EVEN";
    }

}
