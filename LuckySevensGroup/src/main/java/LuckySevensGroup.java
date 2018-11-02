/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author chaseowens
 */
public class LuckySevensGroup {

    public static void main(String[] args) {
        Scanner capture = new Scanner(System.in);
        Random randomizer = new Random();
        int bet = 0, $ = 0, max$ = bet, roll = 0, max$roll = 0;
        boolean betPlaced = false;

        do {
            System.out.println("Place your bet");
            if (capture.hasNextInt()) {
                bet = capture.nextInt();
                $ = bet;
                max$ = bet;
                betPlaced = true;
            } else {
                System.out.println("Your bet should be a number");
                capture.next();
            }

        } while (!betPlaced);

        while ($ > 0) {
            int dice1 = randomizer.nextInt(6) + 1;
            int dice2 = randomizer.nextInt(6) + 1;
            int sum = dice1 + dice2;
            roll += 1;

            if (sum == 7) {
                $ += 4;
                if (max$ < $) {
                    max$ = $;
                    max$roll = roll;
                }
            } else {
                $ -= 1;
            }
        }
        if (max$ == bet) {
            System.out.println("You never had more money than the bet you initially placed");
        } else {
            System.out.println("Your initial bet was " + bet);
            System.out.println("You were broke after " + roll + " rolls.");
            System.out.println("You should've quit after " + max$roll + "with $" + max$ + ".");
        }

    }
}
