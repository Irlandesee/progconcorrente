package binTree;

public class Node{

	private int val;
	private Node left;
	private Node right;

	private boolean visited;

	public Node(int _val){
		val = _val;
		right = null;
		left = null;

		boolean visited = false;
	}

	public int getVal(){
		return val;
	}

	public void setVal(int _val){
		val = _val;
	}

	public Node getLeftNode(){
		return left;
	}

	public void setLeftNode(Node _left){
		left = _left;
	}

	public Node getRightNode(){
		return right;
	}

	public void setRightNode(Node _right){
		right = _right;
	}

	public boolean getVisited(){
		return visited;
	}

	public void setVisited(boolean _visited){
		visited = _visited;
	}

}