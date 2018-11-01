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

public class StayPositive {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("What number should I count down from? ");
        
        int numberProvided = userInput.nextInt();
        int count = 1;
        
        while (numberProvided > -1) {
            if (count % 10 == 0) {
                System.out.println(numberProvided);
            } else {
                System.out.print(numberProvided + ", ");
            }
            numberProvided--;
            count++;
        }
    }
}
