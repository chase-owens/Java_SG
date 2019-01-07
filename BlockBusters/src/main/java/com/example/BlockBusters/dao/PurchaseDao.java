/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Purchase;
import java.math.BigDecimal;
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
    public void updatePurchase(Purchase purchase);
    public void deletePurchase(int id);
    
    //App specific methods
    public BigDecimal getSalesSumById(int id);
    public int getSalesCountById(int id);
}
