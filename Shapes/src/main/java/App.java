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
        int areaOfsq1 = sq1.getArea(5, 4);
        System.out.println(sq1);
        
        EquilateralTriangle tr1 = new EquilateralTriangle(4);
        int areaOftr1 = tr1.getArea(4, 3);
        System.out.println(areaOftr1);
    }

}
