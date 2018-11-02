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

public class Factorizer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("What number do you want to factor?");
        int num = input.nextInt();
        System.out.println("Okay we'll factor " + num + " and check if it's a prime or a perfect number.");
        int factorSum = 0;
        int factorCount = 0;

        for (int i = 1; i < num; i++) {
            System.out.println(i);
            int remainder = (i % num); // why is this == i?
            System.out.println(remainder);
            if (i % num == 0) {
                System.out.println(i + " is a factor of " + num);
                factorSum += i;
                factorCount += 1;
            }

        }

        if (factorSum == 1) {
            System.out.println(num + " is a prime number. There is only " + factorCount + " factor");
        }
        if (factorSum == num) {
            System.out.println(num + " is a perfect number. There are " + factorCount + " factors.");
        }

    }
}
