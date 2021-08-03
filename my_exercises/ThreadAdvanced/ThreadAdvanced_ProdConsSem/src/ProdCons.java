

import java.util.concurrent.Semaphore;

public class ProdCons{
	public static final Semaphore mutex = new Semaphore(1);
	public static final Semaphore full = new Semaphore(0);
	public static final Semaphore empty = new Semaphore(Solution.BUFFERSIZE);

	public static void main(String[] args){
		Solution s = new Solution();
		new Prod("Prod", s).start();
		new Cons("Cons", s).start();

	}
}