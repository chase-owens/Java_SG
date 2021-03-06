/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Profile;
import com.example.BlockBusters.entity.Purchase;
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
    public BigDecimal getSalesSumById(int id, LocalDate startingOn, LocalDate to);
    public int getSalesCountById(int id, LocalDate startingOn, LocalDate to);
}
