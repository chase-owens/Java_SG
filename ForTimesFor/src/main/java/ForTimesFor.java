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

public class ForTimesFor {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Which times table shall we look at?");
        int num = input.nextInt();
        
        for (int i = 1; i < 16; i++) {
            int sum = num * i;
            System.out.println(i + " * " + num + " is:");
            int bestGuess = input.nextInt();
            
            if (sum == bestGuess) {
                System.out.println("Correct!");
            } else {
                System.out.println("Sorry no, the answer is " + sum);
            }
        }
    }
}
