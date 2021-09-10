public class Data{
	
	private String data;
	private int nReaders;
	private int nWriters;
	private boolean writePending;

	public Data(){

		data = "";
		nReaders = nWriters = 0;
		writePending = false;

	}

	public synchronized void startReading(){
		while(nWriters > 0 || writePending){
			if(writePending)
				System.out.printf("%d waiting on pending write..", Thread.currentThread().getName());
			try{
				wait();
			}catch(InterruptedException ie){}
		}
		nReaders++;
	}

	public synchronized void finishedReading(){
		nReaders--;
		if(nReaders == 0)
			notifyAll();
	}

	public synchronized void startWriting(){
		writePending = true;
		while((nWriters + nReaders) > 0){
			try{
				System.out.println("Writer going in waiting");
				wait();
			}catch(InterruptedException ie){}
		}
		writePending = false;
		nWriters++;
	}

	public synchronized void finishedWriting(){
		nWriters--;
		if(nWriters == 0)
			notifyAll();
	}

}