/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.User;
import com.example.CarDealership.service.NeedContactDetailsError;
import com.example.CarDealership.service.NeedContactMessageError;
import com.example.CarDealership.service.NeedContactNameError;
import com.example.CarDealership.service.ProfileService;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author chaseowens
 */
public class UserDaoTest {
    @Autowired
    UserDao dao;
    
    @Autowired
    ProfileService profileService;
    
    public UserDaoTest() {
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
    public void testCreateUser() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        
        //Act
        User user = dao.createUser(profile, role, password, adminId);
        
        //Assert
        assertEquals(user.getRole(), role);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getAdminId(), adminId);
        assertEquals(user.getProfile().getFullName(), "name");
    }
    
    @Test
    public void testGetUsersEmpty() {
        //Act
        List<User> users = dao.readAllUsers();
        
        //Assert
        assertEquals(0, users.size());
    }
    
    @Test
    public void testGetUsers() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        
        Profile profile2 = profileService.createProfile("name2", "email2", "phone2");
        String role2 = "role2", password2 = "password2";
        int adminId2 = 1;
       
        User user = dao.createUser(profile, role, password, adminId);
        User user2 = dao.createUser(profile2, role2, password2, adminId2);
        
        //Act
        List<User> users = dao.readAllUsers();
        
        //Assert
        assertEquals(2, users.size());
        assertEquals(users.get(0).getAdminId(), users.get(1).getAdminId());
        assertNotEquals(users.get(0).getPassword(), users.get(1).getPassword());
        
    }
    
    @Test
    public void testGetUserById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
       
        User user = dao.createUser(profile, role, password, adminId);
        
        //Act
        User userRetrieved = dao.readUserById(user.getUserId());
        
        // Assert
        assertEquals(userRetrieved.getPassword(), password);
        assertEquals(userRetrieved.getRole(), role);
    }
    
    @Test
    public void testUpdateUser() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = dao.createUser(profile, role, password, adminId);
        user.setRole("newRole");
        
        //Act
        dao.updateUser(user);
        User userUpdated = dao.readUserById(user.getUserId());
        
        //Assert
        assertNotEquals(user.getRole(), userUpdated.getRole());
        
    } 
    
}
