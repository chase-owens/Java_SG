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
import com.example.CarDealership.service.VehicleService;
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
public class PurchaseDaoTest {
    @Autowired
    PurchaseDao purchaseDao;
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
    public void testSomeMethod()throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        // Arrange 
        String testName = "testName";
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
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleYear, listPrice, saleType, adminId);
        
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
        
        //Purchase variable
        String saleType = "saleType";
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleYear, listPrice, saleType, adminId);
        
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
        
        //Purchase variable
        String saleType = "saleType";
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleYear, listPrice, saleType, adminId);
        
        //Act
        assertEquals(purchaseCreated.getSalePrice(), listPrice);
        purchaseCreated.setSalePrice(BigDecimal.ONE);
        purchaseDao.updatePurchase(purchaseCreated);
        Purchase updatedPurchase = purchaseDao.readPurchaseById(purchaseCreated.getPurchaseId());
        
        //Assert
        assertEquals(purchaseCreated.getCreatedBy().getAdminId(), updatedPurchase.getCreatedBy().getAdminId());
        assertNotEquals(purchaseCreated.getSalePrice(), updatedPurchase.getSalePrice());
    }
    
    @Test
    public void testDeletePurchases() throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError, TooManyMilesToBeNewError, DataValidationError {
        // Arrange 
        String testName = "testName";
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
        
        //Purchase variable
        String saleType = "saleType";
        Purchase purchaseCreated = purchaseDao.createPurchase(profile, vehicleYear, listPrice, saleType, adminId);
        
        //Act
        List<Purchase> purchasesBeforeDelete = purchaseDao.readAllPurchases();
        assertEquals(1, purchasesBeforeDelete.size());
        purchaseDao.deletePurchase(purchaseCreated.getPurchaseId());
        List<Purchase> purchasesAfterDelete = purchaseDao.readAllPurchases();
        
        //Assert
        assertEquals(0, purchasesAfterDelete.size());
    }
    
}
