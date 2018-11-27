/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.view;


/**
 *
 * @author chaseowens
 */
public class View {

    UserIO io;

    public View(UserIO injectedIO) {
        this.io = injectedIO;
    }

    public String askIfUserWantsProduction() {
        String selection = io.readString("Training or Production mode? Enter T or P");
        

        return selection;

    }

    public void handleError(Exception e) {
        io.print(e.getMessage());
    }

    public void sayGoodbye() {
        io.print("Thank you for your business :)");
    }

    public void displayMenu() {
        
        io.print("\n\n/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\\n"
                + "\\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/\n\n");
        
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit");
        
        io.print("\n\n/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\_/\\\n"
                + "\\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/ \\/\n\n");
    }

    public int getSelection() {
        int selection = io.readInt("Select an option by entering its respective option number", 1, 6);
        return selection;
    }

    public String getDateOfOrder() {
        String date = io.readString("Enter date order was scheduled to be fulfilled in 'mm-dd-yyyy' format");
        return date;
    }
}
