/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Purchase;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chaseowens
 */
public class PurchaseServiceTest {
    
    public PurchaseServiceTest() {
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
     * Test of createPurchase method, of class PurchaseService.
     */
    @Test
    public void testCreatePurchase() throws Exception {
    }

    /**
     * Test of getSalesSumByUserId method, of class PurchaseService.
     */
    @Test
    public void testGetSalesSumByUserId() {
    }

    /**
     * Test of getTotalNumberOfSalesByUserId method, of class PurchaseService.
     */
    @Test
    public void testGetTotalNumberOfSalesByUserId() {
    }

    public class PurchaseServiceImpl implements PurchaseService {

        public Purchase createPurchase(int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, BigDecimal salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError {
            return null;
        }

        public BigDecimal getSalesSumByUserId(int id, LocalDate startingOn, LocalDate to) {
            return null;
        }

        public int getTotalNumberOfSalesByUserId(int id, LocalDate startingOn, LocalDate to) {
            return 0;
        }
    }
    
}
