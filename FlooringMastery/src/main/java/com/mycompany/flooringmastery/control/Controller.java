/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.control;

import com.mycompany.flooringmastery.dao.DataValidationException;
import com.mycompany.flooringmastery.dao.DateNotFoundException;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError;
import com.mycompany.flooringmastery.model.DateValidationObject;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import com.mycompany.flooringmastery.service.Service;
import com.mycompany.flooringmastery.view.View;
import java.time.LocalDate;
import java.util.HashMap;

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
        loadFiles();
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

    private void displayOrders() throws DateNotFoundException, DataValidationException, FlooringMasteryPersistenceError {

        // Get date
        String date = getDate();

        // Check for file and get orders
        HashMap<String, PurchaseOrder> existingOrders = service.getOrders(date);

        // Print orders
        view.printOrders(existingOrders);
    }

    private void addOrder() throws DataValidationException, FlooringMasteryPersistenceError, DateNotFoundException {
        // Get date and validate
        String date = getDateAndCheckDateNotInThePast();

        // Convert date
        LocalDate parsedDate = LocalDate.parse(date);

        // Make sure date is today or in the future
        // Get order information
        String[] info = view.getOrderInfo();

        // validate product
        Product product = service.validateProduct(info[2]);

        // validate state
        StateTax stateTaxRate = service.validateState(info[1]);

        // Create purchaseOrderObject
        PurchaseOrder po = service.createPurchaseOrder(info[0], product, stateTaxRate, info[3], parsedDate);

        // Display order details get confirmation
        boolean decision = view.placeOrder(po);

        if (decision = true) {
            // Check if file exists with that date Add order to HashMap
            service.addOrderToOrdersMap(po);

            // Display confirmation message
        }

    }

    private void editOrder() throws DateNotFoundException, DataValidationException {
        // Get date
        String date = getDateAndCheckDateNotInThePast();

        // Get order number
        // Get order
        // Get new order information
        // Edit order
    }

    private void removeOrder() throws DateNotFoundException, DataValidationException {
        // Get date
        String date = getDateAndCheckDateNotInThePast();

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

    private String getDate() {
        String date = null;
        boolean validEntry = false;
        while (!validEntry) {
            try {
                date = view.getDateOfOrder();
                DateValidationObject questionableDate = new DateValidationObject(date);
                validEntry = questionableDate.validate();
            } catch (DataValidationException e) {
                handleError(e);
            }

        }
        return date;
    }

    private void loadFiles() throws FlooringMasteryPersistenceError {
        service.loadFiles();
    }

    private String getDateAndCheckDateNotInThePast() {
        String date = null;
        boolean canFulfill = false;

        while (!canFulfill) {
            try {
                date = getDate();
                DateValidationObject dateEntered = new DateValidationObject(date);
                canFulfill = dateEntered.checkNotPast();
            } catch (DataValidationException e) {
                handleError(e);
            }
        }

        return date;
    }

}
