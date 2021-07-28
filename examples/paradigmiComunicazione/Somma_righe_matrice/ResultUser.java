
public class ResultUser implements Runnable{
	private int results[];
	public ResultUser(int r[]){
		this.results=r;
	}
	public void run() {
		int total=0;
		System.out.print("[");
		for(int i=0; i<results.length; i++){
			total+=results[i];
			System.out.print(results[i]+" ");
		}
		System.out.println("]");
		System.out.print("total = "+total);
	}
}
