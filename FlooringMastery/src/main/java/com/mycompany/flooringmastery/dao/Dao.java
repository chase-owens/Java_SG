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
import java.util.Set;
/**
 *
 * @author chaseowens
 */
public interface Dao {

    public Product createProduct(String productNameEntered) throws DataValidationException;

    public StateTax createState(String stateEntered) throws DataValidationException;

    public void loadFiles()throws FlooringMasteryPersistenceError;

    public PurchaseOrder createPurchaseOrder(String name, Product product, StateTax stateTaxRate, String area, LocalDate date);

    public PurchaseOrder addOrderToOrderMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException;

    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException;

    public Set<String> getStatesServiced();

    public Set<String> getProductsOffered();

    public void removeOrder(String date, String orderNumber)throws FlooringMasteryPersistenceError, DateNotFoundException;

    public PurchaseOrder getOrder(String date, String orderNumber)throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException;

    public PurchaseOrder updatePO(String name, Product product, StateTax stateTaxRate, String area, PurchaseOrder po);

    public void saveOrders() throws FlooringMasteryPersistenceError;

    public void loadOrders(String date) throws FlooringMasteryPersistenceError;
    
}
