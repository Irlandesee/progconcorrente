
public class BinTree {
	private int value;
	private BinTree left=null;
	private BinTree right=null;
	public BinTree(int v, BinTree l, BinTree r){
		this.value=v;
		this.left=l;
		this.right=r;
	}
	public BinTree getLeft(){
		return this.left;
	}
	public BinTree getRight(){
		return this.right;
	}
	public int getValue(){
		return this.value;
	}
	public void addLeft(BinTree bt){
		this.left=bt;
	}
	public void addRight(BinTree bt){
		this.right=bt;
	}

	public void printBT(){
		if(this!=null){
			if(this.getLeft()!=null){
				System.out.print("["); this.getLeft().printBT(); System.out.print("]");
			}
			System.out.print(" " + this.getValue() + " ");
			if(this.getRight()!=null){
				System.out.print("["); this.getRight().printBT(); System.out.print("]");
			}
		}
	}
	int getSize() {
		int res=1;
		if(this.getLeft()!=null) {
			res+=this.getLeft().getSize();
		}
		if(this.getRight()!=null) {
			res+=this.getRight().getSize();
		}
		return res;
	}
	public BinTree seqSearch(int vx){
		BinTree res=null;
		if(this.getValue()==vx){
			return this;
		}
		if(this.getLeft()!=null){
			res=this.getLeft().seqSearch(vx);
			if(res!=null){
				return res;
			}
		}
		if(this.getRight()!=null){
			return this.getRight().seqSearch(vx);
		}
		return (BinTree)null;
	}
}
