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

public class SimpleCalculator {
    static Scanner capture = new Scanner(System.in);
    public  void run() {
        boolean keepGoing = true;
        System.out.println("Do you want to do some addition, subtraction, multiplication, division, or do you suffer from Math Phobia?");
        String selection = capture.next();

        do {
            switch (selection) {
                case "addition":
                    System.out.println("What two numbers do you want to add?");
                    System.out.println("Enter one at a time...");
                    int num1 = capture.nextInt();
                    int num2 = capture.nextInt();
                    System.out.println(SimpleCalculator.adder(num1, num2));
                    break;
                case "substraction":
                    System.out.println("What two numbers do you want to subtract");
                    System.out.println("Enter one at a time...");
                    int a = capture.nextInt();
                    int b = capture.nextInt();
                    System.out.println(SimpleCalculator.subtractor(a, b));
                    break;
                case "multiplication":
                    System.out.println("What two numbers do you want to multiply");
                    System.out.println("Enter one at a time...");
                    int x = capture.nextInt();
                    int y = capture.nextInt();
                    System.out.println(SimpleCalculator.multiplier(x, y));
                    break;
                case "division":
                    System.out.println("What two numbers do you want to divide");
                    System.out.println("Enter one at a time...");
                    int operand1 = capture.nextInt();
                    int operand2 = capture.nextInt();
                    System.out.println(SimpleCalculator.divider(operand1, operand2));
                    break;
                default:
                    System.out.println("Ok bye!");
                    keepGoing = false;
                    break;
            }
        } while (keepGoing == true);
    }
    public static int adder(int num1, int num2) {
        return num1 + num2;
    }
    
    public static int subtractor(int num1, int num2) {
        return num1 - num2;
    }
    
    public static int multiplier(int num1, int num2) {
        return num1 * num2;
    }
    
    public static int divider(int num1, int num2) {
        return Math.round(num1 / num2);
    }
}
