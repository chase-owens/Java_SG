/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.advice;

import com.mycompany.flooringmastery.dao.AuditDao;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author chaseowens
 */
public class LoggingAdvice {
    AuditDao auditDao;
    
    public LoggingAdvice(AuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = " ";
        for (Object arg : args) {
            auditEntry += arg += " : ";
        }
        
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasteryPersistenceError e) {
            System.err.println(e.getMessage());
        }
    }
}
