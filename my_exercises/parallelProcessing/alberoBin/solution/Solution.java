package solution;

import java.util.LinkedList;
import java.util.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import binTree.BinaryTree;
import binTree.Node;

public class Solution{

	private BinaryTree binTree;
	private Node root;

	private LinkedBlockingQueue<Traverser> traverserList;
	private static int numberOfTraversers;

	public static int getNumberOfTraversers(){
		return numberOfTraversers;
	}

	public Solution(BinaryTree _binTree){
		binTree = _binTree;
		root = binTree.getRoot();
		traverserList = new LinkedBlockingQueue<Traverser>();
		numberOfTraversers = 0;
	}

	public void exec(){
		//node has no children
		if(root.getLeftNode() == null && root.getRightNode() == null)
			return;
		else{
			
			System.out.println("Starting traversers");
			//node has 1 child
			if(root.getLeftNode() != null && root.getRightNode() == null){
				numberOfTraversers++;
				Traverser t = new Traverser(this, root.getLeftNode());
				try{
					traverserList.put(t);	
				}catch(InterruptedException ie){ie.printStackTrace();}
				t.start();
			}
			if(root.getRightNode() != null && root.getLeftNode() == null){
				numberOfTraversers++;
				Traverser t = new Traverser(this, root.getRightNode());
				try{
					traverserList.put(t);
				}catch(InterruptedException ie2){
					ie2.printStackTrace();
				}
				t.start();
			}

			//node has 2 children
			if(root.getLeftNode() != null && root.getRightNode() != null){
				numberOfTraversers+=2;
				Traverser t1 = new Traverser(this, root.getLeftNode());
				Traverser t2 = new Traverser(this, root.getRightNode());
				try{
					traverserList.put(t1);
					traverserList.put(t2);	
				}catch(InterruptedException ie3){
					ie3.printStackTrace();
				}
				t1.start();
				t2.start();
			}

			System.out.println("Solution: waiting for threads to finish");
			for(Traverser t : traverserList){
				try{
					t.join();
					traverserList.take();	
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
				
			}

			System.out.println("Done.");

		}
	}

	public synchronized LinkedBlockingQueue<Traverser> getTraversersList(){
		return traverserList;
	}

	public void addTraverser(Traverser t){
		traverserList.add(t);
		numberOfTraversers++;
	}


}