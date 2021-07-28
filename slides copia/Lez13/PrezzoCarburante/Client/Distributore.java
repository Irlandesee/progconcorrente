public class Distributore implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private double prezzoBenzina;
	private double prezzoDiesel;
	public Distributore(String name, double b, double d) {
		this.name = name;
		this.prezzoBenzina = b;
		this.prezzoDiesel = d;
	}
	public String getName() {
		return name;
	}
	public double getPrezzoBenzina() {
		return prezzoBenzina;
	}
	public void setPrezzoBenzina(double prezzoBenzina) {
		this.prezzoBenzina = prezzoBenzina;
	}
	public double getPrezzoDiesel() {
		return prezzoDiesel;
	}
	public void setPrezzoDiesel(double prezzoDiesel) {
		this.prezzoDiesel = prezzoDiesel;
	}
	public String toString(){
		return "Distributore: " + name
				+ "\n\tbenzina: " + prezzoBenzina
				+ "\n\tdiesel: " + prezzoDiesel;
	}
}