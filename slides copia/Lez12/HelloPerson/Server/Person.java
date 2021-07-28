public class Person implements java.io.Serializable{
  private static final long serialVersionUID = 1L;
  private String name;
  public Person(String n){
     this.name = n;
  }
  public String getName(){
     return this.name;
  }
}

