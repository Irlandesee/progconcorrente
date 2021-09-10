
public class btMain {
	void doTheSearch(int vx, BinTree bt) {
		BinTree res=bt.seqSearch(vx);
		if(res==null){
			System.out.println("Not found "+vx);
		} else {
			System.out.println("Found "+vx);
		}
	}
	void exec() {
		BinTree bt1 = new BinTree(5, null, null);
		bt1.addLeft(new BinTree(3, null, null));
		bt1.addRight(new BinTree(11, null, null));
		BinTree bt2 = new BinTree(15, null, null);
		bt2.addLeft(new BinTree(13, null, null));
		bt2.addRight(new BinTree(19, null, null));
		BinTree bt3=new BinTree(12, bt1, bt2);
		bt3.printBT(); System.out.println();
		doTheSearch(13, bt3);
		doTheSearch(6, bt3);
	}
	public static void main(String args[]){
		btMain btm=new btMain();
		btm.exec();
	}
}
