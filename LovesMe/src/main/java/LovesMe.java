/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class LovesMe {
    public static void main(String[] args) {
        int numberOfPetals = 34;
        
        while (numberOfPetals > 0) {
            if (numberOfPetals % 2 == 1) {
                System.out.println("It LOVES me!");
            } else {
                System.out.println("It LOVES me NOT!");
            }
            numberOfPetals--;
        }
        System.out.println("Treat IT right!");
    }
}
