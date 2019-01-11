/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Model;
import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.Purchase;
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
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class PurchaseDaoTest {

    @Autowired
    PurchaseDao purchaseDao;
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

    public PurchaseDaoTest() {
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
    public void testCreatePurchase() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        // Arrange 
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

        //Purchase variable
        String saleType = "saleType";

        //Test Purchase
        Purchase testPurchase = new Purchase();
        testPurchase.setCreatedBy(user);
        testPurchase.setCustomerProfile(profile);
        testPurchase.setSalePrice(listPrice);
        testPurchase.setSaleType(saleType);
        testPurchase.setVehicle(vehicleCreated);

        // Act
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleCreated.getVehicleId(), listPrice, saleType, user.getUserId());

        // Assert
        assertEquals(testPurchase.getSalePrice(), purchaseCreated.getSalePrice());
        assertEquals(testPurchase.getSaleType(), purchaseCreated.getSaleType());
    }

    @Test
    public void testReadEmptyPurchases() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        //Act
        List<Purchase> purchases = purchaseDao.readAllPurchases();

        //Assert
        assertEquals(0, purchases.size());
    }

    @Test
    public void testReadPurchases() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        // Arrange 
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

        //Purchase variable
        String saleType = "saleType";
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleCreated.getVehicleId(), listPrice, saleType, user.getUserId());

        //Act
        List<Purchase> purchases = purchaseDao.readAllPurchases();

        //Assert
        assertEquals(1, purchases.size());
    }

    @Test
    public void testUpdatePurchase() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        // Arrange 
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

        //Purchase variable
        String saleType = "saleType";
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleCreated.getVehicleId(), listPrice, saleType, user.getUserId());

        //Act
        assertEquals(purchaseCreated.getSalePrice(), listPrice);
        purchaseCreated.setSalePrice(BigDecimal.ONE);
        purchaseDao.updatePurchase(purchaseCreated);
        Purchase updatedPurchase = purchaseDao.readPurchaseById(purchaseCreated.getPurchaseId());

        //Assert
        assertEquals(purchaseCreated.getCreatedBy().getUserId(), updatedPurchase.getCreatedBy().getUserId());
        assertNotEquals(purchaseCreated.getSalePrice(), updatedPurchase.getSalePrice());
    }

    @Test
    public void testDeletePurchases() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        // Arrange 
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

        //Purchase variable
        String saleType = "saleType";
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleCreated.getVehicleId(), listPrice, saleType, user.getUserId());

        //Act
        List<Purchase> purchasesBeforeDelete = purchaseDao.readAllPurchases();
        assertEquals(1, purchasesBeforeDelete.size());
        purchaseDao.deletePurchase(purchaseCreated.getPurchaseId());
        List<Purchase> purchasesAfterDelete = purchaseDao.readAllPurchases();

        //Assert
        assertEquals(0, purchasesAfterDelete.size());
    }
    
    @Test
    public void testGetGroupSales() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        // Arrange 
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

        //Purchase variable
        String saleType = "saleType";
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleCreated.getVehicleId(), listPrice, saleType, user.getUserId());

        //Act
        List<User> groupSales = purchaseDao.getGroupSalesReport(LocalDate.ofYearDay(2000, 1), LocalDate.ofYearDay(2020, 1));
        
        //Assert
        assertEquals(groupSales.size(), 1);
    }
    
    @Test
    public void testGetUserSales() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        // Arrange 
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

        //Purchase variable
        String saleType = "saleType";
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleCreated.getVehicleId(), listPrice, saleType, user.getUserId());

        //Act
        List<User> userSales = purchaseDao.getUserSalesReport(purchaseCreated.getCreatedBy().getUserId(), LocalDate.ofYearDay(2000, 1), LocalDate.ofYearDay(2020, 1));
        
        //Assert
        assertEquals(userSales.size(), 1);
    }


}
