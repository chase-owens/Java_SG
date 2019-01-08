/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Contact;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chaseowens
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class ContactServiceTest {
    @Autowired
    ContactService service;
    
    public ContactServiceTest() {
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
     * Test of makeContact method, of class ContactService.
     * @throws com.example.CarDealership.service.NeedContactNameError
     * @throws com.example.CarDealership.service.NeedContactDetailsError
     * @throws com.example.CarDealership.service.NeedContactMessageError
     */
    @Test
    public void testMakeContact() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        
        //Arrange
        String name = "name", email = "email", phone = "phone", message = "message";
        
        //Act
        Contact contact = service.makeContact(name, email, phone, message);
        
        //Assert
        assertEquals(contact.getMessage(), message);
        assertEquals(contact.getProfile().getEmail(), email);
        assertEquals(contact.getProfile().getFullName(), name);
        assertEquals(contact.getProfile().getNumber(), phone);
        
    }
    
        @Test
    public void testMakeContactWithoutEmail() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        
        //Arrange
        String name = "name", email = "", phone = "phone", message = "message";
        
        //Act
        Contact contact = service.makeContact(name, email, phone, message);
        
        //Assert
        assertEquals(contact.getMessage(), message);
        assertEquals(contact.getProfile().getEmail(), email);
        assertEquals(contact.getProfile().getFullName(), name);
        assertEquals(contact.getProfile().getNumber(), phone);
        
    }
    
    @Test
    public void testMakeContactWithoutName() throws NeedContactDetailsError, NeedContactMessageError {
                
        //Arrange
        String name = "", email = "email", phone = "phone", message = "message";
        
        try {
            Contact contact = service.makeContact(name, email, phone, message);
            fail("Should have thrown NeedContactNameError");
        } catch (NeedContactNameError e) {
            
        }
        
    }
    
    @Test
    public void testMakeContactWithoutMessage() throws NeedContactNameError, NeedContactDetailsError {
                
        //Arrange
        String name = "name", email = "email", phone = "phone", message = "";
        
        try {
            Contact contact = service.makeContact(name, email, phone, message);
            fail("Should have thrown NeedContactMessageError");
        } catch (NeedContactMessageError e) {
            
        }
        
    }
    
    @Test
    public void testMakeContactWithoutContactDetailss() throws NeedContactNameError, NeedContactMessageError {
                
        //Arrange
        String name = "name", email = "", phone = "", message = "message";
        
        try {
            Contact contact = service.makeContact(name, email, phone, message);
            fail("Should have thrown NeedContactDetailsError");
        } catch (NeedContactDetailsError e) {
            
        }
        
    }
    
}
