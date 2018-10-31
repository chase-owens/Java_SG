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

public class GuessMe {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        int hiddenNumber = 12, numberGuessed;

        System.out.println("I've chosen a number. Betcha can't guess it!");
        numberGuessed = inputReader.nextInt();

        System.out.println("Your guess: " + numberGuessed);

        if (numberGuessed == hiddenNumber) {
            System.out.println("WOW, you guessed it!! The hidden number was, in fact, " + hiddenNumber + "!!");
        } else if (numberGuessed < hiddenNumber) {
            System.out.println("Nice try! But too low.. The hidden number was " + hiddenNumber + "!!");
        } else {
            System.out.println("Nice try! But too high.. The hidden number was " + hiddenNumber + "!!");
        }
        
        inputReader.close();

    }

}
