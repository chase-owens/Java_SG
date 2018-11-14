/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.daysoftheweek;

import java.util.Scanner;

/**
 *
 * @author chaseowens
 */
class View {
    Scanner read = new Scanner(System.in);

    String favoriteDay() {
        System.out.println("What's your favorite day of the week");
        return read.nextLine();
    }

    void print(String yourDaysMessage) {
        System.out.println(yourDaysMessage);
    }
    
    
}
