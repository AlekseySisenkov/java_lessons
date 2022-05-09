package ru.stqa.lessons.sandbox;

public class Point {

  public double x;
  public double y;

  public double b1 = 9;
  public double b2 = -7;
  public Point(double x, double y){

    this.x = x;
    this.y = y;
  }

  public double area() {

    return Math.sqrt((b1- this.x)*(b1- this.x)+(b2- this.y)*(b2- this.y));
  }

}
