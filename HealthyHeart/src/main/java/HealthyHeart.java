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

public class HealthyHeart {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        int age, maximumHeartRate;
        double targetHeartRateFloor, targetHeartRateCeiling;
        
        System.out.println("What is your age? ");
        age = inputReader.nextInt();
        maximumHeartRate = 220 - age;
        System.out.println("Your maximum heart rate should be " + maximumHeartRate + " beats per minute.");
        
        targetHeartRateFloor = maximumHeartRate * 0.5;
        targetHeartRateCeiling = maximumHeartRate * 0.85;
        System.out.println("Your target HR Zone is " + targetHeartRateFloor + " - " + targetHeartRateCeiling + " beats per minute.");
        
        inputReader.close();
    }
}
