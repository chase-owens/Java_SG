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

public class TriviaNight {

    public static void main(String[] args) {
        int ans1, ans2, ans3, correctAnswers = 0;

        Scanner inputReader = new Scanner(System.in);

        System.out.println("It's trivia night are you ready?!");
        System.out.println("Enter the number of the best answer");

        System.out.println("First Question!");
        System.out.println("What is the lowest level of programming languages? ");
        System.out.print("1) Source Code");
        System.out.println("     2) Assembly Language");
        System.out.print("3) C#");
        System.out.println("     4) Machine Code");
        ans1 = inputReader.nextInt();

        System.out.println("Second Question!");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.print("1) Grace Hopper");
        System.out.println("     2) Alan Turing");
        System.out.print("3) Charles Babbage");
        System.out.println("     4) Larry Page");
        ans2 = inputReader.nextInt();

        System.out.println("Third Question!");
        System.out.println("Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.print("1) Serinity");
        System.out.println("     2) The Battlestar Galactica");
        System.out.print("3) The USS Enterprise");
        System.out.println("     4) The Millennium Falcon");
        ans3 = inputReader.nextInt();

        if (ans1 == 4) {
            correctAnswers += 1;
        }
        if (ans2 == 2) {
            correctAnswers += 1;
        }
        if (ans3 == 3) {
            correctAnswers += 1;
        }

        if (correctAnswers > 0) {
            System.out.println("Nice job - you got " + correctAnswers + " correct!");
        } else {
            System.out.println("Study a bit then come back and try again..");
        }

        inputReader.close();
    }
}
