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

public class TwoFoursAndTenYearsAgo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("What year would you like to count back from? ");
        int year = input.nextInt();
        
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " years ago would be " + (year - i));
        }
        
        System.out.println("\nI can count backward using a different way too...");
        
        for (int i = year; i >= year - 10; i--) {
            System.out.println((year - i) + " years ago would be " + i);
        }
    }
}
