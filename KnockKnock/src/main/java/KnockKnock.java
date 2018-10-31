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

public class KnockKnock {
    public static void main(String[] args) {
        String nameGuessed;
        
        Scanner inputReader = new Scanner(System.in);
        
        
        System.out.println("Knock Knock! Guess who!!");
        nameGuessed = inputReader.nextLine();
        
        if (nameGuessed.equals("Marty McFly")) {
            System.out.println("Hey! That's right! I'm back!");
            System.out.println("...... from the Future.");
        } else {
            System.out.println("Dude, do I -look- like " + nameGuessed);
        }
        
        
        // == checks to see if pointers are the same
        if (nameGuessed == "Marty McFly") {
            System.out.println("Hey! That's right! I'm back!");
            System.out.println("...... from the Future.");
        } else {
            System.out.println("Dude, do I -look- like " + nameGuessed);
        }
        
        inputReader.close();
    }
}
