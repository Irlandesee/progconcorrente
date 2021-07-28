import java.io.*;

public class CounterClientThread extends Thread {
	private int id;
	CounterClientThread(int i){
		id=i;
	}
	public void run() {
		CounterInterface_with_close localServer=null;
		try {
			localServer = new CounterProxy();
			int init = localServer.reset();
			System.out.println("Client "+id+" reset: "+init);
			long startTime = System.currentTimeMillis();
			for(int i = 0; i < 100; i++){
				int r = localServer.sum(1);
				System.out.println("Client "+id+" increment: "+r);
			}
			System.out.println("Client "+id+" Elapsed time: "+
					(System.currentTimeMillis()-startTime)+ "ms");
		} catch (Exception e) { e.printStackTrace();
		} finally {
			System.out.println("Client "+id+" closing...");
			try { localServer.close();} catch (IOException e) { }
		}
	}
}
