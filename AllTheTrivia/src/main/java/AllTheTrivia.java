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

public class AllTheTrivia {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        String answer1, answer2, answer3, answer4;

        System.out.println("1,024 Gigabytes is equal to what? ");
        answer1 = inputReader.nextLine();

        System.out.println("What is the only planet in our solar system to spin counter clockwise? ");
        answer2 = inputReader.nextLine();

        System.out.println("Which planet in ur solar system hosts the biggest volcano ever discovered? ");
        answer3 = inputReader.nextLine();

        System.out.println("What is the most abundant element in Earth's atmosphere? ");
        answer4 = inputReader.nextLine();

        System.out.println("Wow, 1024 Gigabytes is a " + answer4);
        System.out.println(
                "I didn't know that the largest volcano ever discovered in our solar system was on " + answer2);
        System.out.println("That's amazing that " + answer1 + " is the most abundant element in the atmosphere...");
        System.out.println(answer3 + " is the only planet that rotates clockwise, neat!");

        inputReader.close();
    }
}
