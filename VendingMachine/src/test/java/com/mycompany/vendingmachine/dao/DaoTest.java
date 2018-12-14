/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.TestApplicationConfiguration;
import com.mycompany.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author chaseowens
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class DaoTest {
    
    @Autowired
    Dao dao;
    //  = new DaoImpl()

//    public DaoTest() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        dao = ctx.getBean("daoImpl", DaoImpl.class);
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
     * Test of getItems method, of class Dao.
     *
     * @throws com.mycompany.vendingmachine.dao.VendingMachinePersistenceError
     */
    @Test
    public void testGetItems() throws VendingMachinePersistenceError {
        int inventorySize = 2;
        assertEquals(inventorySize, dao.getItems().size());
    }

    /**
     * Test of processTransaction method, of class Dao.
     *
     * @throws com.mycompany.vendingmachine.dao.InsufficientFundsError
     * @throws com.mycompany.vendingmachine.dao.OutOfStockException
     * @throws com.mycompany.vendingmachine.dao.VendingMachinePersistenceError
     * @throws com.mycompany.vendingmachine.dao.GetEntryError
     */
    @Test
    public void testProcessTransaction() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        dao.getInventory();
        BigDecimal unicornPrice = new BigDecimal("75000");
        assertEquals(BigDecimal.ZERO, dao.processTransaction(unicornPrice, "Unicorn"));
    }

    @Test
    public void testProcessTransactionErrorCatchingInsufficientFundsError() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        dao.getInventory();
        BigDecimal unicornPrice = new BigDecimal("74000");
        try {
            dao.processTransaction(unicornPrice, "Unicorn");
            fail("Insufficient Funds");
        } catch (InsufficientFundsError | OutOfStockException | VendingMachinePersistenceError | GetEntryError e) {
        }

    }

    @Test
    public void testProcessTransactionErrorCatchingOutOfStockException() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        dao.getInventory();
        BigDecimal amountPaid = new BigDecimal("900000");
        try {
            dao.processTransaction(amountPaid, "Trip to Mars");
            fail("Sorry none of those left");
        } catch (InsufficientFundsError | OutOfStockException | VendingMachinePersistenceError | GetEntryError e) {
        }

    }

    @Test
    public void testProcessTransactionErrorCatchingGetEntryError() throws InsufficientFundsError, OutOfStockException, VendingMachinePersistenceError, GetEntryError {
        dao.getInventory();
        BigDecimal amountEntered = new BigDecimal("-1");
        try {
            dao.processTransaction(amountEntered, "Unicorn");
            fail("Please enter the item as it appears in the display.");
        } catch (InsufficientFundsError | OutOfStockException | VendingMachinePersistenceError | GetEntryError e) {
        }

    }

    /**
     * Test of makeChange method, of class Dao.
     */
    @Test
    public void testMakeChange() {

        // .50 should retrun two quarters exacty not 5 dimes
        BigDecimal bd = new BigDecimal(".50");
        assertEquals(2, dao.makeChange(bd).getQuarter());
        assertNotEquals(5, dao.makeChange(bd).getDime());
    }

    @Test
    public void getInventory() throws VendingMachinePersistenceError {
        //Does nothing
    }

    @Test
    public void testGetItem() throws VendingMachinePersistenceError {

        // test is an exact replica of Item with name Trip to Mars in inventory
        Item test = new Item("Trip to Mars", new BigDecimal(150000), 0);
        dao.getInventory();
        assertEquals(test, dao.getItem(test.getName()));
    }

    @Test
    public void updateInventory() throws VendingMachinePersistenceError, InsufficientFundsError, OutOfStockException, GetEntryError {

        // Get a number of unicorn inventoryCount form database
        dao.getInventory();
        Item uBefore = dao.getItem("Unicorn");
        int uCountBefore = uBefore.getInventoryCount();

        // Call updateInventory through
        dao.processTransaction(new BigDecimal("76000"), "Unicorn");

        // get another number of unicorn inventoryCount form inventory after updateing inventory
        Item uAfter = dao.getItem("Unicorn");
        int uCountAfter = uAfter.getInventoryCount();

        // the test 
        assertNotEquals(uCountBefore, uCountAfter);

    }

    @Test
    public void checkMoney() throws GettingMoneyError {

        String test = "1000";
        assertEquals(new BigDecimal(test), dao.checkMoney(test));

        try {
            dao.checkMoney("asddf");
            fail("Please enter a real number without any letters or commas");
        } catch (GettingMoneyError e) {
        }

        try {
            dao.checkMoney("1,000");
            fail("Please enter a real number without any letters or commas");
        } catch (GettingMoneyError e) {
        }

        try {
            dao.checkMoney("-1");
            fail("Try that again and I will call the police and send them a photo of you...");
        } catch (GettingMoneyError e) {
        }

        try {
            dao.checkMoney("2125000001");
            fail("I see you big baller... That's so such money we don't know what to do with it. Please enter less than 2,125,000,000");
        } catch (GettingMoneyError e) {
        }
    }

}
