package client;

import java.net.*;
import java.util.concurrent.*;
import java.util.Scanner;
import tavola.Tavola;

public class ClientThread extends Thread{

    private int id;
    private ProxyClient clientProxy;
    private Scanner sc;
    private Tavola t;

    public ClientThread(InetAddress address, int _id, Tavola _t){
        System.out.println("creazione del client: "+_id);
        char segnaposto = ' ';
        id = _id;
        if(id == 0)
            segnaposto = 'o';
        else if(id == 1)
            segnaposto = 'x';
        t = _t;
        sc = new Scanner(System.in);
        clientProxy = new ProxyClient(address, segnaposto);
        printHelp();
        this.start();
    }

    private void dormi(int t1, int t2){
        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(t1, t2));
        }catch(InterruptedException ie){
            ie.printStackTrace();
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

    private int leggiComandoClient(){
        printHelp();
        System.out.println("Inserisci comando... 1-5");
        String comandoClient;
        if((comandoClient = sc.nextLine()) != null){
            int cmd;
            try{
                cmd = Integer.parseInt(comandoClient);
            }catch(NumberFormatException ne){
                ne.printStackTrace();
                System.out.println("Inserisci un numero!");
                return leggiComandoClient();
            }
            return cmd;
        }
        System.out.printf("Inserire un numero da 1 a 5!\n");
        return leggiComandoClient();
    }

    private String leggiCoordinate(){
        String coordinate;
        System.out.printf("%s: Inserire coordinate. esempio: 1 2\n", this.getName());
        if((coordinate = sc.nextLine()) != null)
            return coordinate;
        System.out.printf("%s: inserire formato coordinate corretto!\n", this.getName());
        return leggiCoordinate();
    }

    private void initServerGioco(){

    }

    public void run(){
        boolean done = false;
        int comandoClient;
        while(!done){
            comandoClient = leggiComandoClient();
            switch(comandoClient){
                case 1:
                    System.out.printf("%s: connessione al server locale e creazione nuovo gioco\n", this.getName());
                    clientProxy.nuovoGioco();
                    break;
                case 2:
                    System.out.printf("Connessione a un server locale.");

                    break;
                case 3:
                    System.out.printf("Inserisci coordinate prossima mossa");
                    String coordinate = leggiCoordinate();
                    String[] coords = coordinate.split(" ");
                    int x = Integer.parseInt(coords[0]);
                    int y = Integer.parseInt(coords[1]);
                    System.out.println("Controllo se è il mio turno");

                    while(!(clientProxy.getTurno())){
                        try{
                            System.out.println("Aspetto il mio turno");
                            wait();
                        }catch(InterruptedException ie){
                            ie.printStackTrace();
                        }
                    }
                    System.out.println("Invio delle coordinate");
                    if(clientProxy.set(x, y, clientProxy.getSegnaposto())){
                            System.out.println("Coordinate valide");
                            dormi(10, 20);
                            if((t = clientProxy.getTavola()) != null)
                                t.show();
                            else
                                return;
                        }
                        else
                            System.out.println("Coordinate non valide!");
                    break;
                case 4:
                    System.out.printf("Disconnessione dal server ");
                    clientProxy.reset();
                    clientProxy.quit();
                    break;
                case 5:
                    System.out.printf("Disconnessione dal server e uscita dalla applicazione");
                    clientProxy.quit();
                    done = true;
                    break;
                default:
                    System.out.printf("comando non valido");
                    printHelp();
                    break;
            }
        }
    }

}
