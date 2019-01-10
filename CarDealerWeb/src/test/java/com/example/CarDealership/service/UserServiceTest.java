/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.User;
import java.util.List;
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
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;
    
    public UserServiceTest() {
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
        
        //Act
        User user = userService.createUserWithProfile(profile, role, password);
        
        //Assert
        assertEquals(user.getRole(), role);
        assertEquals(user.getPassword(), password);
        assertEquals(user.getProfile().getFullName(), "name");
    }
    
    @Test
    public void testGetUsersEmpty() {
        //Act
        List<User> users = userService.readAllUsers();
        
        //Assert
        assertEquals(0, users.size());
    }
    
    @Test
    public void testGetUsers() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        
        Profile profile2 = profileService.createProfile("name2", "email2", "phone2");
        String role2 = "role2", password2 = "password2";
       
        User user = userService.createUserWithProfile(profile, role, password);
        User user2 = userService.createUserWithProfile(profile2, role2, password2);
        
        //Act
        List<User> users = userService.readAllUsers();
        
        //Assert
        assertEquals(2, users.size());
        assertNotEquals(users.get(0).getRole(), users.get(1).getRole());
        assertNotEquals(users.get(0).getPassword(), users.get(1).getPassword());
        
    }
    
    @Test
    public void testGetUserById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
       
        User user = userService.createUserWithProfile(profile, role, password);
        
        //Act
        User userRetrieved = userService.readUserById(user.getUserId());
        
        // Assert
        assertEquals(userRetrieved.getPassword(), password);
        assertEquals(userRetrieved.getRole(), role);
    }
    
    @Test
    public void testUpdateUser() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, PasswordsNotMatchingError {
        //Arrange
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password1 = "password1", password2 = "password1", name = "name", email = "email", phone = "phone";
        User user = userService.createUserWithProfile(profile, role, password1);
        String newRole = "newRole";
        user.setRole(newRole);
        
        //Act
        userService.updateUser(user.getUserId(), name, name, phone, email, newRole, password1, password2);
        User userUpdated = userService.readUserById(user.getUserId());
        
        //Assert
        assertNotEquals(role, userUpdated.getRole());
        assertEquals(newRole, userUpdated.getRole());
        
    }
    
     @Test
    public void testPasswordNotMatchingError() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, PasswordsNotMatchingError {
        //Arrange
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password1 = "password1", password2 = "password2", name = "name", email = "email", phone = "phone";
        User user = userService.createUserWithProfile(profile, role, password1);
        String newRole = "newRole";
        user.setRole(newRole);
        
        //Act
        try {
            userService.updateUser(user.getUserId(), name, name, phone, email, role, password1, password2);
            fail("Should not have worked because passwords match");
        } catch(PasswordsNotMatchingError e) {
            
        }
        
        
    }


    
}
