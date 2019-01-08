/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Model;
import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.Special;
import com.example.CarDealership.entity.User;
import com.example.CarDealership.entity.Vehicle;
import com.example.CarDealership.service.DataValidationError;
import com.example.CarDealership.service.MakeService;
import com.example.CarDealership.service.ModelService;
import com.example.CarDealership.service.NeedContactDetailsError;
import com.example.CarDealership.service.NeedContactMessageError;
import com.example.CarDealership.service.NeedContactNameError;
import com.example.CarDealership.service.ProfileService;
import com.example.CarDealership.service.TooManyMilesToBeNewError;
import com.example.CarDealership.service.UserService;
import com.example.CarDealership.service.VehicleService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SpecialDaoTest {

    @Autowired
    SpecialDao specialDao;
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

    public SpecialDaoTest() {
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
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, adminId);

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;

        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), adminId);

        // Special properties
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2018", formatter);
        LocalDate newYearsEve = LocalDate.parse("12-31-2018", formatter);

        Special testSpecial = new Special();
        testSpecial.setCreatedBy(user);
        testSpecial.setSpecialDescription(vehicleDescription);
        testSpecial.setTitle(specialTitle);
        testSpecial.setVehicle(vehicleCreated);
        testSpecial.setDateBegin(christmas);
        testSpecial.setDateEnd(newYearsEve);

        // Act
        Special specialCreated = specialDao.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), christmas, newYearsEve, adminId);

        // Assert
        assertEquals(specialCreated.getDateBegin(), testSpecial.getDateBegin());
        assertEquals(specialCreated.getDateEnd(), testSpecial.getDateEnd());
    }

    @Test
    public void testReadEmptySpecials() {
        //Act
        List<Special> specials = specialDao.readAllSpecials();

        //Assert
        assertEquals(0, specials.size());
    }

    @Test
    public void testReadSpecials() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, adminId);

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;

        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), adminId);

        // Special properties
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2018", formatter);
        LocalDate newYearsEve = LocalDate.parse("12-31-2018", formatter);

        Special specialCreated = specialDao.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), christmas, newYearsEve, adminId);

        //Act
        List<Special> specials = specialDao.readAllSpecials();

        //Assert
        assertEquals(1, specials.size());
    }

    @Test
    public void testReadSpecialById() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, adminId);

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;

        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), adminId);

        // Special properties
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2018", formatter);
        LocalDate newYearsEve = LocalDate.parse("12-31-2018", formatter);

        Special specialCreated = specialDao.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), christmas, newYearsEve, adminId);

        //Act
        Special specialRetrieved = specialDao.readSpecialById(specialCreated.getSpecialId());

        // Assert
        assertEquals(specialCreated.getDateBegin(), specialRetrieved.getDateBegin());
        assertEquals(specialCreated.getDateEnd(), specialRetrieved.getDateEnd());

    }

    @Test
    public void testUpdateSpecial() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, adminId);

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;

        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), adminId);

        // Special properties
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2018", formatter);
        LocalDate newYearsEve = LocalDate.parse("12-31-2018", formatter);
        LocalDate newYears = LocalDate.parse("01-01-2019", formatter);

        Special specialCreated = specialDao.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), christmas, newYearsEve, adminId);

        //Act
        specialCreated.setDateEnd(newYears);
        specialDao.updateSpecial(specialCreated);
        Special specialRetrieved = specialDao.readSpecialById(specialCreated.getSpecialId());

        // Assert
        assertEquals(specialCreated.getDateBegin(), specialRetrieved.getDateBegin());
        assertNotEquals(specialCreated.getDateEnd(), specialRetrieved.getDateEnd());

    }

    @Test
    public void testDeleteSpecial() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Arrange
        String testName = "testName", specialTitle = "specialTitle";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        int adminId = 1;
        User user = userService.createUserWithProfile(profile, role, password, adminId);
        Make makeCreated = makeService.createMake(testName, adminId);
        Model modelCreated = modelService.createModel(makeCreated.getMakeId(), testName, adminId);

        // VehicleProperties
        int mileage = 0, vehicleYear = 2018;
        String vehicleType = "vehicleType", vehicleDescription = "vehicleDescription", image = "image", exteriorColor = "exteriorColor", interiorColor = "interiorColor", transmission = "transmission", bodyStyle = "bodyStyle", vin = "vin";
        BigDecimal msrp = BigDecimal.TEN, listPrice = BigDecimal.TEN;

        // Use Dao To Create Vehicle
        Vehicle vehicleCreated = vehicleService.createVehicle(makeCreated.getMakeId(), modelCreated.getId(), mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp.toString(), listPrice.toString(), adminId);

        // Special properties
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate christmas = LocalDate.parse("12-25-2018", formatter);
        LocalDate newYearsEve = LocalDate.parse("12-31-2018", formatter);
        LocalDate newYears = LocalDate.parse("01-01-2019", formatter);

        Special specialCreated = specialDao.createSpecial(specialTitle, vehicleDescription, vehicleCreated.getVehicleId(), christmas, newYearsEve, adminId);
        List<Special> specialsBeforeDelete = specialDao.readAllSpecials();
        assertEquals(1, specialsBeforeDelete.size());
        
        //Act
        specialDao.deleteSpecial(specialCreated.getSpecialId());
        List<Special> specialsAfterDelete = specialDao.readAllSpecials();
        
        //Assert
        assertEquals(0, specialsAfterDelete);
    }

}
