package ru.stqa.pft.sandbox;

import java.io.PipedOutputStream;

public class MyFirstProgram {
    public static void main(String[] args) {

        Point p1 = new Point(10,5);
        Point p2 = new Point(15,30);

        System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2));


    }

    public static double distance(Point p1,Point p2) {
        return Math.sqrt((Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2)));
    }


}