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

public class CoinFlipper {
    public static void main(String[] args) {
        Random randomizer = new Random();
        
        System.out.println("Ready, Set, Flip...!!");
        boolean coinToss = randomizer.nextBoolean();
        
        if (coinToss == true) {
            System.out.println("You got TAILS");
        } else {
            System.out.println("You got HEADS");
        }
    }
}
