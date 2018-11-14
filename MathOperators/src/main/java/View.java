
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
class View {
    Scanner read = new Scanner(System.in);

    int displayMenu() {
        System.out.println("Which operand do you want to use?");
        System.out.println("1) Plus \t2) Minus \n3) Multiple \t4) Divide");
        return read.nextInt();
    }

    int[] getOperands(String operand) {
        int[] operands = new int[2];
        System.out.println("What numbers do you want to " + operand);
        System.out.println("Please enter one at a time");
        operands[0] = read.nextInt();
        operands[1] = read.nextInt();
        return operands;
    }

    void print(int answer) {
        System.out.println("The answer is " + answer);
    }
    
    
}
