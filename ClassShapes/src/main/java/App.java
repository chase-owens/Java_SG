/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class App {
    public static void main(String[] args) {
        Square sq1 = new Square(4, "Blue");
        int sq1Area = sq1.getArea(4, 4);
        int sq1AreaAlt = sq1.getArea();
        System.out.println("Square Super Area: " + sq1Area + "\tOverloaded Area: " + sq1AreaAlt);
        
        Rectangle rect1 = new Rectangle(4, 3, "Orange");
        int rect1Area = rect1.getArea(4, 3);
        int rect1AreaAlt = rect1.getArea();
        System.out.println("Rectangle Super Area: " + rect1Area + "\tOverloaded Area: " + rect1AreaAlt);
        
        Triangle tri1 = new Triangle(3, "Yellow");
        int tri1Area = tri1.getArea(3, 3);
        int tri1AreaAlt = tri1.getArea();
        System.out.println("Triangle Super Area: " + tri1Area + "\tOverloaded Area: " + tri1AreaAlt);
        System.out.println(tri1.getPerimeter(3, 3) + "\t" + tri1.getPerimeter());
        
        Circle c1 = new Circle(4, "Green");
        int c1Area = c1.getArea(4, 3);
        int c1AreaAlt = c1.getArea();
        System.out.println("Circle Super Area: " + c1Area + "\tOverloaded Area: " + c1AreaAlt);
    }
}
