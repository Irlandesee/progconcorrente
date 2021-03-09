public class Counter{

	private int i;

	public Counter(int i){
		this.i = i;
	}

	public void add(){
		i += 1;
	}

	public String toString(){
		return ""+i;
	}
}