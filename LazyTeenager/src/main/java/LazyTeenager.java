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
public class LazyTeenager {
    public static void main(String[] args) {
        Random randomizer = new Random();
        int timesToldToCleanRoom = 0;
        int liklihoodOfCleaningRoom = 0;
        boolean roomCleaned = false;
        
        do {
            int chance = randomizer.nextInt(100);
            System.out.println("Clean your room Bryson");
            liklihoodOfCleaningRoom += 5;
            timesToldToCleanRoom += 1;
            
            if (chance <= liklihoodOfCleaningRoom) {
                System.out.println("FINE I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS");
                roomCleaned = true;
            }
            
            if (timesToldToCleanRoom == 15) {
                System.out.println("You're grounded");
                break;
            }
        } while (roomCleaned == false);
    }
}
