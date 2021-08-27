package com.ex_segment;

import com.ex_segment.point.Point;
import com.ex_segment.segment.Segment;

public class SegmentClient {

    public static void main(String[] args){
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(4.0, 4.0);
        Point px = new Point(0.0, 4.0);
        System.out.println("p1: " +p1.toString()
        +"\np2: "+p2.toString());

        Segment sgm = new Segment();
        sgm.set(p1, p2);
        System.out.println(sgm.toString());

        Point simm = sgm.simmetric(px);
        System.out.println("px: " + px.toString());
        System.out.println("simm: " +simm.toString());
    }

}
