/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class Circle extends Shape {
    int diameter;
    boolean hasSides = false;
    
    
    public Circle(int diameter, String color) {
        this.diameter = diameter;
        this.color = color;
    }
    
    @Override
    public int getArea(int diameter, int pi) {
        return (int) (diameter * Math.PI);
    }
    
    public int getArea() {
        return (int) (this.diameter * Math.PI);
    }
    
    @Override
    public int getPerimeter(int diameter, int pi) {
        return ((diameter/2) * pi * 2);
    }
    
    public int getPerimeter() {
        return (int) ((this.diameter/2) * Math.PI * 2);
    }
}
