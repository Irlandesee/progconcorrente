import solution.Solution;
import solution.Producer;
import solution.Consumer;

public class Main{

	public static void main(String[] args){
		int capacity = 10;
		String producerName = "p1";
		String consumerName = "c1";
		Solution s = new Solution(capacity);
		Consumer c = new Consumer(consumerName, s);
		Producer p = new Producer(producerName, s);

	}

}