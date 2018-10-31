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

public class YourLifeInMovies {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        String name;
        int yearBorn;

        System.out.println("Hey, let's play a game! What's your name?");
        name = inputReader.next();

        System.out.println("Ok, " + name + ", when were you born?");
        yearBorn = inputReader.nextInt();

        if (yearBorn < 1965) {
            System.out.println("Mash has been around for almost a century");
        } else if (yearBorn < 1975) {
            System.out.println("The original Jurassic Park release is closer to the lunar landing than today.");
        } else if (yearBorn < 1985) {
            System.out.println("Space Jam came out not last decade, but the one before THAT.");
        } else if (yearBorn < 1995) {
            System.out.println("The first Harry Potter came out over 15 years ago.");
        } else if (yearBorn < 2005) {
            System.out.println("Pixar's 'Up' came out half a decade ago.");
        } else {
            System.out.println("Can I get you a glass of milk?");
        }
        
        inputReader.close();
    }

}
