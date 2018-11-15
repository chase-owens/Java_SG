/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class InterestCalcBigD {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BigDecimal rSum = new BigDecimal("0");
        BigDecimal four = new BigDecimal("4");
        BigDecimal hundred = new BigDecimal("100");
        BigDecimal one = BigDecimal.ONE;

        System.out.println("We are going to calculate ROI on your ivestment at different interest rates over different lengths of time with different amounts of principal invested.");
        System.out.println("Please enter the amount you wish to invest");
        BigDecimal principal = new BigDecimal(input.nextLine());
        BigDecimal principalFormatted = principal.setScale(2, RoundingMode.HALF_UP);

        System.out.println("Please enter the annual interest rate quoted in percent");
        BigDecimal annualInterest = new BigDecimal(input.nextDouble());
        BigDecimal annualInterestFormatted = annualInterest.setScale(2, RoundingMode.HALF_UP);

        System.out.println("Please enter the number of years you wish to encrue your investment?");
        int totalYears = input.nextInt();

        if (principal.signum() <= 0 || annualInterest.signum() <= 0 || totalYears < 1) {
            System.out.println("Your principle after " + totalYears + "years at " + annualInterestFormatted.toString() + "interest rate for will be " + principalFormatted.toString());
        } else {
            BigDecimal principalYearBegan = principal;
            int yearsPassed = 0;
            BigDecimal quarterlyInterest = annualInterest.divide(four);
            BigDecimal quarterlyInterestAsDecimal = quarterlyInterest.divide(hundred);
            BigDecimal quarterlyInterestFormula = one.add(quarterlyInterestAsDecimal);

            for (int month = 1; month < ((totalYears * 12) + 1); month++) {
                if (month % 3 == 0) {
                    BigDecimal newBalance = principal.multiply(quarterlyInterestFormula);
                    principal = newBalance;
                    rSum = rSum.add(newBalance);
                }
                if (month % 12 == 0) {
                    BigDecimal interestEncrued = principal.subtract(principalYearBegan);
                    BigDecimal rSumFormatted = rSum.setScale(2, RoundingMode.HALF_UP);
                    BigDecimal interestEncruedFormatted = interestEncrued.setScale(2, RoundingMode.HALF_UP);
                    yearsPassed += 1;
                    principalFormatted = principal.setScale(2, RoundingMode.HALF_UP);
                    BigDecimal principalYearBeganFormatted = principalYearBegan.setScale(2, RoundingMode.HALF_UP);
                    System.out.println("Year: " + yearsPassed + "    Principal year began: " + principalYearBeganFormatted.toString() + "    Annual Interest Encrued: " + interestEncruedFormatted.toString() + "    Principal at end of year: " + principalFormatted.toString());
                    System.out.println("Interest accumulated this year: " + rSumFormatted.toString());
                    rSum = BigDecimal.ZERO;
                    principalYearBegan = principal;
                }
            }
        }
    }
}
