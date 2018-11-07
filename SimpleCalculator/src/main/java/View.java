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

public class View {

    static Scanner capture = new Scanner(System.in);
    Controller c = new Controller();
    
    public static String selectMathOperation() {
        System.out.println (
    "Do you want to do some addition, subtraction, multiplication, division, or do you suffer from Math Phobia?");
        String selection = capture.next();
        return selection;
    }

    

}
