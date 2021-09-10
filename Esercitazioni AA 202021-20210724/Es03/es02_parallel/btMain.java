
public class btMain {
	void doSeqSearch(int vx, BinTree bt) {
		BinTree res=bt.seqSearch(vx);
		if(res==null){
			System.out.println("Not found "+vx);
		} else {
			System.out.println("Found "+vx);
		}
	}
	void doParSearch(int vx, BinTree bt) {
		Result resObj=new Result(bt.getSize());
		Thread mySearcher = new ParallelSearchThread(bt, vx, resObj);
		System.out.println("Main: launching thread "+mySearcher.getName());
		mySearcher.start();
		while(!resObj.isCompleted()){
			System.out.println("Main dorme");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
		}
		if(resObj.isSuccess()){
			System.out.println("Main: Found "+vx);
		} else {
			System.out.println("Main: Not found "+vx);			
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
		doSeqSearch(13, bt3);
		doSeqSearch(6, bt3);
		System.out.println("================================");
		doParSearch(13, bt3);
		System.out.println("================================");
		doParSearch(6, bt3);
		System.out.println("================================");
		doParSearch(15, bt3);
	}
	public static void main(String args[]){
		btMain btm=new btMain();
		btm.exec();
	}
}
