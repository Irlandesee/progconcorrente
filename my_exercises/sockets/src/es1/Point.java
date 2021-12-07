package es1;

public class Point{
	double x, y;
	final double THRESHOLD = 0.000001;
	
	public Point(double tx, double ty){
		this.x = tx;
		this.y = ty;
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}


	public boolean equals(Point p){
		return (Math.abs(x -p.getX()) < THRESHOLD) &&
			(Math.abs(y - p.getY()) < THRESHOLD);
	}

	public String toString(){
		return this.getX() + "," + this.getY();
	}
}