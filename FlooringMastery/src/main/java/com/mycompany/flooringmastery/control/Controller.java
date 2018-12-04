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
        String date = getDateAndCheckDateNotInThePast();

        // Load date
        service.loadOrders(date);

        // Convert date
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        // Get order information
        Set<String> products = service.getProductsOffered();

        // Get user info name, area, state, and product
        String name = view.getUserName();

        // Get area
        String area = getArea();

        // State and taxRate
        StateTax stateTaxRate = getState();

        // validate product takes in product name
        Product product = getProduct();

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

        // Get user info name, area, state, and product
        String name = updateName(po);

        // Get new area
        String area = updateArea(po);

        // Get new State and taxRate
        StateTax stateTaxRate = updateStateTax(po);

        // Get new Product details
        Product product = updateProduct(po);

        // Update product details
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

        // Display confirmation message
        view.confirmOrderSaved();

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

    private String getDateAndCheckDateNotInThePast() throws FlooringMasteryPersistenceError, DataValidationException {
        String date = getDate();
        DateValidationObject dateEntered = new DateValidationObject(date);
        dateEntered.checkNotPast();

        return date;
    }

    public String getArea() {
        boolean valid = false;
        String area = null;
        BigDecimal areaBD = BigDecimal.ZERO;

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

        return area;
    }

    public StateTax getState() {
        boolean valid = false;
        StateTax stateTaxRate = null;
        Set<String> states = service.getStatesServiced();

        while (!valid) {
            try {
                String state = view.getUserState(states);
                valid = service.validate(state, states);
                stateTaxRate = service.createState(state);
            } catch (DataValidationException e) {
                view.handleError(e);
            }

        }
        return stateTaxRate;
    }

    public Product getProduct() {
        boolean valid = false;
        Product product = null;
        Set<String> products = service.getProductsOffered();

        while (!valid) {
            try {
                String productString = view.getProductType(products);
                valid = service.validate(productString, products);
                product = service.createProduct(productString);
            } catch (DataValidationException e) {
                view.handleError(e);
            }
        }
        return product;
    }

    public String updateName(PurchaseOrder po) {
        String name = view.getUserNewName(po);
        if (name.equals("")) {
            name = po.getCustomerName();
        }
        return name;
    }

    private String updateArea(PurchaseOrder po) {
        boolean valid = false;
        BigDecimal areaBD = BigDecimal.ZERO;
        String area = null;

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
        return area;
    }

    private StateTax updateStateTax(PurchaseOrder po) {
        boolean valid = false;
        StateTax stateTaxRate = null;
        String state = null;
        Set<String> states = service.getStatesServiced();

        while (!valid) {
            try {
                state = view.getNewUserState(states, po);
                if (state.equals("")) {
                    stateTaxRate = service.createState(po.getState());
                    valid = true;
                } else {
                    valid = service.validate(state, states);
                    stateTaxRate = service.createState(state);
                }
            } catch (DataValidationException e) {
                view.handleError(e);
            }

        }
        return stateTaxRate;
    }

    private Product updateProduct(PurchaseOrder po) {
        boolean valid = false;
        Product product = null;
        String productString = null;
        Set<String> products = service.getProductsOffered();

        while (!valid) {
            try {
                productString = view.getNewProductType(products, po);
                if (productString.equals("")) {
                    product = service.createProduct(po.getProductType());
                } else {
                    valid = service.validate(productString, products);
                    product = service.createProduct(productString);
                }

            } catch (DataValidationException e) {
                view.handleError(e);
            }
        }
        return product;
    }

}
