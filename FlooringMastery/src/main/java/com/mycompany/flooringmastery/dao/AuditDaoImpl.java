/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.google.gson.Gson;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author chaseowens
 */
public class AuditDaoImpl implements AuditDao {

    public static final String AUDIT_FILE = "audit.txt";
    PrintWriter write;
    LocalDateTime datestamp = LocalDateTime.now();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    LocalTime timestamp = LocalTime.now();
    DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm");

    @Override
    public void writeAuditEntry(String report) throws FlooringMasteryPersistenceError {
        Gson gson = new Gson();
        try {
            write = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceError("Could not audit file", e);
        }
        
        String entry = datestamp.format(df) + " : " + timestamp.format(tf) + " : " + report; 
        write.println(gson.toJson(entry));
        write.flush();
        write.close();
    }
    
    @Override
    public void writeAuditEntry(String report, String date) throws FlooringMasteryPersistenceError {
        Gson gson = new Gson();
        try {
            write = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceError("Could not audit file", e);
        }
        
        String entry = datestamp.format(df) + " : " + timestamp.format(tf) + " : " + report + " : " + date; 
        write.println(gson.toJson(entry));
        write.flush();
        write.close();
    }
    
    @Override
    public void writeAuditEntry(String report, String date, String detail) throws FlooringMasteryPersistenceError {
        Gson gson = new Gson();
        try {
            write = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceError("Could not audit file", e);
        }
        
        String entry = datestamp.format(df) + " : " + timestamp.format(tf) + " : " + report + " : " + date + " : " + detail; 
        write.println(gson.toJson(entry));
        write.flush();
        write.close();
    }
    
    @Override
    public void writeAuditEntry(String report, String date, PurchaseOrder po) throws FlooringMasteryPersistenceError {
        Gson gson = new Gson();
        try {
            write = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceError("Could not audit file", e);
        }
        
        String entry = datestamp.format(df) + " : " + timestamp.format(tf) + " : " + report + " : " + date; 
        write.println(gson.toJson(entry) + gson.toJson(po));
        write.flush();
        write.close();
    }
}
