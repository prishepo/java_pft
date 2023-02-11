package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {


    @Test
    public void testDistanceIncorrect() {
        Point p1 = new Point(10, 5);
        Point p2 = new Point(15,30);
        Assert.assertEquals(p1.distance(p2), 15);

    }

    @Test
    public void testDistanceCorret() {
        Point p1 = new Point(10, 5);
        Point p2 = new Point(15,30);
        Assert.assertEquals(p1.distance(p2), 25.495097567963924);
    }

    @Test
    public void testPoint1Coord() {
        Point p = new Point(10, 5);
        Assert.assertEquals(p.x, 10);
        Assert.assertEquals(p.y, 5);
    }

    @Test
    public void testPoint2Coord() {
        Point p = new Point(15, 30);
        Assert.assertEquals(p.x, 15);
        Assert.assertEquals(p.y, 30);
    }


}
