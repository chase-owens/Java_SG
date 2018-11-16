/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author chaseowens
 */
public class AuditDaoImpl implements AuditDao {
    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceError {
        PrintWriter read;
        
        try {
            read = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceError("Could not find audit file", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        read.println(timestamp.toString() + " : " + entry);
    }
}
