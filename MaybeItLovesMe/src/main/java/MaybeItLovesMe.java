/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */

import java.util.Random;
public class MaybeItLovesMe {
    public static void main(String[] args) {
        Random randomizer = new Random();
        
        int numberOfPetals = randomizer.nextInt(76) + 13;
        boolean lovesMe;
        
        while (numberOfPetals > 0) {
            if (numberOfPetals % 2 == 1) {
                System.out.println("It loves me!");
                lovesMe = true;
            } else {
                System.out.println("It loves me not!");
                lovesMe = false;
            }
            numberOfPetals--;
        }
        if (lovesMe = true) {
            System.out.println("It really LOVES me!");
        } else {
            System.out.println("Awwwww, bummer");
        }
        
    }
}
