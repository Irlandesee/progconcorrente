
public class BoundedBuffer<Data> {
	private Data buffer[];
	private int first, last;
	private int numberInBuffer = 0;
	private int size;
	public BoundedBuffer(int s) {
		size=s;
		buffer=(Data[]) new Object[size];
		last=0;
		first=0;
	}
	public synchronized void put(Data item)
			throws InterruptedException{
		while(numberInBuffer==size)
			wait();
		last=(last+1)%size;
		numberInBuffer++;
		buffer[last]=item;
		notifyAll();
	}
	public synchronized Data get()
			throws InterruptedException {
		while(numberInBuffer==0)
			wait();
		first=(first+1)%size;
		numberInBuffer--;
		notifyAll();
		return buffer[first];
	}


}
