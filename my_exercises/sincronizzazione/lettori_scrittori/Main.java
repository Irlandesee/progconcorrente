public class Main{

	public static void main(String[] args){
		
		ArrayCondiviso ac = new ArrayCondiviso(30);
		Scrittore[] scrittori = new Scrittore[4];
		Lettore[] lettori = new Lettore[2];
		//creating writers
		for(int i = 0; i < scrittori.length; i++){
			scrittori[i] = new Scrittore(ac, i);
		}

		//creating readers
		for(int i = 0; i < lettori.length; i++){
			lettori[i] = new Lettore(ac, i);
		}

		//starting writers
		for(Scrittore sc : scrittori)
			sc.start();
		//starting readers
		for(Lettore ltt : lettori)
			ltt.start();

		//joining writers
		for(Scrittore sc : scrittori){
			try{
				sc.join();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}

		//joining readers
		for(Lettore ltt : lettori){
			try{
				ltt.join();
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}

		System.out.println("Main, writers & readers done.");
		System.exit(0);
	} 

}