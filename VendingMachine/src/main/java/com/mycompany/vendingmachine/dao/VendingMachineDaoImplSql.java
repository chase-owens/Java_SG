/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import com.mycompany.vendingmachine.dto.ItemMapper;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chaseowens
 */
@Repository
public class VendingMachineDaoImplSql implements Dao {
    @Autowired
    private JdbcTemplate jdbc;
    
    public VendingMachineDaoImplSql() {
        
    }

    @Override
    public Collection<Item> getItems() throws VendingMachinePersistenceError {
        List<Item> itemsL = jdbc.query("SELECT * FROM VendingMachine", new ItemMapper());
        Collection<Item> itemsC = itemsL;
        return itemsC;
    }

    @Override
    public BigDecimal processTransaction(BigDecimal money, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        
        Item item = null;
        int inventoryCount ;
        try {
            item = jdbc.queryForObject("SELECT * FROM VendingMachine Where title = ?", new ItemMapper(), selection);
            inventoryCount = item.getInventoryCount();
        } catch (NullPointerException e) {
            throw new GetEntryError("Please enter the item as it appears in the display.");
        }
        
        BigDecimal change;
        if (item.getInventoryCount() < 1) {
            throw new OutOfStockException("Out of stock");
        } else if (money.compareTo(item.getPrice()) < 0) {
            throw new InsufficientFundsError("Insufficient funds");
        } else {
            change = money.subtract(item.getPrice());
            item.setInventoryCount(inventoryCount - 1);
            jdbc.update("UPDATE VendingMachine SET InventoryCount = ? WHERE  title = ?", item.getInventoryCount(), item.getName());
            //updateInventory(items);
        }
        return change;
    }

    @Override
    public ChangeMaker makeChange(BigDecimal change) {
        ChangeMaker changeOwed = new ChangeMaker(change);
        return changeOwed;
    }

    @Override
    public void getInventory() throws VendingMachinePersistenceError {
        // Does nothing
    }

    @Override
    public void updateInventory(HashMap<String, Item> currentItems) throws VendingMachinePersistenceError {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getItem(String selection) {
        Item item = jdbc.queryForObject("SELECT * FROM VendingMachine Where title = ?", new ItemMapper(), selection);
        return item;
    }

    @Override
    public BigDecimal checkMoney(String amountPaid) throws GettingMoneyError {
        BigDecimal amountPaidBD;

        try {
            amountPaidBD = new BigDecimal(amountPaid);
        } catch (IllegalArgumentException e) {
            throw new GettingMoneyError("Please enter a real number without any letters or commas", e);
        }

        if (amountPaidBD.compareTo(BigDecimal.ZERO) < 1) {
            throw new GettingMoneyError("Try that again and I will call the police and send them a photo of you...");
        } else if (amountPaidBD.compareTo(new BigDecimal("2125000000")) > 0) {
            throw new GettingMoneyError("I see you big baller... That's so such money we don't know what to do with it. Please enter less than 2,125,000,000");
        } else {
            return amountPaidBD;
        }
    }
    
}
