import java.util.concurrent.Semaphore;

public class ArrayCondiviso{

	private int[] celle;
	private int arrayLength;

	private int currentSize; //indica il numero di item presenti

	private int writeIndex = 0;
	private int readIndex = 0;

	private final int READ_ERROR = -1;
	private final boolean WRITE_ERROR = false;

	//semafori
	public static Semaphore mtx; //accesso a zona critica
	public static Semaphore empty; //scrittori
	public static Semaphore full; //lettori

	public ArrayCondiviso(int arrayLength){
		this.arrayLength = arrayLength;
		this.celle = new int[arrayLength];

		initSemaphores();
		initArray();
	}

	private void initArray(){
		System.out.printf("CC: init array\n");
		for(int i = 0; i < celle.length; i++)
			celle[i] = -1;
		System.out.printf("CC: init array DONE\n");
	}

	private void initSemaphores(){
		System.out.printf("CC: init semafori\n");
		mtx = new Semaphore(1);
		full = new Semaphore(0);
		empty = new Semaphore(arrayLength);
		System.out.printf("CC: init semafori DONE\n");
	}

	public int getCurrentSize(){
		return this.currentSize;
	}

	/**
	 * @return true -> scrittura successful
	 * */
	public void write(int number, int writerID){
		try{ //inizio della zona critica
			ArrayCondiviso.mtx.acquire();
			System.out.printf("CC, %d has acquired mutex lock\n", writerID);
		}catch(InterruptedException ie){
			System.out.printf("CC: ie while trying to acquire mtx lock\n");
			ie.printStackTrace();
		}
		celle[writeIndex] = number;
		System.out.printf("CC: writing %d@%d by %d\n", number, writeIndex, writerID);
		writeIndex++;
		currentSize++;
		if(writeIndex == celle.length){//reset write index 
			writeIndex = 0;
			System.out.printf("CC: resetting write index\n");
		} 
		
		System.out.printf("CC: %d is leaving crit zone\n", writerID);
		ArrayCondiviso.mtx.release();
		//fine della zona critica
	}

	/**
	 * @return -1 -> lettura fallita
	 * */
	public int read(int readerID){
		int num = -1;
		try{ //inizio zona critica
			ArrayCondiviso.mtx.acquire();
			System.out.printf("CC, %d has acquired mutex lock\n", readerID);
		}catch(InterruptedException ie){
			System.out.printf("CC: ie while trying to acquire mtx lock\n");
			ie.printStackTrace();
			return num; //-1
		}
		num = celle[readIndex];
		System.out.printf("CC: reading %d@%d by %d\n", num, readIndex, readerID);
		readIndex++;
		currentSize--;
		if(readIndex == celle.length){//reset read index
			readIndex = 0;
			System.out.printf("CC: resetting read index\n");
		}
		System.out.printf("CC: %d is leaving crit zone\n", readerID);
		ArrayCondiviso.mtx.release();
		//fine zona critica
		return num;
	}

}