/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Purchase;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface PurchaseDao {
    //CRUD methods
    public Purchase createPurchase();
    public List<Purchase> readAllPurchases();
    public Purchase readPurchaseById(int id);
    public void updatePurchase(int id);
    public void deletePurchase(int id);
}
