/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class DifferentKettleOfFish {

    public static void main(String[] args) {
        int fish = 1;

        while (fish < 11) {
            if (fish == 3) {
                System.out.println("RED fish");
            } else if (fish == 4) {
                System.out.println("BLUE fish");
            } else {
                System.out.println(fish + "fish");
            }
            fish++;
        }
        
        fish = 1;
        
        for (int i = fish; i < 11; i++) {
            if (i == 3) {
                System.out.println("RED fish");
            } else if (i == 4) {
                System.out.println("BLUE fish");
            } else {
                System.out.println(i + "fish");
            }
        }
    }
}
