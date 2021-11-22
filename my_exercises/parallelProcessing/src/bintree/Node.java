package bintree;

public class Node {

    private int val;
    private Node left;
    private Node right;

    public Node(int val){
        this.val = val;
        left = null;
        right = null;
    }

    public int getVal(){
        return this.val;
    }

    public void setVal(int val){
        this.val = val;
    }

    public Node getLeft(){
        return this.left;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public Node getRight(){
        return this.right;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public String toString(){
        return this.val + "";
    }

}
