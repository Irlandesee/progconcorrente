import binTree.*;
import solution.*;

import java.util.Random;

public class Main{

	private static Random rand;
	private static final int bound = 10;


	private static int nextInt(){
		rand = new Random();
		return rand.nextInt(bound);
	}

	private static BinaryTree buildBinTree(BinaryTree tree){
		
		tree.add(10);
		tree.add(5);
		tree.add(63);
		tree.add(6);
		tree.add(20);
		tree.add(70);
		tree.add(19);
		tree.add(27);
		tree.add(18);
		tree.add(21);

		return tree;
	}

	public static void main(String[] args){
		Node root = new Node(nextInt());
		BinaryTree tree = new BinaryTree(root);
		tree = buildBinTree(tree);

		System.out.println("Single threaded traverser ->");
		tree.traverseLevelOrder();
		System.out.println();
		for(int i = 0; i < 30; i++)
			System.out.print("-");

		System.out.println("\nMulti threaded Traveser ->");

		Solution s = new Solution(tree);
		System.out.println("S initializing...");
		s.exec();

		

	}

}