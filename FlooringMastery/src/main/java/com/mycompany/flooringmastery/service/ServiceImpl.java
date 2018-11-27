/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.AuditDao;
import com.mycompany.flooringmastery.dao.Dao;

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
}
