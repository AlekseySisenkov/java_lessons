package ru.stqa.lessons.sandbox;

public class Auto_point {

  public static void main(String[] args) {

    Point p1 = new Point(1,-15);
    Point p2 = new Point(-6,5);
    System.out.println("Расстояние между точками p1 и p2 = " + distance(p1, p2));

    Point p = new Point(3,-1);
    System.out.println("Расстояние между точками = " + p.area());
  }

  public static double distance(Point p1, Point p2) {

    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }
  }