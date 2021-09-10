package binTree;

import java.util.Queue;
import java.util.LinkedList;

public class BinaryTree{

	private Node root;

	public BinaryTree(Node _root){
		root = _root;
	}

	private Node addRecursive(Node current, int val){
		if(current == null)
			return new Node(val);
		if(val < current.getVal())
			current.setLeftNode(addRecursive(current.getLeftNode(), val));
		else if(val > current.getVal())
			current.setRightNode(addRecursive(current.getRightNode(), val));
		else //value already exists
			return current;
		return current;
	}

	public void add(int val){
		root = addRecursive(root, val);
	}

	private boolean containsNode(Node current, int val){
		if(current == null)
			return false;
		if(val == current.getVal())
			return true;
		return val < current.getVal() ? containsNode(current.getLeftNode(), val) :
			containsNode(current.getRightNode(), val);
	}

	public boolean find(int val){
		return containsNode(root, val);
	}

	private Node destroyNode(Node current, int val){
		if(current == null)
			return null;
		if(val == current.getVal()){
			//a node has no children
			if(current.getLeftNode() == null && current.getRightNode() == null)
				return null;
			//a node has 1 child
			if(current.getRightNode() == null)
				return current.getLeftNode();
			if(current.getLeftNode() == null)
				return current.getRightNode();
			//a node has 2 children
			int smallestVal = findSmallestVal(current.getRightNode());
			current.setVal(smallestVal);
			current.setRightNode(destroyNode(current.getRightNode(), smallestVal));
			return current;
		}
		if(val < current.getVal()){
			current.setLeftNode(destroyNode(current.getLeftNode(), val));
			return current;
		}
		current.setRightNode(destroyNode(current.getRightNode(), val));
		return current;
	}

	private int findSmallestVal(Node root){
		return root.getLeftNode() == null ? root.getVal() : findSmallestVal(root.getLeftNode());
	}

	public void delete(int val){
		root = destroyNode(root, val);
	}

	//Not thread safe
	public void traverseLevelOrder(){
		if(root == null)
			return;

		Queue<Node> nodes = new LinkedList<Node>();
		nodes.add(root);

		while(!nodes.isEmpty()){
			Node node = nodes.remove();
			System.out.printf(" %d", node.getVal());
			if(node.getLeftNode() != null)
				nodes.add(node.getLeftNode());
			if(node.getRightNode() != null)
				nodes.add(node.getRightNode());
		}

	}

	public Node getRoot(){
		return root;
	}

}