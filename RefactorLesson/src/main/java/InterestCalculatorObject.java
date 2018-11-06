/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */

import java.text.DecimalFormat;

public class InterestCalculatorObject {
    
    public static void calculateInterest(double principal, double annualInterest, int totalYears) {
        double initialInvestment = principal;
        double rSum = 0;
        DecimalFormat df = new DecimalFormat(".##");
        if (principal <= 0 || annualInterest <= 0 || totalYears < 1) {
            System.out.println("Your principle after " + totalYears + "years at " + annualInterest + "interest rate for will be " + principal);
        } else {
            double principalYearBegan = principal;
            int yearsPassed = 0;

            for (int month = 1; month < ((totalYears * 12) + 1); month++) {
                if (month % 3 == 0) {
                    double newBalance = principal * (1 + ((annualInterest/4) / 100));
                    rSum += newBalance - principal;
                    principal = newBalance;
                    
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
        System.out.println("Your initial investment of $" + initialInvestment + " turned into $" + df.format(principal));
    }
}
