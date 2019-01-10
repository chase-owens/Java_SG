/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.dao.VehicleDao;
import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Model;
import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.Special;
import com.example.CarDealership.entity.User;
import com.example.CarDealership.entity.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
public class SpecialServiceTest {
    @Autowired
    SpecialService specialService;
    @Autowired
    ModelService modelService;
    @Autowired
    MakeService makeService;
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;
    @Autowired
    VehicleDao vehicleDao;
    
    public SpecialServiceTest() {
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
    public void testCreateSpecial() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
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

        Special testSpecial = new Special();
        testSpecial.setCreatedBy(user);
        testSpecial.setSpecialDescription(vehicleDescription);
        testSpecial.setTitle(specialTitle);
        testSpecial.setVehicle(vehicleCreated);

        // Act
        Special specialCreated = specialService.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), user.getUserId());

        // Assert
        assertEquals(specialCreated.getSpecialDescription(), testSpecial.getSpecialDescription());
        assertEquals(specialCreated.getTitle(), testSpecial.getTitle());
    }

//    @Test
//    public void testReadEmptySpecials() {
//        //Act
//        List<Special> specials = specialDao.readAllSpecials();
//
//        //Assert
//        assertEquals(0, specials.size());
//    }

    @Test
    public void testReadSpecials() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
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
        Special specialCreated = specialService.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), user.getUserId());
        List<Special> specials = specialService.getAllSpecials();
 
        //Assert
        assertEquals(1, specials.size());
    }

    @Test
    public void testReadSpecialById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
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

        Special specialCreated = specialService.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), user.getUserId());

        //Act
        Special specialRetrieved = specialService.getSpecialById(specialCreated.getSpecialId());

        // Assert
        assertEquals(specialCreated.getSpecialDescription(), specialRetrieved.getSpecialDescription());
        assertEquals(specialCreated.getTitle(), specialRetrieved.getTitle());

    }

    @Test
    public void testUpdateSpecial() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
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

        // Special properties
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2018", formatter);
        LocalDate newYearsEve = LocalDate.parse("12-31-2018", formatter);
        LocalDate newYears = LocalDate.parse("01-01-2019", formatter);

        Special specialCreated = specialService.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), user.getUserId());
        
        //Act
        specialCreated.setDateBegin(christmas);
        specialCreated.setDateEnd(newYears);
        specialService.updateSpecial(specialCreated);
        Special specialRetrieved = specialService.getSpecialById(specialCreated.getSpecialId());

        // Assert
        assertEquals(specialCreated.getSpecialDescription(), specialRetrieved.getSpecialDescription());
        assertEquals(specialCreated.getTitle(), specialRetrieved.getTitle());
        

    }

    @Test
    public void testDeleteSpecial() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
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


        Special specialCreated = specialService.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), user.getUserId());
        List<Special> specialsBeforeDelete = specialService.getAllSpecials();
        assertEquals(1, specialsBeforeDelete.size());
        
        //Act
        specialService.deleteSpecial(specialCreated.getSpecialId());
        List<Special> specialsAfterDelete = specialService.getAllSpecials();
        
        //Assert
        assertEquals(0, specialsAfterDelete.size());
    }
    
}
