/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class Square extends Shape{
    int numberOfSides = 4;
    int length, width;
    
    
    public Square (int length, String color) {
        this.length = length;
        this.width = length;
        this.color = color;
    }
    
    @Override
    public int getArea(int length, int width) {
        return length * width;
    }
    
    public int getArea() {
        return (this.length) * (this.width);
    }
    
    @Override
    public int getPerimeter(int length, int width) {
        return (2 * length) + (2 * width);
    }
    
    public int getPerimeter() {
        return (2 * this.length) + (2 * this.width);
    }
}
