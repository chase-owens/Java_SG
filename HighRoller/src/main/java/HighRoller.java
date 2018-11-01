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
import java.util.Random;

public class HighRoller {
    public static void main(String[] args) {
        Random diceRoll = new Random();
        Scanner userInput = new Scanner(System.in);
        
        int rollResult = diceRoll.nextInt(5) + 1;
        
        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("You rolled a " + rollResult);
        
        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        }
        
        System.out.println("How many sided dice do you want to roll?");
        int numOfSides = userInput.nextInt();
        rollResult = diceRoll.nextInt((numOfSides - 1) + 1);
        System.out.println("Let's give it a toss...");
        System.out.println("You rolled a " + rollResult);
        
        userInput.close();
    }
}
