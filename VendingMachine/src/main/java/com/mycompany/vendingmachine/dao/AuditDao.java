/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

/**
 *
 * @author chaseowens
 */
public interface AuditDao {
    public void writeAuditEntry(String entry, String report) throws VendingMachinePersistenceError;
}
