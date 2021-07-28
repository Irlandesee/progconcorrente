import java.io.Serializable;

class Person implements Serializable{
	private static final long serialVersionUID = 1;
	String name;
	Person(String n) {
		this.name = n;
	}
}
