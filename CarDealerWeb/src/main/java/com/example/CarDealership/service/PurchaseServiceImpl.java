/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.dao.PurchaseDao;
import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.Purchase;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    PurchaseDao purchaseDao;
    ProfileService profileService;
    VehicleService vehicleService;

    /**
     *
     * @param purchaseDao
     * @param profileService
     * @param vehicleService
     */
    @Autowired
    public PurchaseServiceImpl(PurchaseDao purchaseDao, ProfileService profileService, VehicleService vehicleService) {
        this.purchaseDao = purchaseDao;
        this.profileService = profileService;
        this.vehicleService = vehicleService;
    }

    @Override
    public Purchase createPurchase(int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, String salePriceString, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError {
        // create customer profile
        Profile profile = profileService.createProfile(customerName, email, customerPhone, street1 + " " + street2, zipcode);

        // update vehicle - mark as unavailable
        vehicleService.markAsSold(vehicleId);
        
        BigDecimal salePrice = null;
        try {
            salePrice = new BigDecimal(salePriceString);
        } catch (NumberFormatException e) {
            
        }

        // add purchase to DB
        Purchase purchase = purchaseDao.createPurchase(profile, vehicleId, salePrice, purchaseType, userId);
        return purchase;
    }
    
    @Override
    public List<Purchase> readAllPurchases() {
        return purchaseDao.readAllPurchases();
    }
    
    @Override
    public Purchase readPurchaseById(int id) {
        return purchaseDao.readPurchaseById(id);
    }

    @Override
    public BigDecimal getSalesSumByUserId(int id, String startingOnString, String toString) throws DataValidationError {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate startingOn = null, to = null;

        try {
            startingOn = LocalDate.parse(startingOnString, formatter);
            to = LocalDate.parse(toString, formatter);
        } catch (DateTimeParseException e) {
            throw new DataValidationError();
        }

        return purchaseDao.getSalesSumById(id, startingOn, to);
    }

    @Override
    public int getTotalNumberOfSalesByUserId(int id, String startingOnString, String toString) throws DataValidationError {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate startingOn = null, to = null;

        try {
            startingOn = LocalDate.parse(startingOnString, formatter);
            to = LocalDate.parse(toString, formatter);
        } catch (DateTimeParseException e) {
            throw new DataValidationError();
        }
        
        return purchaseDao.getSalesCountById(id, startingOn, to);
    }
    
    @Override
    public void deletePurchase(int id) {
        purchaseDao.deletePurchase(id);
    }

    @Override
    public void updatePurchase(int purchaseId, int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, String salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError {
        Profile profile = profileService.createProfile(customerName, email, customerPhone, street1 + " " + street2, zipcode);
        
        Purchase purchaseToUpdate = purchaseDao.readPurchaseById(purchaseId);
        
        purchaseToUpdate.setSaleType(purchaseType);
        
        purchaseDao.updatePurchase(purchaseToUpdate);
    }

}
