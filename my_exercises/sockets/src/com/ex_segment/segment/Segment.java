package com.ex_segment.segment;

import com.ex_segment.point.Point;

public class Segment {

    private Point p1 = null;
    private Point p2 = null;

    public Segment(){}

    public boolean set(Point _p1, Point _p2){
        if(!_p1.isEqual(_p2)){
            p1 = _p1;
            p2 = _p2;
            return true;
        }else
            return false;
    }

    private Point midPoint(){
        double mx, my;
        mx = (p1.getX() + p2.getX()) / 2;
        my = (p1.getY() + p2.getY()) / 2;
        return (new Point(mx, my));
    }

    public Point simmetric(Point p){
        Point m = this.midPoint();
        double simmX = 2 * m.getX() - p.getX();
        double simmY = 2 * m.getY() - p.getY();
        return new Point(simmX, simmY);
    }

    public String toString(){
        if(p1 != null && p2 != null) {
            return "[Segment]\np1: " +p1.toString()
                    +"\np2: " +p2.toString()
                    +"\n[End Segment]";
        }else
            return "";
    }

}
