package ru.stqa.lessons.sandbox;

public class Triangle {
  public double a;
  public double b;
  public double c;

  public Triangle(double a, double b, double c){

    this.a = a;
    this.b = b;
    this.c = c;
  }

  public double per() {

    return this.a+this.b+this.c;
  }

  public double square() {
    return Math.sqrt((per()/2)*(per()-this.a)*(per()-this.b)*(per()-this.c));
  }

}
