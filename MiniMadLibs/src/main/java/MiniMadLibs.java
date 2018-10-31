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

public class MiniMadLibs {
    public static void main(String[] args) {
        // Declare scanner variable
        Scanner inputReader = new Scanner(System.in);
        
        //Declare other variables
        String noun1, adj1, noun2, adj2, pluralNoun1, pluralNoun2, pluralNoun3, presentTenseVerb, pastTenseVerb;
        int number;
        
        System.out.println("Please enter a noun: ");
        noun1 = inputReader.nextLine();
        
        System.out.println("Please enter an adjective: ");
        adj1 = inputReader.nextLine();
        
        System.out.println("Please enter another noun: ");
        noun2 = inputReader.nextLine();
        
        System.out.println("Please enter a whole number: ");
        number = inputReader.nextInt();
        
        System.out.println("Please enter another adjective: ");
        adj2 = inputReader.next();
        
        System.out.println("Please enter a plural noun: ");
        pluralNoun1 = inputReader.next();
        
        System.out.println("Please enter another plural noun: ");
        pluralNoun2 = inputReader.next();
        
        System.out.println("Please enter another plural noun: ");
        pluralNoun3 = inputReader.next();
        
        System.out.println("Please enter a verb in present tense: ");
        presentTenseVerb = inputReader.next();
        
        System.out.println("Please enter the past tense of the same verb: ");
        pastTenseVerb = inputReader.next();
        
        System.out.println(noun1 + ": the " + adj1 + " frontier. These are the voyages of the starship " + noun2 + ". Its " + number + "-year mission: to explore strange " + adj2 + " " + pluralNoun1 + ", to seek out " + adj2 + " " + pluralNoun2 + " and " + adj2 + " " + pluralNoun3 + ", to boldly " + presentTenseVerb + " where no one has " + pastTenseVerb + " before.");
        
        inputReader.close();
        
    }
}
