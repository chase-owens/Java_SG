/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author chaseowens
 */
public class Factorizer {

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        int num;
        int sum = 0;

        System.out.println("What number would you like to factor, (enter numbers only)?");
        num = myScanner.nextInt();
        System.out.println(num);
        
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
                System.out.println(i);
            }
        }
        
        if (num == sum) {
            System.out.println(num + " is a perfect number.");
        }
        
        if (sum == 1) {
            System.out.println(num + " is a prime number.");
        }
    }
}
