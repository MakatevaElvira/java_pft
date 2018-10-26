package ru.stqa.pft.addressbook;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Elvira");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " +s.l +" = " + s.area());

    Rectangle r = new Rectangle(4,6);
    System.out.println("Площадь прямоугольника со сторонами " +r.a +" и " +r.b + " = " + r.area());



    Point p1 = new Point (3,5);

    Point p2 = new Point (7,11);

    double d = p1.distance (p2);
    System.out.println("Distance between p1(" +p1.x + "," + p1.y + ") and p2(" + p2.x + "," + p2.y + ") = " + d);




  }

  public static void hello ( String somebody) {

    System.out.println("Hello, "+ somebody + " ! ");
  }




}
