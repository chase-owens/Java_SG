/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class Circle extends Elipses {
    boolean canRotate = false;
    boolean hasTrueCenter = true;
    int diameter;
    int pi = (int) Math.round(Math.PI);
    
    public Circle() {
        
    }
    
    @Override
    public int getArea(int diameter, int pi) {
        return (pi * (diameter/2)^2);
    }
    
    @Override
    public int getPerimeter(int diameter, int pi) {
        return ((diameter/2) * pi * 2);
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }
    
}
