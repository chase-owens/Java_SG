/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.AuditDao;
import com.mycompany.vendingmachine.dao.Dao;
import com.mycompany.vendingmachine.dao.GetEntryError;
import com.mycompany.vendingmachine.dao.GettingMoneyError;
import com.mycompany.vendingmachine.dao.InsufficientFundsError;
import com.mycompany.vendingmachine.dao.OutOfStockException;
import com.mycompany.vendingmachine.dao.VendingMachinePersistenceError;
import com.mycompany.vendingmachine.dto.ChangeMaker;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class ServiceImpl implements VMService {
    @Autowired
    Dao dao;
    @Autowired
    AuditDao audit;
    
    public ServiceImpl(Dao injectedDao, AuditDao injectedAudit) {
        this.dao = injectedDao;
        this.audit = injectedAudit;
    }

    @Override
    public Collection<Item> getItems() throws VendingMachinePersistenceError {
        return dao.getItems();
    }

    @Override
    public BigDecimal processTransaction(BigDecimal $, String selection) throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError  {
        return dao.processTransaction($, selection);
    }

    @Override
    public ChangeMaker makeChange(BigDecimal change) {
        return dao.makeChange(change);
    }

    @Override
    public void auditFile(String report) throws VendingMachinePersistenceError {
        //audit.writeAuditEntry(selection, report);
    }

    @Override
    public Item getItem(String selection) {
        return dao.getItem(selection);
    }

    @Override
    public BigDecimal checkMoney(String cash) throws GettingMoneyError {
        return dao.checkMoney(cash);
    }
}
