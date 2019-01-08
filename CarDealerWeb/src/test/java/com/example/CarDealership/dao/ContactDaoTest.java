/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Contact;
import com.example.CarDealership.entity.Profile;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class ContactDaoTest {
    @Autowired
    ContactDao dao;
    
    @Autowired
    ProfileDao profileDao;
    
    public ContactDaoTest() {
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

    @Test
    public void testCreateContact() {
        
        // Arrange
        
        Profile profile = profileDao.createProfile("name", "email", "phone");
        String message = "TestContact";
        
        // Act 
        Contact contactCreated = dao.createContact(profile, message);
        
        // Assert
        assertEquals(contactCreated.getMessage(), message);
        assertEquals(contactCreated.getProfile().getFullName(), "name");
        assertEquals(contactCreated.getProfile().getNumber(), "phone");
        assertEquals(contactCreated.getProfile().getEmail(), "email");
    }
    
    @Test
    public void testGetEmptyContats() {
        assertEquals(0, dao.readAllContacts().size());
    }
    
    @Test
    public void testGetContacts() {
        // Arrange
        Profile profile = profileDao.createProfile("name", "email", "phone");
        String message = "TestContact";
        Contact contact1 = dao.createContact(profile, message);
        
        Profile profile2 = profileDao.createProfile("name", "email", "phone");
        String message2 = "TestContact";
        Contact contact2 = dao.createContact(profile2, message2);
        
        // Act 
        List<Contact> contacts = dao.readAllContacts();
        
        // Assert
        assertEquals(2, dao.readAllContacts().size());
    }
    
    @Test
    public void testGetContactById() {
        // Arrange
        Profile profile = profileDao.createProfile("name", "email", "phone");
        String message = "TestContact";
        Contact contact1 = dao.createContact(profile, message);
        
        // Act
        Contact contactRetrieved = dao.readContactById(contact1.getContactId());
        
        // Assert
        assertEquals(contactRetrieved.getMessage(), contact1.getMessage());
        assertEquals(contactRetrieved.getProfile().getEmail(), "email");
        assertEquals(contactRetrieved.getProfile().getNumber(), "phone");
    }
    
}
