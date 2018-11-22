/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dao.GetEntryError;
import com.mycompany.vendingmachine.dao.GettingMoneyError;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.service.Service;
import com.mycompany.vendingmachine.view.View;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author chaseowens
 */
public class VendingMachineController {

    View view;
    Service service;
    boolean keepGoing = true;
    BigDecimal moneyEntered, change;
    String selection;

    public VendingMachineController(View view, Service service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GettingMoneyError, GetEntryError {

        while (keepGoing) {
            try {
                while (keepGoing) {
                    displayItems();
                    getMoney();
                    getSelection();
                    processTransaction();
                    checkIfMakeAnotherTransaction();
                }
                exitGracefully();

            } catch (InsufficientFundsError | OutOfStockException | VendingMachinePersistenceError | GettingMoneyError e) {
                handleError(e);
            }
        }

    }

    private void displayItems() throws VendingMachinePersistenceError {
        // Get all items to vend
        Collection<Item> items = service.getItems();

        // Display items
        view.displayItems(items);
    }

    private void getMoney() throws GettingMoneyError, VendingMachinePersistenceError {

        boolean validCash = false;
        BigDecimal cashEntered = null;
        String cash = null;
        // Ask user to enter money

        while (!validCash) {
            try {
                cash = view.getMoney();
                cashEntered = service.checkMoney(cash);
                validCash = true;
            } catch (GettingMoneyError e) {
                handleError(e);
            }
        }

        view.displayMoneyEntered(cash);
        moneyEntered = cashEntered;
    }

    private void getSelection() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        boolean validSelection = false;
        do {
            try {
                selection = view.selectItem();
                change = processTransaction(moneyEntered, selection);
                validSelection = true;
            } catch (GetEntryError e) {
                handleError(e);
            }
        } while (!validSelection);
    }

    private BigDecimal processTransaction(BigDecimal money, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        // Process Transaction, Update Inventory

        BigDecimal changeMade = service.processTransaction(money, selection);

        Item item = service.getItem(selection);

        service.auditFile(item.toString() + " : " + "successful");

        return changeMade;
    }

    private void exitGracefully() {
        view.exitGracefully();
    }

    private void processTransaction() {
        view.displayChangeOwed(change);

        ChangeMaker changeOwed = service.makeChange(change);

        view.giveChange(changeOwed);

        moneyEntered = BigDecimal.ZERO;
    }
    
    private void giveRefund() {
        view.displayChangeOwed(moneyEntered);

        ChangeMaker changeOwed = service.makeChange(moneyEntered);

        view.giveChange(changeOwed);

        moneyEntered = BigDecimal.ZERO;
    }

    private void checkIfMakeAnotherTransaction() {
        String decision;
        boolean validEntry = false;
        while (!validEntry) {
            
            // Make another transaction?
            decision = view.checkIfMakeAnotherTransaction();
            
            // Check answer for no
            if (decision.startsWith("n")) {
                keepGoing = false;
                validEntry = true;
            } else if (decision.startsWith("y")) {
                validEntry = true;
            } else {
                view.explainKeepGoingRules();
            }
        }

    }

    private void handleError(Exception e) throws VendingMachinePersistenceError {
        view.displayErrorMessage(e);
        Item item = service.getItem(selection);
        if (e.getMessage().equals("Insufficient funds") || e.getMessage().equals("Out of stock")) {
            service.auditFile(item.toString() + " : " + e.getMessage());
            giveRefund();
        }
    }

}
