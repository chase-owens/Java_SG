/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.view;

import com.mycompany.vendingmachine.dao.GettingMoneyError;
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

        io.print("\n\n/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\\n"
                + "\\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/\n\n");

        // lambda steram, filter, forEach methods
        items
                .stream()
                .filter(item -> item.getInventoryCount() > 0)
                .forEach(item -> {
                    if (item.getName().length() < 10) {
                        io.print(item.getName() + "\t\t\t" + df.format(item.getPrice()));
                    } else if (item.getName().length() < 15) {
                        io.print(item.getName() + "\t\t" + df.format(item.getPrice()));
                    } else {
                        io.print(item.getName() + "\t" + df.format(item.getPrice()));
                    }
                });

        io.print("\n\n/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\\n"
                + "\\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/\n\n");

        // First attempt using enhanced for loop
//        for (Item item : items) {
//            if (item.getInventoryCount() > 0) {
//                io.print(item.getName() + "\t" + item.getPrice());
//            }
//        }
    }

    public String getMoney() throws GettingMoneyError {

        String amountPaid = io.readString("Enter money without commas\n");
        return amountPaid;
    }

    public String selectItem() {
        String item = io.readString("Enter the name of the item you want to vend\n");
        io.print("You selected " + item + "\n");
        return item;
    }

    public void displayErrorMessage(Exception e) {
        io.print(e.getMessage() + "\n");
    }

    public void exitGracefully() {
        io.print("Hopefully you found what you were looking for.");
    }

    public void displayChangeOwed(BigDecimal change) {
        BigDecimal changeToDisplay = change.setScale(2, RoundingMode.HALF_UP);
        io.print("Keep the change you filthy animal!!\t" + changeToDisplay.toString());
    }

    public void giveChange(ChangeMaker changeOwed) {
        if (changeOwed.getOne() > 0) {
            io.print("Dollars: " + changeOwed.getOne());
        }
        if (changeOwed.getQuarter() > 0) {
            io.print("Quarters: " + changeOwed.getQuarter());
        }
        if (changeOwed.getDime() > 0) {
            io.print("Dimes: " + changeOwed.getDime());
        }
        if (changeOwed.getNickel() > 0) {
            io.print("Nickles: " + changeOwed.getNickel());
        }
        if (changeOwed.getPenny() > 0) {
            io.print("Pennies: " + changeOwed.getPenny());
        }
        io.print("\nThank you for your business!!\n");
    }

    public String checkIfMakeAnotherTransaction() {
        String answer = io.readString("Do you want to do another transaction Y/N");
        return answer.toLowerCase();
    }

    public void displayMoneyEntered(String cash) {
        io.print("You entered " + cash + "\n");
    }

    public void explainKeepGoingRules() {
        io.print("\nSimply enter 'yes' or 'no'. You can also just type 'y' for yes and 'n' for no\n");
    }

}
