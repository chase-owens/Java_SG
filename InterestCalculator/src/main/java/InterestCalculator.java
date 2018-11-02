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
import java.text.DecimalFormat;

public class InterestCalculator {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double rSum = 0;
        DecimalFormat df = new DecimalFormat(".##");
        

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
                    double newBalance = principal * (1 + ((annualInterest/4) / 100));
                    principal = newBalance;
                    rSum += newBalance;
                }
                if (month % 12 == 0) {
                    yearsPassed += 1;
                    System.out.println("Year: " + yearsPassed + "    Principal year began: " + df.format(principalYearBegan) + "    Annual Interest Encrued: " + df.format(principal - principalYearBegan) + "    Principal at end of year: " + df.format(principal));
                    System.out.println("Interest accumulated this year: " + df.format(rSum));
                    rSum = 0;
                    principalYearBegan = principal;
                }
            }
        }

    }
}
