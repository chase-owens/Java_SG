/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Custom;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface CustomDao {
    //CRUD methods
    public Custom createCustom();
    public List<Custom> readAllCustoms();
    public Custom readCustomById(int id);
    public void updateCustom(Custom custom);
    public void deleteCustom(int id);
    
        
    //App specific methods
    public List<Custom> query20CustomsByArtistPriceAndSizeDescendingPrice(String query, String artist, BigDecimal minPrice, BigDecimal maxPrice, int size);
}
