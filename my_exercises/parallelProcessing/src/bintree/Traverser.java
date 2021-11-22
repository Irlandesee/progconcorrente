package bintree;

import java.util.Queue;
import java.util.LinkedList;

public class Traverser extends Thread{

    private static int traverserID = 0;

    private LinkedList<Node> queue;
    private BinTree tree;

    public Traverser(BinTree tree, Queue<Node> queue){

        this.tree = tree;
        this.queue = (LinkedList<Node>) queue;

        traverserID++;
        this.currentThread().setName(""+traverserID);
        this.start();
    }


    public void run(){
        System.out.printf("Traverser %d starting...\n", getTraverserID());
        while(!queue.isEmpty()){

            Node current = queue.pop();
            System.out.printf("Traverser %d visits node %d\n", getTraverserID(), current.getVal());
            if(current.getLeft() != null && current.getRight() == null)
                queue.add(current.getLeft());
            if(current.getRight() != null && current.getLeft() == null)
                queue.add(current.getRight());
            if(current.getLeft() != null && current.getRight() != null){
                queue.add(current.getLeft());
                LinkedList<Node> newQueue = new LinkedList<Node>();
                newQueue.add(current.getRight());
                Queue<Node> q = newQueue;
                System.out.printf("Traverser %d creates new traverser\n", getTraverserID());
                Traverser newTraverser = new Traverser(tree, q);

            }
        }

        System.out.printf("Traverser %d done...\n", getTraverserID());
        try{
            this.join();
        }catch(InterruptedException ie){}
    }

    public int getTraverserID(){
        return this.traverserID;
    }

    public LinkedList<Node> getQueue(){
        return this.queue;
    }

    public void setQueue(Queue<Node> queue){
        this.queue = (LinkedList<Node>) queue;
    }


}
