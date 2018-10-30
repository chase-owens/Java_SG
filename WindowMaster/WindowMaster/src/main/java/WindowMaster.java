/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
import java.util.Scanner;

public class WindowMaster {
    public static void main(String[] args) {
        // declare varaibles for height and width
    float height;
    float width;
    
    /* declare varaibles height and width input from console.
        Scanner/console reads Strings only. 
    */
    String stringHeight;
    String stringWidth;
    
    // declare other variables
    float areaOfWindow;
    float perimeterOfWindow;
    float cost;
    float areaCostPerFoot = 3.50f;
    float perimeterCostPerFoot = 2.25f;
    
    
    // declare scanner
    Scanner myScanner = new Scanner(System.in);
    
    // get input from user
    System.out.println("Please enter window height:");
    stringHeight = myScanner.nextLine();
    
    System.out.println("Please enter window width:");
    stringWidth = myScanner.nextLine();
    
    //convert strings to floats
    height = Float.parseFloat(stringHeight);
    width = Float.parseFloat(stringWidth);
    
    areaOfWindow = height * width;
    perimeterOfWindow = 2 * (height + width);
    
    //calculate cost of material 3.50 ft area, 2.25 ft perimeter
    cost = ((areaCostPerFoot * areaOfWindow) + (perimeterCostPerFoot * perimeterOfWindow));
    
    System.out.println("Window height = " + stringHeight);
    System.out.println("Window width = " + stringWidth);
    System.out.println("Widnow area = " + areaOfWindow);
    System.out.println("Window perimeter = " + perimeterOfWindow);
    System.out.println("Total Cost = " + cost);
    }
}
