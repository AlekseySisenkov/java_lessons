package ru.stqa.lessons.sandbox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
  public TriangleTests() {
  }

  @Test
  public void testPer() {
    Triangle t = new Triangle(5.0, 4.0, 7.0);

    assert t.per() == 16.0;

  }

  @Test
  public void testSquare() {
    Triangle t = new Triangle(14.0, 14.0, 3.0);
    Assertions.assertEquals(t.square(), 354.15533315199417);
  }

  @Test
  public void testSideNegative(){
    try {
      new Triangle(-5.0, 4.0, 7.0);
      Assertions.fail();
    } catch (IllegalArgumentException exception){

    }
  }

  @Test
  public void testInequalityViolated(){
    try {
      new Triangle(2.0, 4.0, 7.0);
      Assertions.fail();
    } catch (IllegalArgumentException exception){

    }
  }

  @Test
  public void testTriangle(){
    Triangle t1 = new Triangle(10.0, 4.0, 7.0);
    Triangle t2 = new Triangle(4.0, 7.0, 10.0);
    Assertions.assertEquals(t1, t2);
  }
}
