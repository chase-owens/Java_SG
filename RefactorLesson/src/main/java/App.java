/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
import java.util.Arrays;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        
        // Factorizor
        System.out.println("FaCtOrIzOr ApP");
        Scanner capture = new Scanner(System.in);
        int numberToFactor;
        
        System.out.println("What number do you want to factor?");
        numberToFactor = capture.nextInt();
        
        System.out.println("Factor count " +FactorizorObject.determineFactorCount(numberToFactor));
        System.out.println("is Prime: " + FactorizorObject.isPrime(numberToFactor));
        System.out.println("is Perfect: " + FactorizorObject.isPerfect(numberToFactor));
        System.out.println("Factor list: " + Arrays.toString(FactorizorObject.getFactorList(numberToFactor))+ "\n\n");
        
        // Add ACS art
        System.out.println("Interest Calculator");
        System.out.println("We are going to calculate ROI on your ivestment at different interest rates over different lengths of time with different amounts of principal invested.");
        System.out.println("Please enter the amount you wish to invest");
        double principal = capture.nextDouble();

        System.out.println("Please enter the annual interest rate quoted in percent");
        double annualInterest = capture.nextDouble();

        System.out.println("Please enter the number of years you wish to encrue your investment?");
        int totalYears = capture.nextInt();
        
        InterestCalculatorObject.calculateInterest(principal, annualInterest, totalYears);
        System.out.println("\n\n");
        
        //ACS art
        
        // Lucky Sevens
        System.out.println("Let's switch to something more fun");
        System.out.println("Let's play one of my favorite games!!");
        System.out.println("It's called Lucky Sevens");
        
        System.out.println("How much do you want to lose?");
        int bet = capture.nextInt();
        
        LuckySevensObject.playLuckySevens(bet);
        System.out.println("\n\n");
        
        // RPS
        System.out.println("Let's switch gears again!!");
        System.out.println("This time let's play Rock, Paper, Scissors");
        RPSObject.playRPS();
        
    }
}
