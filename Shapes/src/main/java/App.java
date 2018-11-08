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
        Square sq1 = new Square(5);
        Square sq2 = new Square(6);
        int areaOfsq1 = sq1.getArea(5, 4);
        System.out.println(areaOfsq1 + " " + sq2.getArea());
        
        EquilateralTriangle tr1 = new EquilateralTriangle(4);
        int areaOftr1 = tr1.getArea(4, 3);
        int perimeterOftr1 = tr1.getPerimeter();
        System.out.println("Triangle: \t" + areaOftr1 + " " + perimeterOftr1 + " " + tr1.getArea());
        
        Rectangle rect1 = new Rectangle(3, 4);
        int perimeterOfrect1 = rect1.getPerimeter(3, 4);
        int perimeterRect1alt = rect1.getPerimeter();
        int arect = rect1.getArea(3, 4);
        int arectalt = rect1.getArea();
        System.out.println(perimeterOfrect1 + " " + perimeterRect1alt);
        System.out.println(arect + " " + rect1.getPerimeter());
    }

}
