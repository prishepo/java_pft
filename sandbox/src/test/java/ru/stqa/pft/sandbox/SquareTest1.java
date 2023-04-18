package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTest1 {
    @Test
    public void testAres() {
        Square1 s = new Square1(5);
        Assert.assertEquals(s.area(), 25.0);
    }
}
