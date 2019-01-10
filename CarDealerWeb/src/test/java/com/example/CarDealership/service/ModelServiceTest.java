/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Model;
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
public class ModelServiceTest {

    @Autowired
    ModelService modelService;
    @Autowired
    ProfileService profileService;
    @Autowired
    UserService userService;
    @Autowired
    MakeService makeService;

    @Autowired

    public ModelServiceTest() {
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
    public void testCreateModel() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model testModel = new Model();
        testModel.setCreatedBy(user);
        testModel.setMake(makeCreated);
        testModel.setModelName(testName);

        //Act
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());

        //Assert
        assertEquals(testModel.getCreatedBy().getUserId(), modelCreated.getCreatedBy().getUserId());
        assertEquals(testModel.getModelName(), modelCreated.getModelName());
        assertNotEquals(testModel.getId(), modelCreated.getId());

    }

    @Test
    public void testCreateModelWithoutName() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);

        //Act
        try {
            Make makeCreated = makeService.createMake(testName, user.getUserId());
            Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());
            fail("Should have failed without a model name specified");
        } catch (DataValidationError e) {

        }

    }

    @Test
    public void testGetModelsWithoutAny() {
        //Act
        List<Model> model = modelService.readAllModels();

        //Assert
        assertEquals(0, model.size());
    }

    @Test
    public void testGetModels() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());

        //Act
        List<Model> models = modelService.readAllModels();

        //Assert
        assertEquals(1, models.size());

    }

    @Test
    public void testReadModelById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());

        //Act
        Model modelRetrieved = modelService.readModelById(modelCreated.getId());

        //Assert
        assertEquals(modelRetrieved.getCreatedBy().getUserId(), modelCreated.getCreatedBy().getUserId());
        assertEquals(modelRetrieved.getModelName(), modelCreated.getModelName());
        assertEquals(modelRetrieved.getId(), modelCreated.getId());
    }

    @Test
    public void updateModel() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());

        String newRole = "newRole";

        //Act
        modelCreated.setModelName("test");
        modelService.updateModel(makeCreated.getMakeId(), newRole, modelCreated.getId());
        Model modelRetrieved = modelService.readModelById(modelCreated.getId());

        //Assert
        assertEquals(modelRetrieved.getCreatedBy().getUserId(), modelCreated.getCreatedBy().getUserId());
        assertNotEquals(modelRetrieved.getModelName(), testName);
        assertEquals(modelRetrieved.getId(), modelCreated.getId());
        assertEquals(user.getUserId(), modelRetrieved.getCreatedBy().getUserId());

    }

}
