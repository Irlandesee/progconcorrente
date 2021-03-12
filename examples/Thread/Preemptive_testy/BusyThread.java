
public class BusyThread extends Thread{
	public void run() {
		int a=0;
		while(true){
			if(a>100){ a=a+1; }
			else { a=a-1; }
		}
	}
} 
