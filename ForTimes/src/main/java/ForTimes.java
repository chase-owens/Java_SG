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

public class ForTimes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Which times table shall I write?");
        int num = input.nextInt();
        
        for (int i = 15; i > 0; i--) {
            int sum = num * i;
            System.out.println(num + " * " + i + " = " + sum);
        }
    }
}
