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

public class PickyEater {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("How many times has it been fried? (#)");
        int timesFried = inputReader.nextInt();
        
        System.out.println("Does it have any spinach in it? (y/n)");
        String hasSpinach = inputReader.next();
        
        System.out.println("Is it covered in cheese? (y/n)");
        String cheeseCovered = inputReader.next();
        
        System.out.println("How many pats of butter are on top? (#)");
        int butterPats = inputReader.nextInt();
        
        System.out.println("Is it covered in chocolate? (y/n)");
        String chocolateCovered = inputReader.next();
        
        System.out.println("Does it have a funny name? (y/n)");
        String funnyName = inputReader.next();
        
        System.out.println("Is it broccoli? (y/n)");
        String isBroccoli = inputReader.next();
        
        if (timesFried >= 4 || hasSpinach.equals("y") || cheeseCovered.equals("y") || butterPats >= 8 || chocolateCovered.equals("y") || funnyName.equals("y") || isBroccoli.equals("y")) {
            System.out.println("There is no way he'll eat that...");
        }
        
        inputReader.close();
        
    }
}
