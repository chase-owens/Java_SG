/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.dao.ShoeDao;
import com.example.BlockBusters.entity.Shoe;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class ShoeServiceImpl implements ShoeService {
    ShoeDao shoeDao;
    
    @Autowired
    public ShoeServiceImpl(ShoeDao shoeDao) {
        this.shoeDao = shoeDao;
    }

    @Override
    public Shoe createShoe(int brandId, String shoeName, int userId) {
        return shoeDao.createShoe(brandId, shoeName, userId);
    }

    @Override
    public List<Shoe> readAllShoes() {
        return shoeDao.readAllShoes();
    }

    @Override
    public Shoe readShoeById(int id) {
        return shoeDao.readShoeById(id);
    }
}
