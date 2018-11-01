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

public class MiniZork {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("with a boarded front door.");
        System.out.println("There is a small maibox here.");
        System.out.print("Go to the house, or open the mailbox? ");

        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox. ");
            System.out.println("It's really dark in there. ");
            System.out.println("Do you look inside or stick your hand in? ");
            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox. ");
                System.out.println("It's really very dark. So ... so very dark");
                System.out.println("Do you run away or keep looking? ");
                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea. ");
                    System.out.println("You've been eaten by a grue.");
                } else {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you are alive. Possibly a wise choice.");
                }
            } else if (action.equals("stick your hand in")) {
                System.out.println("That was a very bad decision.");
                System.out.println("A troll grabbed your hand and pulled you in");
                System.out.println("You were never to be seen again.");
            }
        } else {
            System.out.println("Your friends laughed at you for going home early.");
            System.out.println("But you are safe and sound.");
            System.out.println("Who kows what you missed...");
        }

    }

}
