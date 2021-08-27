package com.ex_segment.point;

public class Point {

    private double x, y;
    private final double THRESHOLD = 0.0000001;

    public Point(double _x, double _y){
        x = _x;
        y = _y;
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

    public String toString(){
        return "x: "+ getX() + ", y: " + getY();
    }

}
