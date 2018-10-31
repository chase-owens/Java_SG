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

public class PassingTheTuringTest {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        String name, myName, favoriteColor, myFavoriteColor, favoriteFood, myFavoriteFood;
        double favoriteNumber, myFavoriteNumber, sum;
        
        myName = "Zeph";
        myFavoriteColor = "green";
        myFavoriteFood = "byte";
        
        myFavoriteNumber = 42;
        
        System.out.println("Hi there! Thanks for visiting me. What is your name");
        name = inputReader.nextLine();
        System.out.println("Nice to meet you " + name + ". My name is " + myName + ".");
        
        System.out.println("What's your favorite color? ");
        favoriteColor = inputReader.nextLine();
        System.out.println(favoriteColor + "?? Cool, my favorite color is " + myFavoriteColor);
        
        System.out.println("What about your favorite food? ");
        favoriteFood = inputReader.nextLine();
        System.out.println(favoriteFood + " sounds really good! I could take a " + myFavoriteFood + " out of anything!!");
        
        System.out.println("Tell me your favorite number. ");
        favoriteNumber = inputReader.nextDouble();
        sum = favoriteNumber + myFavoriteNumber;
        System.out.println(favoriteNumber + " is a cool number! My favorite number is " + myFavoriteNumber + ". " + favoriteNumber + " + " + myFavoriteNumber + " is " + sum + "! That's a cool number too!! What a combination we make!");
        
        System.out.println("Well, thanks for talking to me " + name + ".");
        
        
    }
}
