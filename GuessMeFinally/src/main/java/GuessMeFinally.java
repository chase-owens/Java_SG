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

public class GuessMeFinally {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random randomizer = new Random();
        
        int hiddenNumber = randomizer.nextInt(200) - 100;
        
        System.out.println("I've chosen a number between -100 and 100. Betcha can't guess it!: ");
        int numberGuessed = input.nextInt();
        if (numberGuessed == hiddenNumber) {
            System.out.println("WOW, Nice guess!");
            System.out.println("The number I was thinking of was " + hiddenNumber);
        }
        
        while (numberGuessed != hiddenNumber) {
            if (numberGuessed < hiddenNumber) {
                System.out.println("Too low, try again!:");
                numberGuessed = input.nextInt();
            } else if (numberGuessed > hiddenNumber) {
                System.out.println("Too high, try again!:");
                numberGuessed = input.nextInt();
            }
        }
        
        if (numberGuessed == hiddenNumber) {
            System.out.println("Finally, it's about time you got it!");
        }
        
    }
}
