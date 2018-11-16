/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author chaseowens
 */
public class DaoTest {

    private final Dao dao = new DaoImpl();

    public DaoTest() {
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
     * Test of getItems method, of class Dao.
     *
     * @throws com.mycompany.vendingmachine.dao.VendingMachinePersistenceError
     */
    @Test
    public void testGetItems() throws VendingMachinePersistenceError {
        int inventorySize = 9;
        assertEquals(inventorySize, dao.getItems().size());
    }

    /**
     * Test of processTransaction method, of class Dao.
     *
     * @throws com.mycompany.vendingmachine.dao.InsufficientFundsError
     * @throws com.mycompany.vendingmachine.dao.OutOfStockException
     * @throws com.mycompany.vendingmachine.dao.VendingMachinePersistenceError
     */
    @Test
    public void testProcessTransaction() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        dao.getInventory();
        BigDecimal unicornPrice = new BigDecimal("75000");
        assertEquals(BigDecimal.ZERO, dao.processTransaction(unicornPrice, "Unicorn"));
    }

    @Test
    public void testProcessTransactionErrorCatchingInsufficientFundsError() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        dao.getInventory();
        BigDecimal unicornPrice = new BigDecimal("74000");
        try {
            dao.processTransaction(unicornPrice, "Unicorn");
            fail("Insufficient Funds");
        } catch (InsufficientFundsError | OutOfStockException | VendingMachinePersistenceError e) {
        }

    }

    @Test
    public void testProcessTransactionErrorCatchingOutOfStockException() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError {
        dao.getInventory();
        BigDecimal unicornPrice = new BigDecimal("900000");
        try {
            dao.processTransaction(unicornPrice, "Trip to Mars");
            fail("Sorry none of those left");
        } catch (InsufficientFundsError | OutOfStockException | VendingMachinePersistenceError e) {
        }

    }

    /**
     * Test of makeChange method, of class Dao.
     */
    @Test
    public void testMakeChange() {
        BigDecimal bd = new BigDecimal(".50");
        assertEquals(2, dao.makeChange(bd).getQuarter());
        assertNotEquals(2, dao.makeChange(bd).getHundred());
    }

}
