package es2.client;

import es2.offer.Offer;
import es2.serverinterface.ServerInterface;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientProxy implements ServerInterface{

    private int proxyID;
    private Socket sock;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientProxy(int proxyID, Socket sock){
        this.proxyID = proxyID;
        this.sock = sock;
    }

    public void quit() throws IOException {
        outputStream = new ObjectOutputStream(sock.getOutputStream());
        outputStream.writeObject(ServerInterface.QUIT);
        outputStream.flush();
        outputStream.close();
    }

    public double read() throws IOException, ClassNotFoundException{
        inputStream = new ObjectInputStream(sock.getInputStream());
        outputStream = new ObjectOutputStream(sock.getOutputStream());

        outputStream.writeObject(ServerInterface.READ);
        double topOffer = (double) inputStream.readObject();

        inputStream.close();
        outputStream.flush();
        outputStream.close();
        return topOffer;
    }

    public void offer(Offer offer) throws IOException, ClassNotFoundException{
        inputStream = new ObjectInputStream(sock.getInputStream());
        outputStream = new ObjectOutputStream(sock.getOutputStream());

        outputStream.writeObject(ServerInterface.OFFER);
        outputStream.writeObject(offer);
        String response = inputStream.readObject().toString();
        if(response.equals(ServerInterface.OK))
            System.out.printf("Proxy %d: Offered placed correctly\n", proxyID);
        else
            System.out.printf("Proxy %d: failed to place the offer\n", proxyID);
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    public List<Offer> getOffers() throws IOException, ClassNotFoundException{
        inputStream = new ObjectInputStream(sock.getInputStream());
        outputStream = new ObjectOutputStream(sock.getOutputStream());
        List<Offer> res = new LinkedList<Offer>();

        outputStream.writeObject(ServerInterface.GET_OFFERS);
        Object received;
        while(!(received = inputStream.readObject()).equals(ServerInterface.OK)){
            if(!received.equals(ServerInterface.KO))
                res.add((Offer) received);
        }
        System.out.println("Done receiving objects");
        inputStream.close();
        outputStream.flush();
        outputStream.close();
        return res;
    }

    public String getItem() throws IOException, ClassNotFoundException{
        inputStream = new ObjectInputStream(sock.getInputStream());
        outputStream = new ObjectOutputStream(sock.getOutputStream());

        outputStream.writeObject(ServerInterface.GET_ITEM);
        String itemInOffer = inputStream.readObject().toString();

        inputStream.close();
        outputStream.flush();
        outputStream.close();
        return itemInOffer;
    }
}
