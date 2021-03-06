/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class EquilateralTriangle extends Triangle {

    boolean allSidesAreEqualLength = true;
    int sideLength;

    public EquilateralTriangle(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public int getArea(int sideLength, int numberOfSides) {
        return (int) ((sideLength ^ 2) * (Math.sqrt(numberOfSides)/4));
        // (int) ((((Math.sqrt(3))) / 4) * (sideLength ^ 2));
    }

    public int getArea() {
        return (int) ((this.sideLength ^ 2) * (Math.sqrt(this.numberOfSides)/4));
    }

    @Override
    public int getPerimeter(int sideLength, int numberOfSides) {
        return sideLength * numberOfSides;
    }

    public int getPerimeter() {
        return this.sideLength * this.numberOfSides;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
}
