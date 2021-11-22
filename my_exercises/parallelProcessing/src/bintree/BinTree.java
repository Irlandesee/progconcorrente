package bintree;

public class BinTree {

    private Node root;

    public BinTree(Node root){
        this.root = root;
    }

    private Node addRecursive(Node current, int val){
        if(current == null)
            return new Node(val);
        if(val < current.getVal())
            current.setLeft(addRecursive(current.getLeft(), val));
        else if(val > current.getVal())
            current.setRight(addRecursive(current.getRight(), val));
        else
            return current;
        return current;
    }

    public void add(int val){
        root = addRecursive(root, val);
    }

    private boolean containsNodeRecursive(Node current, int val){
        if(current == null)
            return false;
        if(val == current.getVal())
            return true;
        return val < current.getVal()
                ? containsNodeRecursive(current.getLeft(), val)
                : containsNodeRecursive(current.getRight(), val);
    }

    public boolean containsNode(int val){
        return containsNodeRecursive(root, val);
    }

    private int findSmallestValue(Node root){
        return root.getLeft() == null ? root.getVal() : findSmallestValue(root.getLeft());
    }

    private Node deleteRecursive(Node current, int val){
        if(current == null)
            return null;
        if(val == current.getVal()){
            if(current.getLeft() == null && current.getRight() == null)
                return null;
            if(current.getRight() == null)
                return current.getLeft();
            if(current.getLeft() == null)
                return current.getRight();
            //node has 2 children
            int smallestValue = findSmallestValue(current.getRight());
            current.setVal(smallestValue);
            current.setRight(deleteRecursive(current.getRight(), smallestValue));
            return current;
        }
        if(val < current.getVal()){
            current.setLeft(deleteRecursive(current.getLeft(), val));
            return current;
        }
        current.setRight(deleteRecursive(current.getRight(), val));
        return current;
    }

    public void delete(int val){
        root = deleteRecursive(root, val);
    }

}
