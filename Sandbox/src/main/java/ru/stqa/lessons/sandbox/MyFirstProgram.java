package ru.stqa.lessons.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {

    hello("Aleksey");
    hello("hkf");
    hello("iuyot");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(5, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(3,5);
    Point p2 = new Point(-4,10);
    System.out.println("Расстояние между точками p1 и p2 = " + distance(p1, p2));
  }

  public static void hello(String somebody) {

    System.out.println("Hello," + somebody + "!");
  }

  public static double distance(Point p1, Point p2){

    return Math.sqrt((p2.x- p1.x)*(p2.x- p1.x)+(p2.y- p1.y)*(p2.y- p1.y));
  }
}