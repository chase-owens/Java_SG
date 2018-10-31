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

        System.out.println("What month's birthstones do you want to know? ");
        numberProvided = inputReader.nextInt();

        if (numberProvided == 1) {
            System.out.println("Janurary's birthstone is Garnet");
        } else if (numberProvided == 2) {
            System.out.println("February's birthstone is Amethyst");
        } else if (numberProvided == 3) {
            System.out.println("March's birthstone is Aquamarine");
        } else if (numberProvided == 4) {
            System.out.println("April's birthstone is Diamond");
        } else if (numberProvided == 5) {
            System.out.println("May's birthstone is Emerald");
        } else if (numberProvided == 6) {
            System.out.println("June's birthstone is Pearl");
        } else if (numberProvided == 7) {
            System.out.println("July's birthstone is Ruby");
        } else if (numberProvided == 8) {
            System.out.println("August's birthstone is Peridot");
        } else if (numberProvided == 9) {
            System.out.println("September's birthstone is Sapphire");
        } else if (numberProvided == 10) {
            System.out.println("October's birthstone is Opal");
        } else if (numberProvided == 11) {
            System.out.println("November's birthstone is Topaz");
        } else if (numberProvided == 12) {
            System.out.println("December's birthstone is Turquoise");
        } else {
            throw new java.lang.Error("Please enter a whole number between 1 an 12");
        }
        
        inputReader.close();
    }
}
