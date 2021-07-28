import java.io.*;

class Employee implements Serializable{
  private static final long serialVersionUID = 1;
  String name;
  int serNum;
  float salary;
  Person parent;
  Employee(String name, int no, float sal, String pname){
      this.name=name;
      serNum = no;
      salary = sal;
      parent = new Person(pname);
  }
  void display(){
    System.out.println(name+"   "+serNum+"    "+salary);
  }
  String getParentName(){
	    return parent.name;
	  }
}
