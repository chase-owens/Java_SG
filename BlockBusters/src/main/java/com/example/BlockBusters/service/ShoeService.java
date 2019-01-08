/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.entity.Shoe;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface ShoeService {
    public Shoe createShoe(int brandId, String shoeName, int userId);
    
    public List<Shoe> readAllShoes();
    
    public Shoe readShoeById(int id);
}
