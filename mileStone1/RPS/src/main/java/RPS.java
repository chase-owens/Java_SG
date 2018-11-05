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
import java.util.Random;

public class RPS {

    public static void main(String[] args) {
        Scanner capture = new Scanner(System.in);
        Random randomizer = new Random();
        boolean hasEnteredValidNumber = false, keepPlaying = true;
        int rounds = 0, tie = 0, userWins = 0, computerWins = 0, compNumber = 0;
        String userPick, computerPick;

        do {

            // Check to make sure number b/t 1-10 was entered
            do {
                System.out.println("Let's play Rock Paper Siccors!!");
                System.out
                        .println("How many rounds do you want to play? Please only select something between 1 and 10");
                if (capture.hasNextInt()) {
                    rounds = capture.nextInt();
                    if (rounds >= 0 && rounds <= 10) {
                        hasEnteredValidNumber = true;
                    }
                } else {
                    System.out.println("That is not a number b/t 1 & 10!");
                    capture.next();
                }

            } while (!hasEnteredValidNumber);

            // Play game
            for (int i = 1; i < (rounds + 1); i++) {
                System.out.println("Pick: rock, paper, or scissors");
                userPick = capture.next().toLowerCase();
                System.out.print("Round " + i + "    You picked " + userPick);

                compNumber = randomizer.nextInt(3);
                if (compNumber == 0) {
                    computerPick = "rock";
                } else if (compNumber == 1) {
                    computerPick = "paper";
                } else {
                    computerPick = "scissors";
                }
                System.out.println("    The computer picks " + computerPick);

                // Calculate match
                if ((userPick.equals("rock") && computerPick.equals("scissors"))
                        || (userPick.equals("paper") && computerPick.equals("rock"))
                        || (userPick.equals("scissors") && computerPick.equals("paper"))) {
                    userWins += 1;
                    System.out.println("You Win!");
                } else if (userPick.equals(computerPick)) {
                    tie += 1;
                    System.out.println("Tie!");
                } else {
                    computerWins += 1;
                    System.out.println("Sorry, you loose");
                }
            }

            // Calculate who wins, print game data
            System.out.println("User wins: " + userWins + "Computer wins: " + computerWins + "Ties: " + tie);
            if (userWins > computerWins) {
                System.out.println("You win!!");
            } else if (userWins == computerWins) {
                System.out.println("Kiss your sister, It's a tie");
            } else {
                System.out.println("You lose..");
            }

            // Check if user wants to continue
            System.out.println("Keep Playing? (y/n)");
            String play = capture.next();

            if (play.equals("n")) {
                keepPlaying = false;
                hasEnteredValidNumber = false;
                rounds = 0;
                userWins = 0;
                computerWins = 0;
                tie = 0;
            }

        } while (keepPlaying == true);

    }

}
