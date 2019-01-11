/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.Purchase;
import com.example.CarDealership.entity.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface PurchaseDao {
    //CRUD methods
    public Purchase createPurchase(Profile profile, int vehicleId, BigDecimal salePrice, String purchaseType, int userId);
    public List<Purchase> readAllPurchases();
    public Purchase readPurchaseById(int id);
    public void updatePurchase(Purchase purchase);
    public void deletePurchase(int id);
    
    //App specific methods
    public List<User> getGroupSalesReport(LocalDate startingOn, LocalDate to);
    public List<User> getUserSalesReport(int id, LocalDate startingOn, LocalDate to);
}
