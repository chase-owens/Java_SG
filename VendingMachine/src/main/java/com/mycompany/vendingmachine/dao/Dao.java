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
public interface Dao {

    public Collection<Item> getItems() throws VendingMachinePersistenceError ;

    public BigDecimal processTransaction(BigDecimal $, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError;

    public ChangeMaker makeChange(BigDecimal change);
    
    public void getInventory() throws VendingMachinePersistenceError;
    
    public void updateInventory(HashMap<String, Item> currentItems) throws VendingMachinePersistenceError;
    
}
