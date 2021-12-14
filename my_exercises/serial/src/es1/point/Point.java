package es1.point;
public class Point{

  private static final long serialVersionUID = 1;

  private double x, y;
  private double THRESHOLD = 0.0000001;
  
  public Point(double tx, double ty){
    x = tx; y = ty;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public boolean isEqual(Point p){
    return (Math.abs(x - p.getX()) < THRESHOLD) &&
      (Math.abs(y - p.getY()) < THRESHOLD);
  }

}