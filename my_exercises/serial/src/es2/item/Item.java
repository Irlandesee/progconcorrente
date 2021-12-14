package es2.item;

public class Item {

    private static final long serialVersionUID = 1;

    private String name;
    private double cost;

    public Item(String name, double cost){
        this.name = name;
        this.cost = cost;
    }

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}
    public double getCost(){return this.cost;}
    public void setCost(double cost){this.cost = cost;}
    public String toString(){return "item name: "+this.name+"\ncost: "+this.cost;}

}
