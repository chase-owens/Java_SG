/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.AuditDao;
import com.mycompany.vendingmachine.dao.AuditDaoImpl;
import com.mycompany.vendingmachine.dao.Dao;
import com.mycompany.vendingmachine.dao.DaoStubImpl;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author chaseowens
 */
public class ServiceTest {
    Dao dao = new DaoStubImpl();
    AuditDao audit = new AuditDaoImpl();
    
    Service service = new ServiceImpl(dao, audit);
    
//    public ServiceTest(Dao dao, AuditDao audit) {
//        this.dao = dao;
//        this.audit = audit;
//    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getItems method, of class Service.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetItems() throws Exception {
        assertEquals(1, service.getItems().size());
    }

    /**
     * Test of processTransaction method, of class Service.
     * @throws java.lang.Exception
     */
    @Test
    public void testProcessTransaction() throws Exception {
        assertEquals(BigDecimal.ZERO, service.processTransaction(BigDecimal.TEN,"dummy"));
    }

    /**
     * Test of makeChange method, of class Service.
     */
    @Test
    public void testMakeChange() {
        assertEquals(1, service.makeChange(BigDecimal.TEN).getTen());
    }
    
}
