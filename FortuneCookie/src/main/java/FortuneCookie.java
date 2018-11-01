/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */

import java.util.Random;

public class FortuneCookie {
    public static void main(String[] args) {
        
        Random randomizer = new Random();
        
        int x = randomizer.nextInt(9);
        
        switch(x) {
            case 0:
                System.out.println("Your geek fortune: Those aren’t the droids you’re looking for.");
                break;
            case 1:
                System.out.println("Your geek fortune: Never go in against a Sicilian when death is on the line!");
                break;
            case 2:
                System.out.println("Your geek fortune: Goonies never say die.");
                break;
            case 3:
                System.out.println("Your geek fortune: With great power there must also come — great responsibility.");
                break;
            case 4:
                System.out.println("Your geek fortune: Never argue with the data.");
                break;
            case 5:
                System.out.println("Your geek fortune: Try not. Do, or do not. There is no try.");
                break;
            case 6:
                System.out.println("Your geek fortune: You are a leaf on the wind, watch how you soar.");
                break;
            case 7:
                System.out.println("Your geek fortune: Do absolutely nothing, and it will be everything that you thought it could be.");
                break;
            case 8:
                System.out.println("Your geek fortune: Kneel before Zod.");
                break;
            default:
                System.out.println("Your geek fortune: Make it so.");
        }
        
    }
}
