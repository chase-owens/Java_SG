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

public class BiggerBetterAdder {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        
        Double sum, operand1, operand2;
        
        System.out.println("Give me a number: ");
        operand1 = inputReader.nextDouble();
        
        System.out.println("Give me another number: ");
        operand2 = inputReader.nextDouble();
        
        sum = operand1 + operand2;
        System.out.println(operand1 + " + " + operand2 + " = " + sum);
        
        inputReader.close();
        
    }
}
