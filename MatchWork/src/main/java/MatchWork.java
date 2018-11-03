/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class MatchWork {
    public static void main(String[] args) {
        System.out.println(" The word Cart should come before Horse alphabetically : " + comesBefore("cart", "horse"));
        System.out.println(" Half of 42 = " + halfOf(42));
        System.out.println(" (short) Pi = " + pi());
        System.out.println(" This first letter of the word Llama is: " + firstLetter(Llama));
        System.out.println(" 1337 x 1337 = " + times1337(1337));
    }
    public static int times1337(int n) {
        return 1337 * n;
    }
    
    public static String firstLetter(String word) {
        return word.substring(0, 1);
    }
    
    public static double pi() {
        return 3.14;
    }
    
    public static double halfOf(double num) {
        return (num / 2);
    }
    public static boolean comeaBefore(String a, String b) {
        return a.compareToIgnoreCase(b) < 0;
    }
}
