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

public class FieldDay {

    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("What's your last name? ");
        String name = userInput.next();
        
        if (name.compareTo("Baggins") < 0) {
            System.out.println("You're on the \"Red Dragons\"");
        } else if (name.compareTo("Dresden") < 0) {
            System.out.println("You're on the \"Dark Wizards\"");
        } else if (name.compareTo("Howl") < 0) {
            System.out.println("You're on the \"Moving Castles\"");
        } else if (name.compareTo("Potter") < 0) {
            System.out.println("You're on the \"Golden Snitches\"");
        } else if (name.compareTo("Vimes") < 0) {
            System.out.println("You're on the \"Night Guards\"");
        } else {
            System.out.println("You're on the \"Black Holes\"");
        }
        
        System.out.println("Good luck in the games!");
        userInput.close();
    }

}
