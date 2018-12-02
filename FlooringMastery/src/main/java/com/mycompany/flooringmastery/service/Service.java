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
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author chaseowens
 */
public interface Service {

    public StateTax validateState(String string) throws DataValidationException ;

    public Product validateProduct(String string) throws DataValidationException ;

    public void loadFiles() throws FlooringMasteryPersistenceError;

    public PurchaseOrder createPurchaseOrder(String name, Product product, StateTax stateTaxRate, String area, LocalDate date);

    public PurchaseOrder addOrderToOrdersMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException;

    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException;

    public Set<String> getStatesServiced();

    public Set<String> getProductsOffered();

    public void removeOrder(String date, String orderNumber) throws FlooringMasteryPersistenceError, DateNotFoundException;

    public PurchaseOrder getOrder(String date, String orderNumber) throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException;

    public PurchaseOrder updatePO(String name, Product product, StateTax stateTaxRate, String area, PurchaseOrder po);

    public void saveOrders() throws FlooringMasteryPersistenceError;

    public void audit(String displayOrders, String date) throws FlooringMasteryPersistenceError;

    public void audit(String addOrder, String date, PurchaseOrder po) throws FlooringMasteryPersistenceError;

    public void audit(String editFile, String date, String orderNumber) throws FlooringMasteryPersistenceError;

    public void audit(String saveOrders) throws FlooringMasteryPersistenceError;

    public void loadOrders(String date) throws FlooringMasteryPersistenceError;
    
}
