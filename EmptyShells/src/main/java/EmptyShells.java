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

public class EmptyShells {
    static Random randomizer = new Random();
    
    public static void main(String[] args) {
        String color = createColor();
        String animal = createAnimal();
        String colorAgain = createColor();
        
        int weight = createNumber(5, 200);
        int distance = createNumber(10, 20);
        int number = createNumber(10000, 20000);
        int time = createNumber(2, 6);
        
        System.out.println("Once, when I was very small...");
        System.out.println("I was chased by a " + color + ", " + weight + "lb miniature " + animal + " for over " + distance + " miles!!");
        System.out.println("I had to hide in a field of over " + number + " " + colorAgain + " poppies for nearly " + time + " hours until it left me alone!");
        System.out.println("\nIt was QUITE the experienuce, " + "let me tell you!");
    }
    
    public static int createNumber(int min, int max) {
        return randomizer.nextInt(max + min + 1) - min;
    }
    
    public static String createColor() {
        int rNum = randomizer.nextInt(3);
        String[] colors = {"Green", "Red", "Yellow"};
        return colors[rNum];
    }
    
    public static String createAnimal() {
        int rNum = randomizer.nextInt(3);
        String[] animals = {"fish", "reptile", "mammal"};
        return animals[rNum];
    }
    
}
