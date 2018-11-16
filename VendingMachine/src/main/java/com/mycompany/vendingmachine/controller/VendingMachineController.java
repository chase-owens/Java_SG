/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

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
    BigDecimal $;

    public VendingMachineController(View view, Service service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        
        String selection;
        BigDecimal change;

        try {
            while (keepGoing) {
                displayItems();
                $ = getMoney();
                selection = selectItem();
                change = processTransaction($, selection);
                displayChangeOwed(change);
                checkIfMakeAnotherTransaction();
                
                // display and return change, $ = 0, add exit option to display
                // make change Class pass in change, have method setting dollars, quarters, dimes, etc.
                
                // I added updateInventory and getInventory to displayItems and processTransaction
                
            }
            exitGracefully();
            
        } catch (InsufficientFundsError | OutOfStockException | VendingMachinePersistenceError e) {
            displayErrorMessage(e);
        }

    }

    private void displayItems() throws VendingMachinePersistenceError {
        // Get all items to vend
        Collection<Item> items = service.getItems();

        // Display items
        view.displayItems(items);
    }

    private BigDecimal getMoney() {
        // Ask user to enter money
        BigDecimal cash = new BigDecimal(view.getMoney());
        return cash;
    }

    private String selectItem() {
        return view.selectItem();
    }

    private BigDecimal processTransaction(BigDecimal $, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        // Process Transaction, Update Inventory
        
        BigDecimal change = service.processTransaction($, selection);
        service.auditFile(selection);
        return change;
    }
    
    // Ask Tobe how to handle both errors **** I think I answered it, but need to ask for confirmation
    private void displayErrorMessage(Exception e) {
        view.displayErrorMessage(e);
    }

    private void exitGracefully() {
        view.exitGracefully();
    }

    private void displayChangeOwed(BigDecimal change) {
        view.displayChangeOwed(change);
        
        ChangeMaker changeOwed = service.makeChange(change);
        
        view.giveChange(changeOwed);
        
        $ = BigDecimal.ZERO;
    }

    private void checkIfMakeAnotherTransaction() {
        String decision = view.checkIfMakeAnotherTransaction();
        if (decision.startsWith("n")) {
            keepGoing = false;
        }
    }

}
