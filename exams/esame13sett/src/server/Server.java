package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import serverinterface.ServerInterface;
import tavola.Tavola;

import java.util.List;
import java.util.LinkedList;

public class Server{

    private static final int MAX_CLIENTS = 2;
    private final int SERVER_PORT = ServerInterface.serverPort;

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
             System.out.printf("Avviato nuovo server @%d", serv.SERVER_PORT);
             System.out.printf("Server in attesa di clients...\n");
             int clientsConnected = 0;
             serv.initGame(); //inizializzazione della tavola
             while(true){
                 System.out.printf("Server > clients connessi:%d\n", clientsConnected);
                 Socket s = null;
                 if((s = serverSocket.accept()) != null && clientsConnected <= MAX_CLIENTS ){
                     clientsConnected++;
                     System.out.println("Server accetta nuovo client...");
                     System.out.printf("Server > clients connessi: %d\n", clientsConnected);
                     Slave temp = null;
                     if(clientsConnected == 1)
                         temp = new Slave(serv, s, serv.getTavola(), 'X');
                     else
                         temp = new Slave(serv, s, serv.getTavola(), 'O');
                     temp.start();
                     serv.addSlave(temp);
                 }
                 else{
                     System.out.println("Clients massimi raggiunti, server rifiuta connessione...");
                     System.out.printf("Server > clients connessi: %d\n", clientsConnected);
                 }
             }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            System.out.println("Closing server");
            try{
                serverSocket.close();
            }catch(IOException e1){
                System.out.println("Error while closing down server");
                e1.printStackTrace();
            }

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

    public Tavola getTavola(){
        return this.tavola;
    }

    public void setTavola(Tavola _tavola){
        tavola = _tavola;
    }

}
