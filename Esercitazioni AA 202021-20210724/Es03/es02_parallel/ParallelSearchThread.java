
public class ParallelSearchThread extends Thread{
	private BinTree myBT;  // l'albero in cui cercare
	private int myX;       // il valore da cercare
	private Result theResult=null;  // riferimento all'oggetto in cui segnalare progressi e completamento
	public ParallelSearchThread(BinTree bt, int x, Result res) {
		myBT=bt;
		myX=x;
		theResult=res;
	}
	private void search(BinTree bt, int vx){
		if(bt!=null){
			System.out.println(this.getName()+" started searching "+vx+" at ["+bt.getValue()+"]");
			theResult.incVisits();
			if(bt.getValue()==vx){
				theResult.setNode(bt);
				theResult.setSuccess();
				System.out.println(this.getName()+" found "+vx+"!!!");
			} else {
				System.out.println(this.getName()+" visited "+bt.getValue());
			}
			// il codice qui sotto si potrebbe ottimizzare...
			if(bt.getRight()!=null){
				new ParallelSearchThread(bt.getRight(), vx, theResult).start();
			}
			if(bt.getLeft()!=null){
				search(bt.getLeft(), vx);
			}
		}
	}
	public void run(){
		System.out.println(this.getName()+" running");
		search(myBT, myX);
		System.out.println(this.getName()+" finished");
	}
}