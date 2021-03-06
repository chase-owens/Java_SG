/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.dao.DataPersistenceError;
import com.example.CarDealership.entity.Profile;
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
public class ProfileServiceTest {
    @Autowired
    ProfileService service;
    
    public ProfileServiceTest() {
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
    public void testCreateProfile() throws NeedContactNameError, NeedContactDetailsError {
        // Arrange
        String name = "Chase", email = "youWish@icloud.com", phone = "214-916-0963";
        
        // Act
        Profile profileCreated = service.createProfile(name, email, phone);
        
        // Assert
        assertEquals(name, profileCreated.getFullName());
        assertEquals(email, profileCreated.getEmail());
        assertEquals(phone, profileCreated.getNumber());
    }

    @Test
    public void testCreateProfileWithoutName() throws NeedContactDetailsError {
        // Arrange
        String name = "", email = "youWish@icloud.com", phone = "214-916-0963";
        
        // Act
        try {
            Profile profileCreated = service.createProfile(name, email, phone);
            fail("Should have thrown need ProfileName error");
        } catch(NeedContactNameError e) {
        }
    }
    
    @Test
    public void testCreateProfileWithoutContactDetails() throws NeedContactNameError {
        // Arrange
        String name = "Chase", email = "", phone = "";
        
        // Act
        try {
            Profile profileCreated = service.createProfile(name, email, phone);
            fail("Should have thrown need NeedContactDetailsError");
        } catch(NeedContactDetailsError e) {
        }
    }
    
    @Test
    public void testUpdateProfileWithoutContactDetails() throws NeedContactNameError, NeedContactDetailsError {
        // Arrange
        
        // Create new profile, update its properties, update in DB, retreive profile
        String name = "Chase", email = "youWish@icloud.com", phone = "214-916-0963";
        Profile profileCreated = service.createProfile(name, email, phone);
        String newName = "Jonathan", newEmail = "", newPhone = "";

        try {
            service.updateProfile(profileCreated.getProfileId(), newName, newEmail, newPhone);
            fail("Should have thrown NeedContactDetailsError");
        } catch (NeedContactDetailsError | DataPersistenceError e) {
            
        }
    }
    
        @Test
    public void testUpdateProfileWithoutContactName() throws NeedContactNameError, NeedContactDetailsError {
        // Arrange
        
        // Create new profile, update its properties, update in DB, retreive profile
        String name = "Chase", email = "youWish@icloud.com", phone = "214-916-0963";
        Profile profileCreated = service.createProfile(name, email, phone);
        String newName = "", newEmail = "chase@icloud.com", newPhone = "214-916-0963";

        try {
            service.updateProfile(profileCreated.getProfileId(), newName, newEmail, newPhone);
            fail("Should have thrown NeedContactDetailsError");
        } catch (NeedContactNameError | DataPersistenceError e) {
            
        }
    }
    
}
