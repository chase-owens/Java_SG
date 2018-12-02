/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.service;

import com.mycompany.flooringmastery.dao.DataValidationException;
import com.mycompany.flooringmastery.dao.DateNotFoundException;
import com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError;
import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chaseowens
 */
public class ServiceTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    Service service = ctx.getBean("serviceLayer", ServiceImpl.class);

    public ServiceTest() {
    }

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
     * Test of validateState method, of class Service.
     *
     * @throws com.mycompany.flooringmastery.dao.DataValidationException
     */
    @Test
    public void testValidateState() throws DataValidationException {
        
        StateTax dummyState = new StateTax("OH", new BigDecimal("6.25"));
        assertEquals(dummyState, service.validateState("OH"));
        
        try {
            service.validateState("TX");
            fail("We don't serve that state");
        } catch (DataValidationException e) {

        }

    }

    /**
     * Test of validateProduct method, of class Service.
     *
     * @throws com.mycompany.flooringmastery.dao.DataValidationException
     */
    @Test
    public void testValidateProduct() throws DataValidationException {
        
        Product dummyProduct = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        assertEquals(dummyProduct, service.validateProduct("wood"));
        
        try {
            service.validateProduct("slate");
            fail("We don't have that product");
        } catch (DataValidationException e) {

        }
    }

    /**
     * Test of createPurchaseOrder method, of class Service.
     */
    @Test
    public void testCreatePurchaseOrder() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2000", df);
        StateTax ohio = new StateTax("OH", new BigDecimal("6.25"));
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        PurchaseOrder dummyPO = new PurchaseOrder("Santa", wood, ohio, "100", christmas);
        
        assertEquals(dummyPO, service.createPurchaseOrder("Santa", wood, ohio, "100", christmas));
        assertNull(service.createPurchaseOrder("Ms. Clause", wood, ohio, "100", christmas));
        
    }

    /**
     * Test of addOrderToOrdersMap method, of class Service.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     * @throws com.mycompany.flooringmastery.dao.DateNotFoundException
     */
    @Test
    public void testAddOrderToOrdersMapandGetOrders() throws FlooringMasteryPersistenceError, DateNotFoundException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2000", df);
        StateTax ohio = new StateTax("OH", new BigDecimal("6.25"));
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        PurchaseOrder dummyPO = new PurchaseOrder("Santa", wood, ohio, "100", christmas);
        dummyPO.setOrderNumber("1");
        
        // Before adding to map
        try {
            service.getOrders(christmas.toString());
            fail("Could not find that date");
        } catch(DateNotFoundException e) {
            
        }
        
        service.addOrderToOrdersMap(dummyPO);
        // After adding to map
        assertEquals(dummyPO, service.addOrderToOrdersMap(dummyPO));
        
        
        
    }

    /**
     * Test of getOrder method, of class Service.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     * @throws com.mycompany.flooringmastery.dao.DateNotFoundException
     */
    @Test
    public void testGetOrder() throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2000", df);
        StateTax ohio = new StateTax("OH", new BigDecimal("6.25"));
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        PurchaseOrder dummyPO = new PurchaseOrder("Santa", wood, ohio, "100", christmas);
        dummyPO.setOrderNumber("1");
        
        assertEquals(dummyPO, service.getOrder("12-25-2000", "1"));
        
        try {
            service.getOrder("12-25-2000", "2");
            fail("That order number does not exist");
        } catch (DataValidationException e) {
            
        }
        
        try {
            service.getOrder("12-24-2000", "1");
            fail("Could not find that date");
        } catch (DateNotFoundException e) {
            
        }
    }

}
