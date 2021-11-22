import tavola.Tavola;
import client.Client;
import server.Server;

public class Main {

    public static void main(String[] args){
        String segnaposto = args[0];
        char s = segnaposto.toCharArray()[0];
        //Server serv = new Server();
        Client x = new Client(0, s);
        //Client o = new Client(1, 'o');

        x.exec("localhost");
        //o.exec("localhost");

    }

}
