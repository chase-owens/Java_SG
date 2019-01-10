/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Purchase;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface PurchaseService {
    public Purchase createPurchase(int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, String salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError;
    public List<Purchase> readAllPurchases();
    public Purchase readPurchaseById(int id);
    public void updatePurchase(int purchaseId, int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, String salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError;
    public void deletePurchase(int id);
    public BigDecimal getSalesSumByUserId(int id, String startingOn, String to) throws DataValidationError;
    public int getTotalNumberOfSalesByUserId(int id, String startingOn, String to) throws DataValidationError;
}
