import bintree.BinTree;
import bintree.Node;
import bintree.Traverser;
import com.matrix.*;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        //es 1
        /**
	    Matrix m = new Matrix(10, 10);
        m.printMatrix();

        Summer[] sums = new Summer[10];
        for(int i = 0; i < 10; i++){
            Summer s = new Summer(m);
            sums[i] = s;
        }

        for(Summer s : sums)
            s.start();
        for(Summer s : sums)
            try{
                s.join();
            }catch(InterruptedException ie){}
         **/
        //es 2

        Node root = new Node(5);
        BinTree tree = new BinTree(root);
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        tree.add(6);
        tree.add(7);
        tree.add(8);
        tree.add(9);

        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        System.out.println("Main: first traverser starting...");
        Traverser t = new Traverser(tree, q);

    }
}
