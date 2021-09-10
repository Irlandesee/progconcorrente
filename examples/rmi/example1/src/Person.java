public class Person implements java.io.Serializable{

	private String name;
	
	public Person(String _name){
		name = _name;
	}

	public String getName(){
		return name;
	}

}