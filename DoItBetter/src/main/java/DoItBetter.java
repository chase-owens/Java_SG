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

public class DoItBetter {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        double milesCanRun, hotdogsCanEat, languagesKnown;
        
        System.out.println("How many miles can you run? ");
        milesCanRun = inputReader.nextDouble();
        double milesICanRun = milesCanRun + 1;
        System.out.println("Nice, while you can run " + milesCanRun + " miles. I can run " + milesICanRun + " miles!");
        
        System.out.println("How many hot dogs can you eat? ");
        hotdogsCanEat = inputReader.nextDouble();
        double hotdogsICanEat = hotdogsCanEat + 1;
        System.out.println(hotdogsCanEat + "? Well you're no Joey Chestnut... I can eat " + hotdogsICanEat + " dogs.");
        
        System.out.println("How many languages do you know? ");
        languagesKnown = inputReader.nextDouble();
        double languagesIKnow = languagesKnown + 1;
        System.out.println(languagesKnown + "?! That's how many I knew when I was 15!! I know " + languagesIKnow + " languages!");
    }
}
