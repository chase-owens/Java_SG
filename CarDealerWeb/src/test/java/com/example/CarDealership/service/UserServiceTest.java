/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.User;
import java.util.List;
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
public class UserServiceTest {
    
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

    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testCreateUser() throws Exception {
    }

    /**
     * Test of readAllUsers method, of class UserService.
     */
    @Test
    public void testReadAllUsers() {
    }

    /**
     * Test of readUserByid method, of class UserService.
     */
    @Test
    public void testReadUserByid() {
    }

    /**
     * Test of updateUser method, of class UserService.
     */
    @Test
    public void testUpdateUser() throws Exception {
    }

    public class UserServiceImpl implements UserService {

        public User createUser(String firstName, String lastName, String phone, String email, String role, String password1, String password2, int adminId) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError {
            return null;
        }

        public List<User> readAllUsers() {
            return null;
        }

        public User readUserByid(int id) {
            return null;
        }

        public void updateUser(int userId, String firstName, String lastName, String phone, String email, String role, String password1, String password2, int adminId) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError {
        }
    }
    
}
