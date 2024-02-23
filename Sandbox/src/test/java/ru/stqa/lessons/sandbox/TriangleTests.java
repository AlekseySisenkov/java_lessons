package ru.stqa.lessons.sandbox;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
  public TriangleTests() {
  }

  @Test
  public void testPer() {
    Triangle t1 = new Triangle(5.0, 4.0, 7.0);

    assert t1.per() == 16.0;

  }

  @Test
  public void testSquare() {
    Triangle t2 = new Triangle(14.0, 14.0, 3.0);
    Assertions.assertEquals(t2.square(), 354.15533315199417);
  }
}
