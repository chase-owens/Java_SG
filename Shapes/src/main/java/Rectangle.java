/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class Rectangle extends Quadralateral {
    boolean parallelSidesAreDifferentlengths = true;
    int length, width;
    
    public Rectangle(int length, int width) {
        
    }
    
    @Override
    public int getArea(int length, int width) {
        return length * width;
    }
    
    @Override
    public int getPerimeter(int length, int width) {
        return (2 * length) + (2 * width);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
}
