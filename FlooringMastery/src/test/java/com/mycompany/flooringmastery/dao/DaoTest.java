/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.dao;

import com.mycompany.flooringmastery.model.Product;
import com.mycompany.flooringmastery.model.PurchaseOrder;
import com.mycompany.flooringmastery.model.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class DaoTest {

    private final Dao dao;
    HashMap<String, BigDecimal[]> products = new HashMap<>();
    HashMap<String, BigDecimal> states = new HashMap<>();
    HashMap<LocalDate, HashMap<String, PurchaseOrder>> ordersMap = new HashMap<>();

    public DaoTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        dao = ctx.getBean("daoImpl", DaoImplProduction.class);
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
     * Test of validateProduct method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.DataValidationException
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     */
    @Test
    public void testValidateProduct() throws DataValidationException, FlooringMasteryPersistenceError {
        dao.loadFiles();
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        assertEquals(wood, dao.createProduct("wood"));
    }

    /**
     * Test of validateState method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.DataValidationException
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     */
    @Test
    public void testValidateState() throws DataValidationException, FlooringMasteryPersistenceError {
        dao.loadFiles();
        StateTax test = new StateTax("OH", new BigDecimal("6.25"));
        assertEquals(test, dao.createState("OH"));
    }

    /**
     * Test of loadFiles method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     * @throws com.mycompany.flooringmastery.dao.DataValidationException
     */
    @Test
    public void testLoadFiles() throws FlooringMasteryPersistenceError, DataValidationException {
        // Before loading
        try {
            dao.createProduct("wood");
            fail("We don't have that product");
        } catch (DataValidationException e) {

        }

        // After loading
        dao.loadFiles();
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        assertEquals(wood, dao.createProduct("wood"));
    }

    /**
     * Test of createPurchaseOrder method, of class Dao.
     */
    @Test
    public void testCreatingPurchaseOrder() {
        StateTax test = new StateTax("OH", new BigDecimal("6.25"));
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        LocalDate christmas = LocalDate.parse("12-25-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        PurchaseOrder po = new PurchaseOrder("Santa", wood, test, "500", christmas);
        assertEquals(po, dao.createPurchaseOrder("Santa", wood, test, "500", christmas));
    }

    /**
     * Test of addOrderToOrderMap method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     * @throws com.mycompany.flooringmastery.dao.DateNotFoundException
     */
    @Test
    public void testAddOrderToOrderMap() throws FlooringMasteryPersistenceError, DateNotFoundException {
        assertEquals(0, ordersMap.size());
        StateTax test = new StateTax("OH", new BigDecimal("6.25"));
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        LocalDate christmas = LocalDate.parse("12-25-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        PurchaseOrder po = new PurchaseOrder("Santa", wood, test, "500", christmas);
        dao.addOrderToOrderMap(po);
        assertEquals(1, dao.getOrders("12-25-2000").size());

    }

    /**
     * Test of getOrders method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     * @throws com.mycompany.flooringmastery.dao.DateNotFoundException
     */
    @Test
    public void testGetOrders() throws FlooringMasteryPersistenceError, DateNotFoundException {
        int orders = 3;
        assertEquals(orders, dao.getOrders("12-25-2017").size());
    }

    /**
     * Test of getStatesServiced method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     */
    @Test
    public void testGetStatesServiced() throws FlooringMasteryPersistenceError {
        dao.loadFiles();
        assertEquals(5, dao.getStatesServiced().size());
    }

    /**
     * Test of getProductsOffered method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     */
    @Test
    public void testGetProductsOffered() throws FlooringMasteryPersistenceError {
        dao.loadFiles();
        assertEquals(4, dao.getProductsOffered().size());
    }

    /**
     * Test of removeOrder method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     * @throws com.mycompany.flooringmastery.dao.DateNotFoundException
     */
    @Test
    public void testRemoveOrder() throws FlooringMasteryPersistenceError, DateNotFoundException {
        StateTax test = new StateTax("OH", new BigDecimal("6.25"));
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        LocalDate christmas = LocalDate.parse("12-25-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        PurchaseOrder po = new PurchaseOrder("Santa", wood, test, "500", christmas);
        dao.addOrderToOrderMap(po);
        assertEquals(1, dao.getOrders("12-25-2000").size());
        dao.removeOrder("12-25-2000", "1");
        //assertEquals(0, dao.getOrders("12-25-2000").size());
    }

    /**
     * Test of getOrder method, of class Dao.
     *
     * @throws com.mycompany.flooringmastery.dao.FlooringMasteryPersistenceError
     * @throws com.mycompany.flooringmastery.dao.DateNotFoundException
     * @throws com.mycompany.flooringmastery.dao.DataValidationException
     */
    @Test
    public void testGetOrder() throws FlooringMasteryPersistenceError, DateNotFoundException, DataValidationException {
        StateTax test = new StateTax("OH", new BigDecimal("6.25"));
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        LocalDate christmas = LocalDate.parse("12-25-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        PurchaseOrder po = new PurchaseOrder("Santa", wood, test, "500", christmas);
        dao.addOrderToOrderMap(po);
        assertEquals(po, dao.getOrder("12-25-2000", "1"));
    }

    /**
     * Test of updatePO method, of class Dao.
     */
    @Test
    public void testUpdatePO() {
        StateTax test = new StateTax("OH", new BigDecimal("6.25"));
        Product wood = new Product("wood", new BigDecimal("5.15"), new BigDecimal("4.75"));
        LocalDate christmas = LocalDate.parse("12-25-2000", DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        PurchaseOrder po = new PurchaseOrder("Santa", wood, test, "500", christmas);
        dao.updatePO("Ms. Clause", wood, test, "250", po);
        assertEquals("Ms. Clause", po.getCustomerName());
        assertEquals(new BigDecimal("250"), po.getArea());
    }

}
