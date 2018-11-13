/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.greatparty;

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
public class GreatPartyTest {
    
    GreatParty party = new GreatParty();
    
    public GreatPartyTest() {
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
    
    @Test // Just below floor
    public void test39False() {
        assertFalse(party.checkSuccess(39, false));
    }
    
    @Test // Just above weekday ceiling
    public void test61False() {
        assertFalse(party.checkSuccess(61, false));
    }
    
    @Test // Make sure no weekend ceiling
    public void test61True() {
        assertTrue(party.checkSuccess(40, true));
    }
    
    @Test // Lower limit
    public void test40False() {
        assertTrue(party.checkSuccess(40, false));
    }
    
    @Test // Upper limit
    public void test60False() {
        assertTrue(party.checkSuccess(60, false));
    }
    
}
