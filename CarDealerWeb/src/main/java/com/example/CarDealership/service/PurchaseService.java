/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Purchase;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author chaseowens
 */
public interface PurchaseService {
    public Purchase createPurchase(int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, BigDecimal salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError;
    public BigDecimal getSalesSumByUserId(int id, LocalDate startingOn, LocalDate to);
    public int getTotalNumberOfSalesByUserId(int id, LocalDate startingOn, LocalDate to);
}
