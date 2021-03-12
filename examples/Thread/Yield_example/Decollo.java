public class Decollo implements Runnable {
	protected int countDown = 5;
	private static int taskCount = 0;
	private final int id = taskCount++;
	public String status () {
		return " " + id + "(" + ( countDown > 0 ? countDown: "Decollo !") + ")";
	}
	public void run() {
		while(countDown-- > 0) {
			System.out.println(status());
			Thread.yield();
		}
	}
}
