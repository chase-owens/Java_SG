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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;

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

        while (keepGoing) {
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

    }

    private void displayMenu() {
        view.displayMenu();
    }

    private void getSelection() throws DataValidationException {
        selection = view.getSelection();
    }

    private void displayOrders() throws DateNotFoundException, DataValidationException, FlooringMasteryPersistenceError {

        // Get date
        String date = getDate();

        // Check for file and get orders
        HashMap<String, PurchaseOrder> existingOrders = service.getOrders(date);

        // Print orders
        view.printOrders(existingOrders);

        // Audit - String[] - "displayOrders" + "date"
        service.audit("displayOrders", date);
    }

    private void addOrder() throws DataValidationException, FlooringMasteryPersistenceError, DateNotFoundException {
        // Get date and validate that date is not in the past
        String date = getDateAndCheckDateNotInThePast(), area = null;
        boolean valid = false;
        Product product = null;
        StateTax stateTaxRate = null;
        BigDecimal areaBD = BigDecimal.ZERO;

        // Load date
        service.loadOrders(date);

        // Convert date
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        // Get order information
        Set<String> states = service.getStatesServiced();
        Set<String> products = service.getProductsOffered();
        //String[] info = view.getOrderInfo(states, products);

        // Get user info name, area, state, and product
        String name = view.getUserName();

        // Get area
        while (!valid && areaBD.compareTo(BigDecimal.TEN) < 0) {
            try {
                areaBD = view.getUserArea();
                area = areaBD.toString();
                valid = true;
                if (areaBD.compareTo(BigDecimal.TEN) == -1) {
                    valid = false;
                    view.informUserOrderTooSmall();
                }
            } catch (DataValidationException e) {
                view.handleError(e);
            }

        }
        valid = false;

        // State and taxRate
        while (!valid) {
            try {
                String state = view.getUserState(states);
                stateTaxRate = service.validateState(state);
                valid = true;
            } catch (DataValidationException e) {
                view.handleError(e);
            }

        }
        valid = false;

        // validate product takes in product name
        while (!valid) {
            try {
                String productString = view.getProductType(products);
                product = service.validateProduct(productString);
                valid = true;
            } catch (DataValidationException e) {
                view.handleError(e);
            }
        }

        // Create purchaseOrderObject
        PurchaseOrder po = service.createPurchaseOrder(name, product, stateTaxRate, area, parsedDate);

        // Display order details get confirmation
        boolean decision = view.placeOrder(po);

        if (decision = true) {
            // Check if file exists with that date Add order to HashMap
            PurchaseOrder confirmedPO = service.addOrderToOrdersMap(po);

            // Display confirmation message
            view.displayConfirmation(confirmedPO);
        }

        // auditFile "addOrder" + "date" + "PO"
        service.audit("addOrder", date, po);

    }

    private void editOrder() throws DateNotFoundException, DataValidationException, FlooringMasteryPersistenceError {
        boolean valid = false;
        Product product = null;
        StateTax stateTaxRate = null;
        BigDecimal areaBD = BigDecimal.ZERO;
        String area = null, state = null, productString = null;

        // Get date
        String date = getDateAndCheckDateNotInThePast();

        // Get existing orders and display them to user
        HashMap<String, PurchaseOrder> currentOrders = service.getOrders(date);
        view.printOrders(currentOrders);

        // Get order number
        String orderNumber = view.getOrderNumber();

        // Get order
        PurchaseOrder po = service.getOrder(date, orderNumber);

        // Display Order
        view.displayOrderInformation(po);

        // Get new order info
        Set<String> states = service.getStatesServiced();
        Set<String> products = service.getProductsOffered();

        // Get user info name, area, state, and product
        String name = view.getUserNewName(po);
        if (name.equals("")) {
            name = po.getCustomerName();
        }

        // Get area
        while (!valid && areaBD.compareTo(BigDecimal.TEN) < 0) {
            try {
                areaBD = view.getNewUserArea(po);
                if (areaBD == null) {
                    area = po.getArea().toString();
                    valid = true;

                } else {
                    area = areaBD.toString();
                    valid = true;
                    if (areaBD.compareTo(BigDecimal.TEN) == -1) {
                        valid = false;
                        view.informUserOrderTooSmall();
                    }
                }
            } catch (DataValidationException e) {
                view.handleError(e);
            }

        }
        valid = false;

        // State and taxRate
        while (!valid) {
            try {
                state = view.getNewUserState(states, po);
                if (state.equals("")) {
                    valid = true;
                    stateTaxRate = service.validateState(po.getState());
                } else {
                    stateTaxRate = service.validateState(state);
                    valid = true;
                }
            } catch (DataValidationException e) {
                view.handleError(e);
            }

        }
        valid = false;

        // validate product takes in product name
        while (!valid) {
            try {
                productString = view.getNewProductType(products, po);
                if (productString.equals("")) {
                    product = service.validateProduct(po.getProductType());
                    valid = true;
                } else {
                    product = service.validateProduct(productString);
                    valid = true;
                }

            } catch (DataValidationException e) {
                view.handleError(e);
            }
        }

        PurchaseOrder updatedPO = service.updatePO(name, product, stateTaxRate, area, po);

        // Display order details get confirmation
        boolean decision = view.placeOrder(updatedPO);

        if (decision = true) {

            // Display confirmation message
            view.displayConfirmation(updatedPO);
        }

        // auditFile "editFile" "date" "orderNumber"
        service.audit("editFile", date, orderNumber);
    }

    private void removeOrder() throws DateNotFoundException, DataValidationException, FlooringMasteryPersistenceError {
        // Get date
        String date = getDateAndCheckDateNotInThePast();

        // Put in hashMap if not already there
        service.loadOrders(date);

        // Get existing orders and display them to user
        HashMap<String, PurchaseOrder> currentOrders = service.getOrders(date);
        view.printOrders(currentOrders);

        // Get order number
        String orderNumber = view.getOrderNumber();

        // Remove order
        service.removeOrder(date, orderNumber);

        // Display confirmation message
        view.confirmOrderRemoved(orderNumber);

        // auditFile "removeOrder" "date" "orderNumber"
        service.audit("removeOrder", date, orderNumber);
    }

    private void saveOrder() throws FlooringMasteryPersistenceError {
        // Go through orders hashMap
        service.saveOrders();

        // auditFile "saveOrders"
        service.audit("saveOrders");
    }

    private void exitGracefully() {
        keepGoing = false;
        view.sayGoodbye();
    }

    private void handleError(Exception e) throws FlooringMasteryPersistenceError {
        view.handleError(e);

        // auditFile "errorMessage"
        service.audit(e.getMessage());
    }

    private String getDate() throws FlooringMasteryPersistenceError {
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

    private String getDateAndCheckDateNotInThePast() throws FlooringMasteryPersistenceError {
        String date = null;
        boolean canFulfill = false;

        // Might remove while - Try/Catch critical
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
