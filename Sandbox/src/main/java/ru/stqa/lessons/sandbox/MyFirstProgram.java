package ru.stqa.lessons.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {

    hello("Aleksey");
    hello("hkf");
    hello("iuyot");

    double l =5;
    System.out.println("Площадь квадрата со стороной "+ l +" = "+ area(l));

    double a = 5;
    double b = 6;

    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));
  }

  public static void hello(String somebody) {

    System.out.println("Hello," +somebody+ "!");
  }

  public static double area(double len){

    return len * len;
  }

  public static double area(double a, double b){

    return a*b;
  }
}