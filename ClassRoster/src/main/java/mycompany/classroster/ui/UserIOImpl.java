/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.ui;
import java.util.Scanner;

/**
 *
 * @author chaseowens
 */
public class UserIOImpl implements UserIO {
    
    final Scanner capture = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        double answer = capture.nextInt();
        return answer;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean isIn = false;
        double num = 0;
        while (isIn == false) {
            System.out.println(prompt);
            num = capture.nextInt();
            if (num < min || num > max) {
                System.out.println("Number must be within range.");
            } else {
                isIn = true;
            }
        }
        return num;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        float answer = capture.nextInt();
        return answer;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num = 0;
        boolean isIn = false;
        while (!isIn) {
            System.out.println(prompt);
            num = capture.nextInt();
            if (num < min || num > max) {
                System.out.println("Must choose number within range.");
            } else {
                isIn = true;
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int num = Integer.parseInt(capture.nextLine());
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean isIn = false;
        int num = 0;
        while (isIn == false) {
            System.out.println(prompt);
            num = Integer.parseInt(capture.nextLine());
            if (num < min || num > max) {
                System.out.println("Must be within range!");
            } else {
                isIn = true;
            }
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        return 0;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        return 0;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String answer = capture.nextLine();
        return answer;
    }
}
