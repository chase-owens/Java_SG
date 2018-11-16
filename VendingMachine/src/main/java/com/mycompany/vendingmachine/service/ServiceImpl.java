/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.Dao;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author chaseowens
 */
public class ServiceImpl implements Service {
    Dao dao;
    
    public ServiceImpl(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Collection<Item> getItems() throws VendingMachinePersistenceError {
        return dao.getItems();
    }

    @Override
    public BigDecimal processTransaction(BigDecimal $, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        return dao.processTransaction($, selection);
    }

    @Override
    public ChangeMaker makeChange(BigDecimal change) {
        return dao.makeChange(change);
    }
}
