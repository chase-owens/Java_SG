/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author chaseowens
 */
public class DaoStubImpl implements Dao{
    private final HashMap<String, Item> items = new HashMap<>();
    private final BigDecimal bd = BigDecimal.TEN;
    private final Item onlyItem;
    
    public DaoStubImpl() {
        this.onlyItem = new Item("dummy", bd, 1);
        items.put(onlyItem.getName(), onlyItem);
    }
    
    @Override
    public Collection getItems() {
        Collection<Item> currentInventory = items.values();
        if (currentInventory.size() == 1) {
            return currentInventory;
        } else {
            return null;
        }
        
    }
    

    @Override
    public BigDecimal processTransaction(BigDecimal $, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        BigDecimal change = $.subtract(onlyItem.getPrice());
        if (change.compareTo(BigDecimal.ZERO) == 0) {
            return change;
        } else {
            return null;
        }
    }

    @Override
    public void getInventory() throws VendingMachinePersistenceError {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateInventory(HashMap<String, Item> currentItems) throws VendingMachinePersistenceError {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChangeMaker makeChange(BigDecimal change) {
        ChangeMaker changeOwed = new ChangeMaker(change);
        if (change.compareTo(BigDecimal.ZERO) > 0) {
            return changeOwed;
        } else {
            return null;
        }
    }
}
