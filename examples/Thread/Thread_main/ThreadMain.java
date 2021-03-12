
public class ThreadMain {
	public static void main(String args []) {
		Thread t = Thread.currentThread( );
		System.out.println("Thread corrente: " + t );
		t.setName("Mio Thread");
		System.out.println("Dopo cambio nome: " + t );
	}
}
