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

public class TraditionalFizzBuzz {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("How many units fizzing and buzzing do you need in your life? ");
        int number = input.nextInt();

        for (int i = 0; i < number + 1; i++) {
            if (i % 15 == 0) {
                System.out.println("fizzbuzz");
            } else if (i % 5 == 0) {
                System.out.println("buzz");
            } else if (i % 3 == 0) {
                System.out.println("fizz");
            } else {
                System.out.println(i);
            }

        }
        System.out.println("TRADITION!!!!!");

    }
}
