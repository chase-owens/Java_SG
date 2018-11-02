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

public class InterestCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("We are going to calculate ROI on your ivestment at different interest rates over different lengths of time with different amounts of principal invested.");
        System.out.println("Please enter the amount you wish to invest");
        double principal = input.nextDouble();

        System.out.println("Please enter the annual interest rate quoted in percent");
        double annualInterest = input.nextDouble();

        System.out.println("Please enter the number of years you wish to encrue your investment?");
        int totalYears = input.nextInt();

        if (principal <= 0 || annualInterest <= 0 || totalYears < 1) {
            System.out.println("Your principle after " + totalYears + "years at " + annualInterest + "interest rate for will be " + principal);
        } else {
            double principalYearBegan = principal;
            int yearsPassed = 0;

            for (int month = 1; month < ((totalYears * 12) + 1); month++) {
                if (month % 3 == 0) {
                    double quarterlyInterestEarned = principal * (1 + ((annualInterest/4) / 100));
                    principal += quarterlyInterestEarned;
                }
                if (month % 12 == 0) {
                    yearsPassed += 1;
                    System.out.println("Year: " + yearsPassed + "    Principal year began: " + principalYearBegan + "    Annual Interest Encrued: " + (principal - principalYearBegan) + "    Principal at end of year: " + principal );
                    principalYearBegan = principal;
                }
            }
        }

    }
}
