/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.User;
import com.example.CarDealership.service.NeedContactDetailsError;
import com.example.CarDealership.service.NeedContactMessageError;
import com.example.CarDealership.service.NeedContactNameError;
import com.example.CarDealership.service.ProfileService;
import com.example.CarDealership.service.UserService;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
public class MakeDaoTest {
    @Autowired
    MakeDao makeDao;
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;
    
    public MakeDaoTest() {
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
    public void testCreateMake() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        
        //Set up test Make
        Make testMake = new Make();
        testMake.setCreatedBy(user);
        testMake.setMakeName(testName);
        
        //Act
        Make makeCreated = makeDao.createMake(testName, user.getUserId());
        
        //Assert
        assertEquals(testMake.getCreatedBy().getUserId(), makeCreated.getCreatedBy().getUserId());
        assertEquals(testMake.getMakeName(), makeCreated.getMakeName());
        assertNotEquals(testMake.getMakeId(), makeCreated.getMakeId());
        
    }
    
    @Test
    public void testGetMakesWithoutAny() {
        //Act
        List<Make> makes = makeDao.readAllMakes();
        
        //Assert
        assertEquals(0, makes.size());
    }
    
    @Test
    public void testGetMakes() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeDao.createMake(testName, user.getUserId());
        
        //Act
        List<Make> makes = makeDao.readAllMakes();
        
        //Assert
        assertEquals(1, makes.size());
        
    }
    
    @Test
    public void testReadMakeById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeDao.createMake(testName, user.getUserId());
        
        //Act
        Make makeRetrieved = makeDao.readMakeById(makeCreated.getMakeId());
        
        //Assert
        assertEquals(makeRetrieved.getCreatedBy().getUserId(), makeCreated.getCreatedBy().getUserId());
        assertEquals(makeRetrieved.getMakeName(), makeCreated.getMakeName());
        assertEquals(makeRetrieved.getMakeId(), makeCreated.getMakeId());
    }
    
    @Test
    public void updateMake() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeDao.createMake(testName, user.getUserId());
        makeCreated.setMakeName("test");
        
        //Act
        makeDao.updateMake(makeCreated);
        Make makeRetrieved = makeDao.readMakeById(makeCreated.getMakeId());
        
        //Assert
        assertEquals(makeRetrieved.getCreatedBy().getUserId(), makeCreated.getCreatedBy().getUserId());
        assertNotEquals(makeRetrieved.getMakeName(), testName);
        assertEquals(makeRetrieved.getMakeName(), "test");
        assertEquals(makeRetrieved.getMakeId(), makeCreated.getMakeId());
    }
    
}
