package solution;

import java.util.LinkedList;
import java.util.Queue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import binTree.BinaryTree;
import binTree.Node;

public class Traverser extends Thread{

	private Node root;
	private Queue<Traverser> traverserList;

	private Solution s;

	private static int traverserID = 0;

	public static int getTraveserID(){
		return traverserID;
	}

	public Traverser(Solution _s, Node _root){
		s = _s;
		traverserList = s.getTraversersList();
		root = _root;
		traverserID++;
	}

	public void run(){
		if(root == null)
			return;
		BlockingQueue<Node> nodes = new LinkedBlockingQueue<Node>();
		try{
			nodes.put(root);	
		}catch(InterruptedException ie4){
			ie4.printStackTrace();
		}
		
		System.out.printf("Traverser %d has visited: %d\n", Traverser.getTraveserID(),
					root.getVal());
		while(!nodes.isEmpty()){
			try{
				root = nodes.take();	
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			
			//node has no children
			if(root.getLeftNode() == null && root.getRightNode() == null){
				System.out.printf("Traverser %d has visited: %d\n", Traverser.getTraveserID(),
					root.getVal());
				return;
			}
			//node has 1 child
			if(root.getLeftNode() != null && root.getRightNode() == null){
				System.out.printf("Traverser %d has visited: %d\n", Traverser.getTraveserID(),
					root.getVal());
				try{
					nodes.put(root.getLeftNode());	
				}catch(InterruptedException ie1){
					ie1.printStackTrace();
				}
			}
			if(root.getRightNode() != null && root.getLeftNode() == null){
				System.out.printf("Traverser %d has visited: %d\n", Traverser.getTraveserID(),
					root.getVal());
				try{
					nodes.put(root.getRightNode());	
				}catch(InterruptedException ie2){
					ie2.printStackTrace();
				}
			}
			//node has 2 children
			if(root.getLeftNode() != null && root.getRightNode() != null){
				System.out.printf("Traverser %d has visited: %d\n", Traverser.getTraveserID(),
					root.getVal());
				try{
					nodes.put(root.getLeftNode());	
				}catch(InterruptedException ie3){
					ie3.printStackTrace();
				}
				
				Traverser t = new Traverser(s, root.getRightNode());
				s.addTraverser(t);
				t.start();
			}
		}
	}
}