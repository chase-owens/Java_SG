/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author chaseowens
 */
public class DaoImplStub implements Dao {

    HashMap<String, BigDecimal[]> products = new HashMap<>();
    HashMap<String, BigDecimal> states = new HashMap<>();
    HashMap<LocalDate, HashMap<String, PurchaseOrder>> ordersMap = new HashMap<>();
    HashMap<String, PurchaseOrder> christmasOrder;
    LocalDate christmas;
    StateTax ohio;
    Product wood;
    PurchaseOrder po;

    public DaoImplStub() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        this.christmas = LocalDate.parse("12-25-2000", df);
        this.ohio = new StateTax("OH", new BigDecimal("6.25"));
        this.wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        this.po = new PurchaseOrder("Santa", this.wood, this.ohio, "100", christmas);
    }

    @Override
    public Product createProduct(String productNameEntered) throws DataValidationException {
        if (productNameEntered.equals("wood")) {
            return this.wood;
        } else {
            throw new DataValidationException("We don't have that product");
        }
    }

    @Override
    public StateTax createState(String stateEntered) throws DataValidationException {
        StateTax state = null;
        if (stateEntered.equals("OH")) {
            state = this.ohio;
        } else {
            throw new DataValidationException("We don't serve that state");
        }
        return state;
    }

    @Override
    public void loadFiles() throws FlooringMasteryPersistenceError {
        // Smile
    }

    @Override
    public PurchaseOrder createPurchaseOrder(String name, Product product, StateTax stateTaxRate, String area, LocalDate date) {
        if (name.equals("Santa") && product.equals(this.wood) && stateTaxRate.equals(this.ohio) && area.equals("100") && date.equals(this.christmas)) {
            return this.po;
        } else {
            return null;
        }
    }

    @Override
    public PurchaseOrder addOrderToOrderMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        this.christmas = LocalDate.parse("12-25-2000", df);
        this.ohio = new StateTax("OH", new BigDecimal("6.25"));
        this.wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        this.po = new PurchaseOrder("Santa", this.wood, this.ohio, "100", christmas);
        this.po.setOrderNumber("1");
        
        if (po.getCustomerName().equals("Santa")) {
            return po;
        } else {
            return null;
        }
    }

    @Override
    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException {
        if (date.equals("12-25-2000")) {
            return this.christmasOrder;
        } else {
            throw new DateNotFoundException("Could not find that date");
        }
    }

    @Override
    public Set<String> getStatesServiced() {
        // smile
        return null;
    }

    @Override
    public Set<String> getProductsOffered() {
        // smile
        return null;
    }

    @Override
    public void removeOrder(String date, String orderNumber) throws FlooringMasteryPersistenceError, DateNotFoundException {
        // smile
    }

    @Override
    public PurchaseOrder getOrder(String date, String orderNumber) throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException {
        this.po.setOrderNumber("1");
        if (date.equals("12-25-2000") && orderNumber.equals("1")) {
            return this.po;
        } else if ((date.equals("12-25-2000") && orderNumber.equals("2"))) {
            throw new DataValidationException("That order number does not exist");
        } else if (date.equals("12-25-2000") == false) {
            throw new DateNotFoundException("Could not find that date");
        } else {
            return null;
        }
    }

    @Override
    public PurchaseOrder updatePO(String name, Product product, StateTax stateTaxRate, String area, PurchaseOrder po) {
        // smile
        return null;
    }

    @Override
    public void saveOrders() throws FlooringMasteryPersistenceError {
        // smile
    }

    @Override
    public void loadOrders(String date) throws FlooringMasteryPersistenceError {
        // Smile
    }

}
