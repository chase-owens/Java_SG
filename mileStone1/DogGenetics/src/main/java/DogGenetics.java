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
import java.util.Random;

public class DogGenetics {
    public static void main(String[] args) {
        Scanner capture = new Scanner(System.in);
        Random random = new Random();
        int totalPercent = 100;
        int workingPercent = 0;
        String dogName;
        String[] breeds = {"Akita", "Schitzu", "Daschund", "Labradoodle", "Border Collie"};
        
        System.out.println("What's your dog's name?");
        dogName = capture.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigous lineage right here.");
        
        for (int i = 0; i < breeds.length; i++) {
            if (i < breeds.length - 1) {
                workingPercent = random.nextInt(totalPercent);
                totalPercent -= workingPercent;
            } else {
                workingPercent = totalPercent;
            }
            System.out.println(workingPercent + "% " + breeds[i]);
        }
        System.out.println("That's quite a dog!!");
        
        
        
    }
}
