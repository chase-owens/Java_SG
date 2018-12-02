/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.PurchaseOrder;

/**
 *
 * @author chaseowens
 */
public class AuditDaoImplStub implements AuditDao {
    
    @Override
    public void writeAuditEntry(String displayOrders, String date) throws FlooringMasteryPersistenceError {
        // Smile
    }

    @Override
    public void writeAuditEntry(String addOrder, String date, PurchaseOrder po) throws FlooringMasteryPersistenceError {
        // Smile
    }

    @Override
    public void writeAuditEntry(String editFile, String date, String orderNumber) throws FlooringMasteryPersistenceError {
        // Smile
    }

    @Override
    public void writeAuditEntry(String saveOrders) throws FlooringMasteryPersistenceError {
        // Smile
    }
}
