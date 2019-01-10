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
import com.example.CarDealership.entity.Vehicle;
import com.example.CarDealership.service.DataValidationError;
import com.example.CarDealership.service.MakeService;
import com.example.CarDealership.service.ModelService;
import com.example.CarDealership.service.NeedContactDetailsError;
import com.example.CarDealership.service.NeedContactMessageError;
import com.example.CarDealership.service.NeedContactNameError;
import com.example.CarDealership.service.ProfileService;
import com.example.CarDealership.service.UserService;
import java.math.BigDecimal;
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
public class VehicleDaoTest {

    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    ModelService modelService;
    @Autowired
    MakeService makeService;
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;

    public VehicleDaoTest() {
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
     * Test of createVehicle method, of class VehicleDao.
     *
     * @throws com.example.CarDealership.service.NeedContactNameError
     * @throws com.example.CarDealership.service.NeedContactDetailsError
     * @throws com.example.CarDealership.service.NeedContactMessageError
     */
    @Test
    public void testCreateVehicle() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());
        
        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;
        
        // Test Vehicle
        Vehicle testVehicle = new Vehicle();
        testVehicle.setBodyStyle(bodyStyle);
        testVehicle.setCreatedBy(user);
        testVehicle.setExteriorColor(exteriorColor);
        testVehicle.setInteriorColor(interiorColor);
        testVehicle.setListPrice(listPrice);
        testVehicle.setMake(makeCreated);
        testVehicle.setMileage(mileage);
        testVehicle.setModel(modelCreated);
        testVehicle.setMsrp(msrp);
        testVehicle.setTransmission(transmission);
        testVehicle.setVehicleDescription(vehicleDescription);
        testVehicle.setVehicleType(vehicleType);
        
        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleDao.createVehicle(makeCreated, modelCreated, msrp, listPrice, mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, user.getUserId());
        
        // Assert
        assertEquals(vehicleCreated.getBodyStyle(), testVehicle.getBodyStyle());
        assertEquals(vehicleCreated.getExteriorColor(), testVehicle.getExteriorColor());
        
    }

    @Test
    public void testReadAllVehiclesWhenEmpty() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Act
        List<Vehicle> vehicles = vehicleDao.readAllVehicles();

        //Assert
        assertEquals(0, vehicles.size());
    }

    @Test
    public void testreadAllVehicles() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());
        
        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;
        
        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleDao.createVehicle(makeCreated, modelCreated, msrp, listPrice, mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, user.getUserId());
        
        //Act
        List<Vehicle> vehicles = vehicleDao.readAllVehicles();

        //Assert
        assertEquals(1, vehicles.size());
        
    }

    @Test
    public void testReadVehicleById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());
        
        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;
        
        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleDao.createVehicle(makeCreated, modelCreated, msrp, listPrice, mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, user.getUserId());
        
        //Act
        Vehicle vehicleRetrieved = vehicleDao.readVehicleById(vehicleCreated.getVehicleId());
        
        // Assert
        assertEquals(vehicleCreated.getBodyStyle(), vehicleRetrieved.getBodyStyle());
        assertEquals(vehicleCreated.getExteriorColor(), vehicleRetrieved.getExteriorColor());
        
    }

    @Test
    public void testUpateVehicle() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());
        
        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;
        
        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleDao.createVehicle(makeCreated, modelCreated, msrp, listPrice, mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, user.getUserId());
        
        //Act
        vehicleCreated.setBodyStyle("suv");
        vehicleDao.updateVehicle(vehicleCreated);
        Vehicle vehicleRetrieved = vehicleDao.readVehicleById(vehicleCreated.getVehicleId());
        
        // Assert
        assertNotEquals(bodyStyle, vehicleRetrieved.getBodyStyle());
        assertEquals(vehicleCreated.getExteriorColor(), vehicleRetrieved.getExteriorColor());
        
    }


}
