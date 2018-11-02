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
    }
    public static boolean comeaBefore(String a, String b) {
        return a.compareToIgnoreCase(b) < 0;
    }
}
