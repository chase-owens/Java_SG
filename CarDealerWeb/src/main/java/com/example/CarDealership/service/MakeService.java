/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.dao.MakeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class MakeService {
    MakeDao makeDao;
    
    @Autowired
    public MakeService(MakeDao makeDao) {
        this.makeDao = makeDao;
    }
    
}
