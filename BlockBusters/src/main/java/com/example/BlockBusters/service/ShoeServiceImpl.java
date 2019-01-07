/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.dao.ShoeDao;
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
}
