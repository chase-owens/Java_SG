/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.classroster.advice;

import mycompany.classroster.dao.ClassRosterAuditDAO;
import mycompany.classroster.dto.ClassRosterPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author chaseowens
 */
public class LoggingAdvice {
    ClassRosterAuditDAO auditDao;
    
    public LoggingAdvice(ClassRosterAuditDAO auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " : ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (ClassRosterPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice");
        }
    }
}
