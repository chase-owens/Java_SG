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
import com.example.CarDealership.entity.Vehicle;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class VehicleServiceTest {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ModelService modelService;
    @Autowired
    MakeService makeService;
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;

    public VehicleServiceTest() {
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
    public void testCreateVehicle() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError, TooManyMilesToBeNewError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image.png", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "manual", bodyStyle = "bodyStyle", vin = "fffffffffffffffff";
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
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), user.getUserId());

        // Assert
        assertEquals(vehicleCreated.getBodyStyle(), testVehicle.getBodyStyle());
        assertEquals(vehicleCreated.getExteriorColor(), testVehicle.getExteriorColor());

    }

    @Test
    public void testReadAllVehiclesWhenEmpty() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        //Act
        List<Vehicle> vehicles = vehicleService.readAllVehicles();

        //Assert
        assertEquals(0, vehicles.size());
    }

    @Test
    public void testreadAllVehicles() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError, TooManyMilesToBeNewError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image.png", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "manual", bodyStyle = "bodyStyle", vin = "fffffffffffffffff";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;

        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), user.getUserId());

        //Act
        List<Vehicle> vehicles = vehicleService.readAllVehicles();

        //Assert
        assertEquals(1, vehicles.size());

    }

    @Test
    public void testReadVehicleById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError, TooManyMilesToBeNewError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image.png", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "manual", bodyStyle = "bodyStyle", vin = "fffffffffffffffff";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;

        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), user.getUserId());

        //Act
        Vehicle vehicleRetrieved = vehicleService.readVehicleById(vehicleCreated.getVehicleId());

        // Assert
        assertEquals(vehicleCreated.getBodyStyle(), vehicleRetrieved.getBodyStyle());
        assertEquals(vehicleCreated.getExteriorColor(), vehicleRetrieved.getExteriorColor());

    }

    @Test
    public void testUpateVehicle() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, DataValidationError, TooManyMilesToBeNewError {
        //Arrange
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeService.createMake(testName, user.getUserId());
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, user.getUserId());
        String isFeatured = "true";

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "C:%5Cfakepath%5Cfresh-prince-floral-airJordan7-rocket-boy-nift.jpg", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "manual", bodyStyle = "bodyStyle", vin = "fffffffffffffffff";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;

        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), user.getUserId());

        //Act
        String newBodyStyle = "suv";
        vehicleService.updateVehicle(vehicleCreated.getVehicleId(), makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, newBodyStyle, vin, msrp.toString(), listPrice.toString(), isFeatured, user.getUserId());
        Vehicle vehicleRetrieved = vehicleService.readVehicleById(vehicleCreated.getVehicleId());

        // Assert
        assertNotEquals(bodyStyle, vehicleRetrieved.getBodyStyle());
        assertEquals(vehicleCreated.getExteriorColor(), vehicleRetrieved.getExteriorColor());

    }

}
