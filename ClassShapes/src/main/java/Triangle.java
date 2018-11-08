/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class Triangle extends Shape {
    int numberOfSides = 3, sideLength;
    boolean hasSides = false;
    
    public Triangle(int sideLength, String color) {
        this.sideLength = sideLength;
        this.color = color;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public void setNumberOfSides(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
    
    
    
    @Override
    // Treat like an equilateral
    public int getArea(int sideLength, int numberOfSides) {
        return (int) ((sideLength ^ 2) * (Math.sqrt(numberOfSides)/4));
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
}
