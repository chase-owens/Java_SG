/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.control;

import com.mycompany.flooringmastery.dao.DataValidationException;
import com.mycompany.flooringmastery.dao.DateNotFoundException;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError;
import com.mycompany.flooringmastery.service.Service;
import com.mycompany.flooringmastery.view.View;

/**
 *
 * @author chaseowens
 */
public class Controller {

    View view;
    Service service;
    Boolean keepGoing = true;
    int selection;

    public Controller(View injectedView, Service injectedService) {
        this.view = injectedView;
        this.service = injectedService;
    }

    public void run() throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException {
        try {
            while (keepGoing) {
                displayMenu();
                getSelection();
                switch (selection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveOrder();
                        break;
                    case 6:
                        exitGracefully();
                        break;
                    default:
                        throw new DataValidationException("Invalid entry");
                }
            }
        } catch (DateNotFoundException | FlooringMasteryPersistenceError | DataValidationException e) {
            handleError(e);
        }
    }

    private void displayMenu() {
        view.displayMenu();
    }

    private void getSelection() {
        selection = view.getSelection();
    }

    private void displayOrders() throws DateNotFoundException, DataValidationException {
        String[] formattedDate = null;
        boolean validEntry = false;
        
        // Get date
        while (!validEntry) {
            try {
                String date = view.getDateOfOrder();
                formattedDate = date.split("-");
                int month = Integer.parseInt(formattedDate[0]);
                int day = Integer.parseInt(formattedDate[1]);
                int year = Integer.parseInt(formattedDate[2]);
                if (month < 1 || month > 12 || day < 1 || day > 31 || formattedDate[0].length() != 2 || formattedDate[1].length() != 2 || formattedDate[2].length() != 4) {
                    throw new DataValidationException("This is not in correct format");
                } else if (year < 2000) {
                    throw new DataValidationException("We were not in business at that time");
                } else if (year > 2020) {
                    throw new DataValidationException("We are not taking orders for that date at this time..");
                } else {
                    validEntry = true;
                }
            } catch (UnsupportedOperationException | NumberFormatException | ArrayIndexOutOfBoundsException | DataValidationException e) {
                view.handleError(e);
            }
        }

        // Check for file
        
        
        // Get orders from that file
        // Print orders
    }

    private void addOrder() {
        // Get date to add order

        // Get order information
        // Add orderObject
        // Add order to HashMap
    }

    private void editOrder() throws DateNotFoundException {
        // Get date

        // Get order number
        // Get order
        // Get new order information
        // Edit order
    }

    private void removeOrder() throws DateNotFoundException {
        // Get date

        // Get order number
        // Get order
        // Remove order
    }

    private void saveOrder() throws FlooringMasteryPersistenceError {
        // Go through orders hashMap

        // If date does not have file - Create new file
        // Write order to respective file
    }

    private void exitGracefully() {
        keepGoing = false;
        view.sayGoodbye();
    }

    private void handleError(Exception e) {
        view.handleError(e);
    }

}
