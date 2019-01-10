/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Make;
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
public class MakeServiceTest {

    @Autowired
    MakeService makeService;
    @Autowired
    ProfileService profileService;
    @Autowired
    UserService userService;

    public MakeServiceTest() {
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
    public void testCreateMake() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
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
        Make makeCreated = makeService.createMake(testName, user.getUserId());

        //Assert
        assertEquals(testMake.getCreatedBy().getUserId(), makeCreated.getCreatedBy().getUserId());
        assertEquals(testMake.getMakeName(), makeCreated.getMakeName());
        assertNotEquals(testMake.getMakeId(), makeCreated.getMakeId());

    }
    
    @Test
    public void testCreateMakeWithoutMakeName() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);

        //Set up test Make
        Make testMake = new Make();
        testMake.setCreatedBy(user);
        testMake.setMakeName(testName);

        //Act
        try {
            Make makeCreated = makeService.createMake(testName, user.getUserId());
            fail("Should not have created the make without name");
        } catch (DataValidationError e) {
            
        }
        

    }

    @Test
    public void testGetMakesWithoutAny() {
        //Act
        List<Make> makes = makeService.getAllMakes();

        //Assert
        assertEquals(0, makes.size());
    }

    @Test
    public void testGetMakes() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());

        //Act
        List<Make> makes = makeService.getAllMakes();

        //Assert
        assertEquals(1, makes.size());

    }

    @Test
    public void testReadMakeById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());

        //Act
        Make makeRetrieved = makeService.readMakeById(makeCreated.getMakeId());

        //Assert
        assertEquals(makeRetrieved.getCreatedBy().getUserId(), makeCreated.getCreatedBy().getUserId());
        assertEquals(makeRetrieved.getMakeName(), makeCreated.getMakeName());
        assertEquals(makeRetrieved.getMakeId(), makeCreated.getMakeId());
    }

    @Test
    public void updateMake() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        String newName = "test";

        //Act
        makeService.updateMake(newName, makeCreated.getMakeId());
        Make makeRetrieved = makeService.readMakeById(makeCreated.getMakeId());

        //Assert
        assertEquals(makeRetrieved.getCreatedBy().getUserId(), makeCreated.getCreatedBy().getUserId());
        assertNotEquals(makeRetrieved.getMakeName(), testName);
        assertEquals(makeRetrieved.getMakeName(), "test");
        assertEquals(makeRetrieved.getMakeId(), makeCreated.getMakeId());
    }

}
