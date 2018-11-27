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
import java.io.PrintStream;

public class App {
    static Scanner read = new Scanner(System.in);
    static PrintStream placeholderSetter = new PrintStream(System.out);
    
    public static void main(String[] args) {
        String entry, name = "name", age = "age";
        
        
        
        System.out.print(name + "\r");
        
        getName();
        
        //entry = read.nextLine();
    }
    
    public static String getName() {
        String name = null;
        boolean selected = false;
        
//        placeholderSetter.append("name");
        
        while (!selected) {
            name = read.nextLine();
            selected = true;
        }
        
        System.out.println(name);
        
        return name;
    }
}
