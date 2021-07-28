import java.util.ArrayList;

public class BarrierReachedAction implements Runnable {
	private ArrayList<WordCounter> wordCounters;
	long t0=0;
	public BarrierReachedAction(long start) {
		t0=start;
	}
	public void setCounters(ArrayList<WordCounter> wordCounters) {
		this.wordCounters=wordCounters;
	}
	public void run() {
		System.out.println("BarrierReached!");
		int totalWords=0;
		for(WordCounter wc:wordCounters) {
			totalWords+=wc.wordCount;
		}
		long totalTimeElapsed=System.currentTimeMillis()-t0;
		System.out.println("Elapsed time = "+totalTimeElapsed);
		System.out.println("Word count is = " + totalWords);
	}
}
