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

public class QuestForUserInput {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        String yourName, yourQuest;
        double velocityOfSwallow;
        
        System.out.println("What is your name? ");
        yourName = inputReader.nextLine();
        
        System.out.println("What is your qeust?! ");
        yourQuest = inputReader.nextLine();
        
        System.out.println("What is the airspeed velocity of an unladen swallow? ");
        velocityOfSwallow = inputReader.nextDouble();
        
        System.out.println("How do you know " + velocityOfSwallow + " is correct " + yourName);
        System.out.println("when you didn't even know if the swallow was African or European?");
        System.out.println("Maybe skip answering things about birds and instead go " + yourQuest + ".");
        
        inputReader.close();
    }
}
