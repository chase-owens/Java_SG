/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class Square extends Quadralateral {
    boolean allSidesAreEqual = true;
    int sideLength;
    
    public Square(int sideLength) {
        this.sideLength = sideLength;
    }
    
    @Override
    public int getArea(int sideLength, int numberOfSides) {
        return this.sideLength * this.sideLength;
    }
    
    public int getArea() {
        return this.sideLength * this.sideLength;
    }
    
    @Override
    public int getPerimeter(int sideLength, int numberOfSides) {
        return sideLength * numberOfSides;
    }
    
    public int getPerimeter() {
        return (sideLength + sideLength) * 2;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
    
}