/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Model;
import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.User;
import com.example.CarDealership.service.MakeService;
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
public class ModelDaoTest {

    @Autowired
    ModelDao modelDao;
    @Autowired
    MakeService makeService;
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;

    public ModelDaoTest() {
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
    public void testCreateModel() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model testModel = new Model();
        testModel.setCreatedBy(user);
        testModel.setMake(makeCreated);
        testModel.setModelName(testName);
        
        //Act
        Model modelCreated = modelDao.createModel(adminId, testName, adminId);
        
        //Assert
        assertEquals(testModel.getCreatedBy(), modelCreated.getCreatedBy());
        assertEquals(testModel.getModelName(), modelCreated.getModelName());
        assertNotEquals(testModel.getId(), modelCreated.getId());
        assertEquals(user.getAdminId(), modelCreated.getCreatedBy().getAdminId());
        
        
    }
    
    @Test
    public void testGetModelsWithoutAny() {
        //Act
        List<Model> model = modelDao.readAllModels();
        
        //Assert
        assertEquals(0, model.size());
    }
    
    @Test
    public void testGetModels() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelDao.createModel(makeCreated.getMakeId(), testName, adminId);
        
        //Act
        List<Model> models = modelDao.readAllModels();
        
        //Assert
        assertEquals(1, models.size());
        
    }
    
    @Test
    public void testReadModelById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelDao.createModel(adminId, testName, adminId);
        
        //Act
        Model modelRetrieved = modelDao.readModelById(modelCreated.getId());
        
        //Assert
        assertEquals(modelRetrieved.getCreatedBy(), modelCreated.getCreatedBy());
        assertEquals(modelRetrieved.getModelName(), modelCreated.getModelName());
        assertNotEquals(modelRetrieved.getId(), modelCreated.getId());
        assertEquals(user.getAdminId(), modelRetrieved.getCreatedBy().getAdminId());
    }
    
    @Test
    public void updateModel() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelDao.createModel(makeCreated.getMakeId(), testName, adminId);
        
        //Act
        modelCreated.setModelName("test");
        modelDao.updateModel(modelCreated);
        Model modelRetrieved = modelDao.readModelById(modelCreated.getId());
        
        //Assert
        assertEquals(modelRetrieved.getCreatedBy(), modelCreated.getCreatedBy());
        assertNotEquals(modelRetrieved.getModelName(), modelCreated.getModelName());
        assertNotEquals(modelRetrieved.getId(), modelCreated.getId());
        assertEquals(user.getAdminId(), modelRetrieved.getCreatedBy().getAdminId());
        
    }
    
    @Test
    public void deleteModel() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelDao.createModel(makeCreated.getMakeId(), testName, adminId);
        List<Model> models = modelDao.readAllModels();
        assertEquals(1, models.size());
        
        //Act
        modelDao.deleteModel(modelCreated.getId());
        List<Model> modelsAfterDelete = modelDao.readAllModels();
        
        //Assert
        assertEquals(0,modelsAfterDelete);
    }

}
