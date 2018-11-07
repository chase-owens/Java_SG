/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
abstract class Shape {
    boolean closedFigure = true, hasArea = true, hasPerimeter = true;
    String color;
    
    public int getArea(int a, int b) {
        return a * b;
    }
    
    public int getPerimeter(int a, int b) {
        return a + b;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
