package es2.offer;

public class Offer{

    private static final long serialVersionUID = 1;

    private String clientID;
    private int slaveID;
    private String item;
    private double offeredAmount;

    public Offer(String clientID, String item, double offeredAmount){
        this.clientID = clientID;
        this.item = item;
        this.offeredAmount = offeredAmount;
    }

    public String getClientID(){return this.clientID;}

    public String getItem(){return this.item;}

    public double getOfferedAmount(){return this.offeredAmount;}

    public int getSlaveID(){return this.slaveID;}

    public void setSlaveID(int slaveID){this.slaveID = slaveID;}

    public String toString(){
        return "clientID: " + this.clientID + "\nitem: " + this.item + "\nofferedAmount:" + this.offeredAmount;
    }

    public boolean compareOffers(Offer otherOffer){
        if(this.offeredAmount > otherOffer.getOfferedAmount())
            return true;
        return false;
    }

}
