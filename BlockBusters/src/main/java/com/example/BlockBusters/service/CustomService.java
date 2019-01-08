/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.entity.Custom;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface CustomService {
    Custom createCustom(int brandId, int shoeId, String size, int yearReleased, String shoeType, String customDescription, 
            String image, String priceString, int userId) throws DataValidationError;
    
    Custom readCustomByInt(int customId);
    
    List<Custom> query20CustomWithFilters(String query, String artist, BigDecimal minPrice, BigDecimal maxPrice, int size);
    
    void updateCustom(int customId, int shoeId, String size, int yearReleased, String shoeType, String customDescription, 
            String image, String price, String isFeatured, int userId) throws DataValidationError;

    void markAsSold(int customId);
}
