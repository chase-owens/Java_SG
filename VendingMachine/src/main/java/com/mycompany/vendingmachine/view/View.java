/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.view;

import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collection;

/**
 *
 * @author chaseowens
 */
public class View {

    UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public void displayItems(Collection<Item> items) {
        DecimalFormat df = new DecimalFormat("#,##0");
        
        
        // lambda steram, filter, forEach methods
        items
                .stream()
                .filter(item -> item.getInventoryCount() > 0)
                .forEach(item -> {if (item.getName().length() < 10) {
                    io.print(item.getName() + "\t\t\t" + df.format(item.getPrice()));
                } else if(item.getName().length() < 15) {
                    io.print(item.getName() + "\t\t" + df.format(item.getPrice()));
                } else {
                    io.print(item.getName() + "\t" + df.format(item.getPrice()));
                }
                });

        // First attempt using enhanced for loop
//        for (Item item : items) {
//            if (item.getInventoryCount() > 0) {
//                io.print(item.getName() + "\t" + item.getPrice());
//            }
//        }
    }

    public String getMoney() {
        io.print("\n\n/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\\n" +
"\\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/\n\n");
        String $ = io.readString("Enter money without commas");
        io.print("You entered " + $);
        return $;
    }

    public String selectItem() {
        String item = io.readString("Enter the name of the item you want to vend");
        io.print("You selected " + item);
        return item;
    }

    public void displayErrorMessage(Exception e) {
        io.print(e.getMessage());
    }

    public void exitGracefully() {
        io.print("Keep the change you filthy animal!!");
    }

    public void displayChangeOwed(BigDecimal change) {
        BigDecimal changeToDisplay = change.setScale(2, RoundingMode.HALF_UP);
        io.print("Your change is " + changeToDisplay.toString());
    }

    public void giveChange(ChangeMaker changeOwed) {
        if (changeOwed.getHundred() > 0) {
            io.print("Franklins: " + changeOwed.getHundred());
        }
        if (changeOwed.getFifty() > 0) {
            io.print("Grants: " + changeOwed.getFifty());
        }
        if (changeOwed.getTwenty() > 0) {
            io.print("Tubmans & Jacksons: " + changeOwed.getTwenty());
        }
        if (changeOwed.getTen() > 0) {
            io.print("Hamiltons: " + changeOwed.getTen());
        }
        if (changeOwed.getFive() > 0) {
            io.print("Lincolns: " + changeOwed.getFive());
        }
        if (changeOwed.getOne() > 0) {
            io.print("Chases or Washingtons: " + changeOwed.getOne());
        }
        if (changeOwed.getQuarter() > 0) {
            io.print("Quarters: " + changeOwed.getQuarter());
        }
        if (changeOwed.getDime() > 0) {
            io.print("Dimes: " + changeOwed.getDime());
        }
        if (changeOwed.getFive() > 0) {
            io.print("Nickles: " + changeOwed.getNickel());
        }
        if (changeOwed.getPenny() > 0) {
            io.print("Pennies: " + changeOwed.getPenny());
        }
        io.print("Thank you for your business!!");
    }

    public String checkIfMakeAnotherTransaction() {
        String answer = io.readString("Do you want to do another transaction Y/N");
        return answer.toLowerCase();
    }

}
