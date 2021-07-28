import java.io.Serializable;

public class Point implements Serializable{
  private static final long serialVersionUID = 99;
  private int x, y;
  public Point(int x,int y){
    this.x=x;
    this.y=y;
  }
  public String toString(){
    return "("+x+","+y+")";
  }
}
