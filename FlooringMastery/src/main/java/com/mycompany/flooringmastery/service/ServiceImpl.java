/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.AuditDao;
import com.mycompany.flooringmastery.dao.Dao;
import com.mycompany.flooringmastery.dao.DataValidationException;
import com.mycompany.flooringmastery.dao.DateNotFoundException;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author chaseowens
 */
public class ServiceImpl implements Service {
    Dao dao;
    AuditDao auditDao;
    
    public ServiceImpl(Dao injectedDao, AuditDao injectedAuditDao) {
        this.dao = injectedDao;
        this.auditDao = injectedAuditDao;
    }

    @Override
    public StateTax validateState(String stateEntered) throws DataValidationException {
        return dao.validateState(stateEntered);
    }

    @Override
    public Product validateProduct(String productNameEntered)  throws DataValidationException  {
        return dao.validateProduct(productNameEntered);
    }

    @Override
    public void loadFiles() throws FlooringMasteryPersistenceError {
        dao.loadFiles();
    }

    @Override
    public PurchaseOrder createPurchaseOrder(String name, Product product, StateTax stateTaxRate, String area, LocalDate date) {
        return dao.createPurchaseOrder(name, product, stateTaxRate, area, date);
    }

    @Override
    public void addOrderToOrdersMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException {
        dao.addOrderToOrderMap(po);
    }

    @Override
    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException {
        return dao.getOrders(date);
    }
}
