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

public class DontForgetToStoreIt {
    public static void main(String[] args) {
        int meaningOfLifeAndEverything = 42;
        final double PI = 3.14159;
        double pi;
        String cheese, color;

        Scanner inputReader = new Scanner(System.in);

        System.out.println("Give me pi to at least 5 decimals: ");
        pi = inputReader.nextDouble();

        System.out.println("What is the meaning of life, the universe and everything? ");
        meaningOfLifeAndEverything = inputReader.nextInt();

        System.out.println("What is your favorite kind of cheese? ");
        cheese = inputReader.next();

        System.out.println("Do you like the color red or blue more? ");
        color = inputReader.next();

        System.out.println("Ooh, " + color + " " + cheese + " sounds delicous!");
        System.out.println("The circumference of life is " + (2 * pi * meaningOfLifeAndEverything));

        inputReader.close();
    }
}
