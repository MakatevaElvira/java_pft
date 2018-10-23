package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistance (){
    Point p1 = new Point(3,5);
    Point p2 = new Point(7,11);
    Assert.assertEquals(p1.distance(p2),7.211102550927978); ;
  };
  @Test
  public void testDistance2 (){
    Point p1 = new Point(3,5);
    Point p2 = new Point(7,11);
    assert p1.distance(p2) == 7.211102550927978 ;
  }
}
