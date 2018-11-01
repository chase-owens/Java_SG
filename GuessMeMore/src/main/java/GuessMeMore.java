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

public class GuessMeMore {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Random randomizer = new Random();
        
        int hiddenNumber = randomizer.nextInt(200) - 100;
        int numberGuessed = 102;
        
        while (hiddenNumber != numberGuessed) {
            System.out.println("Guess a whole number between -100 and 100: ");
            numberGuessed = userInput.nextInt();
            
            if (numberGuessed < hiddenNumber) {
                System.out.println("Too low, try again: ");
            } else {
                System.out.println("Too high, try again: ");
            }
        }
        
        if (numberGuessed == hiddenNumber) {
            System.out.println("You got it! The hidden number is " + hiddenNumber);
        }
    }
}
