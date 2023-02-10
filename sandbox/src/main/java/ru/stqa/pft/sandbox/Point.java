package ru.stqa.pft.sandbox;

public class Point {

    public double x;
    public double y;
    public double x1;
    public double y1;

    public Point (double x, double y, double x1, double y1){
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }


    public double distance() {
        return Math.sqrt((Math.pow((this.x1 - this.x), 2) + Math.pow((this.y1 - this.y), 2)));
    }

}
