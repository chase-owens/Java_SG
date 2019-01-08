/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Profile;
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
public class ProfileDaoTest {
    @Autowired
    ProfileDao dao;
    
    public ProfileDaoTest() {
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
    public void testCreateProfile() {
        // Arrange
        String name = "Chase", email = "youWish@icloud.com", phone = "214-916-0963";
        
        // Act
        Profile profileCreated = dao.createProfile(name, email, phone);
        
        // Assert
        assertEquals(name, profileCreated.getFullName());
        assertEquals(email, profileCreated.getEmail());
        assertEquals(phone, profileCreated.getNumber());
    }
    
    @Test
    public void testUpdateProfile() {
        // Arrange
        
        // Create new profile, update its properties, update in DB, retreive profile
        String name = "Chase", email = "youWish@icloud.com", phone = "214-916-0963";
        Profile profileCreated = dao.createProfile(name, email, phone);
        
        String newName = "Jonathan", newEmail = "jon@icloud.com", newPhone = "911";
        
        profileCreated.setEmail(newEmail);
        profileCreated.setFullName(newName);
        profileCreated.setNumber(newPhone);
        dao.updateProfile(profileCreated);
        
        // Act
        Profile profileRetrieved = dao.readProfileById(profileCreated.getProfileId());
        
        // Assert
        assertEquals(profileRetrieved.getEmail(), newEmail);
        assertEquals(profileRetrieved.getFullName(), newName);
        assertEquals(profileRetrieved.getNumber(), newPhone);
    }
    
    
    
}
