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
import java.util.Set;

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
    public StateTax createState(String stateEntered) throws DataValidationException {
        return dao.createState(stateEntered);
    }

    @Override
    public Product createProduct(String productNameEntered)  throws DataValidationException  {
        return dao.createProduct(productNameEntered);
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
    public PurchaseOrder addOrderToOrdersMap(PurchaseOrder po) throws FlooringMasteryPersistenceError, DateNotFoundException {
        return dao.addOrderToOrderMap(po);
    }

    @Override
    public HashMap<String, PurchaseOrder> getOrders(String date) throws FlooringMasteryPersistenceError, DateNotFoundException {
        return dao.getOrders(date);
    }

    @Override
    public Set<String> getStatesServiced() {
        return dao.getStatesServiced();
    }

    @Override
    public Set<String> getProductsOffered() {
        return dao.getProductsOffered();
    }

    @Override
    public void removeOrder(String date, String orderNumber) throws FlooringMasteryPersistenceError, DateNotFoundException {
        dao.removeOrder(date, orderNumber);
    }

    @Override
    public PurchaseOrder getOrder(String date, String orderNumber) throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException {
        return dao.getOrder(date, orderNumber);
    }

    @Override
    public PurchaseOrder updatePO(String name, Product product, StateTax stateTaxRate, String area, PurchaseOrder po) {
        return dao.updatePO(name, product, stateTaxRate, area, po);
    }

    @Override
    public void saveOrders() throws FlooringMasteryPersistenceError {
        dao.saveOrders();
    }

    @Override
    public void audit(String displayOrders, String date) throws FlooringMasteryPersistenceError {
        //auditDao.writeAuditEntry(displayOrders, date);
    }

    @Override
    public void audit(String addOrder, String date, PurchaseOrder po) throws FlooringMasteryPersistenceError {
        //auditDao.writeAuditEntry(addOrder, date, po);
    }

    @Override
    public void audit(String editFile, String date, String orderNumber) throws FlooringMasteryPersistenceError {
        //auditDao.writeAuditEntry(editFile, date, orderNumber);
    }

    @Override
    public void audit(String saveOrders) throws FlooringMasteryPersistenceError {
        //auditDao.writeAuditEntry(saveOrders);
    }

    @Override
    public void loadOrders(String date) throws FlooringMasteryPersistenceError {
        dao.loadOrders(date);
    }

    @Override
    public boolean validate(String entry, Set<String> alternatives) {
        boolean valid = false;
        
        for (String availableAlternative : alternatives) {
            if (entry.toLowerCase().equals(availableAlternative)) {
                valid = true;
            } else {
                valid = false;
            }
        }
        return valid;
    }
}
