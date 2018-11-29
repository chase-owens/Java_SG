/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import java.time.LocalDate;
import java.util.HashMap;
/**
 *
 * @author chaseowens
 */
public interface Dao {

    public Product validateProduct(String productNameEntered) throws DataValidationException;

    public StateTax validateState(String stateEntered) throws DataValidationException;

    public void loadFiles()throws FlooringMasteryPersistenceError;

    public PurchaseOrder createPurchaseOrder(String name, Product product, StateTax stateTaxRate, String area, LocalDate date);

    public void addOrderToOrderMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException;

    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException;
    
}
