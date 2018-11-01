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

public class TheCount {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int start, stop, countBy;
        
        System.out.println("I LOVE TO COUNT! HA HA HA, LET ME SHARE MY COUNTING WITH YOU!");
        
        System.out.println("What number should we start at?");
        start = input.nextInt();
        
        System.out.println("What number should we count to?");
        stop = input.nextInt();
        
        System.out.println("Should we count by 1s, 2s, or ?");
        countBy = input.nextInt();
        
        for (int i = start; i < (stop + 1); i += countBy) {
            System.out.println(i);
            if (i % 3 == 0) {
                System.out.println("Ah ah ah!");
            }
        }
    }
}
