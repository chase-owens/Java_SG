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
    public Purchase createPurchase(int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, BigDecimal salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError {
        // create customer profile
        Profile profile = profileService.createProfile(customerName, email, customerPhone, street1+" "+street2, zipcode);
        
        // update vehicle - mark as unavailable
        vehicleService.updateVehicle(vehicleId);
        
        // add purchase to DB
        Purchase purchase = purchaseDao.createPurchase(profile, vehicleId, salePrice, purchaseType, userId);
        return purchase;
    }

    @Override
    public BigDecimal getSalesSumByUserId(int id, LocalDate startingOn, LocalDate to) {
        return purchaseDao.getSalesSumById(id, startingOn, to);
    }

    @Override
    public int getTotalNumberOfSalesByUserId(int id, LocalDate startingOn, LocalDate to) {
        return purchaseDao.getSalesCountById(id, startingOn, to);
    }
    

}
