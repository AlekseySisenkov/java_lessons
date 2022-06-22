package ru.stqa.lessons.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PoinTests {


  @Test
  public void testArea1 () {

    Point p1 = new Point(1, -15);
    Point p2 = new Point(-6, 5);

    assert p1.distance(p2) == 21.18962010041709;
  }

  @Test
  public void testArea2 () {

    Point p3 = new Point(-1, 4);
    Point p4 = new Point(-4, 9);
    Assert.assertEquals(p3.distance(p4), 5.830951894845301);
  }

  @Test
  public void testArea3 () {

    Point p5 = new Point(34,2);
    Point p6 = new Point(-10,-6);
    Assert.assertEquals(p5.distance(p6),44.721359549995796);
  }
}
