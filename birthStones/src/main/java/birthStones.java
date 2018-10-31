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

public class birthStones {

    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        int numberProvided;

        System.out.println("Please enter a whole number between 1 and 12: ");
        numberProvided = inputReader.nextInt();

        if (numberProvided == 1) {
            System.out.println("Janurary - Garnet");
        } else if (numberProvided == 2) {
            System.out.println("February - Amethyst");
        } else if (numberProvided == 3) {
            System.out.println("March - Aquamarine");
        } else if (numberProvided == 4) {
            System.out.println("April - Diamond");
        } else if (numberProvided == 5) {
            System.out.println("May - Emerald");
        } else if (numberProvided == 6) {
            System.out.println("June - Pearl");
        } else if (numberProvided == 7) {
            System.out.println("July - Ruby");
        } else if (numberProvided == 8) {
            System.out.println("August - Peridot");
        } else if (numberProvided == 9) {
            System.out.println("September - Sapphire");
        } else if (numberProvided == 10) {
            System.out.println("October - Opal");
        } else if (numberProvided == 11) {
            System.out.println("November - Topaz");
        } else if (numberProvided == 12) {
            System.out.println("December - Turquoise");
        } else {
            throw new java.lang.Error("Please enter a whole number between 1 an 12");
        }
        
        inputReader.close();
    }
}
