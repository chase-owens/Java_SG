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
public class LuckySevensObject {
    static Random randomizer = new Random();
    
    public static void playLuckySevens(int bet) {
        int roll = 0, $ = bet, max$ = $, max$roll = 0, dice1, dice2, diceTotal;

        while ($ > 0) {
            dice1 = randomizer.nextInt(6) + 1;
            dice2 = randomizer.nextInt(6) + 1;
            diceTotal = dice1 + dice2;
            roll += 1;

            if (diceTotal == 7) {
                $ += 4;
                if (max$ < $) {
                    max$ = $;
                    max$roll = roll;
                }
            } else {
                $ -= 1;
            }
        }
        System.out.println("It took you " + roll + " rolls to go broke");
        System.out.println("You should've quit on roll " + max$roll + " when you had $" + max$ + ".");
    }
    
    
}
