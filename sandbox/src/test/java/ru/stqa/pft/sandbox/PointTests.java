package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {


    @Test
    public void testDistanceUncorrect() {
        Point p = new Point(10, 5, 15, 30);
        Assert.assertEquals(p.distance(), 15);
    }

    @Test
    public void testDistanceCorret() {
        Point p = new Point(10, 5, 15, 30);
        Assert.assertEquals(p.distance(), 25.495097567963924);
    }

    @Test
    public void testPoint1Coord() {
        Point p = new Point(10, 5, 15, 30);
        Assert.assertEquals(p.x, 10);
        Assert.assertEquals(p.y, 5);
    }

    @Test
    public void testPoint2Coord() {
        Point p = new Point(10, 5, 15, 30);
        Assert.assertEquals(p.x1, 15);
        Assert.assertEquals(p.y1, 30);
    }


}
