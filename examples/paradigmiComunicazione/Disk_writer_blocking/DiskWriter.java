
public class DiskWriter extends Thread {
	private PersistentSignal forSignalling;
	public DiskWriter(PersistentSignal s){
		this.forSignalling = s;
	}
	public void run(){
		boolean finito = false;
		while(!finito){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
			if(((long) (Math.random()*10))>5) {
				finito = true;
			}
		}
		forSignalling.send();
	}
}
