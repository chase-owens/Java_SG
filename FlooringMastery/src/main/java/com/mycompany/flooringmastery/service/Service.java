/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.DataValidationException;
import com.mycompany.flooringmastery.dao.DateNotFoundException;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author chaseowens
 */
public interface Service {

    public StateTax validateState(String string) throws DataValidationException ;

    public Product validateProduct(String string) throws DataValidationException ;

    public void loadFiles() throws FlooringMasteryPersistenceError;

    public PurchaseOrder createPurchaseOrder(String name, Product product, StateTax stateTaxRate, String area, LocalDate date);

    public void addOrderToOrdersMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException;

    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException;
    
}
