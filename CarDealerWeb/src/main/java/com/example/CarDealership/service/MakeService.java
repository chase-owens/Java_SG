/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Make;
import java.util.List;

/**
 *
 * @author chaseowens
 */
interface MakeService {
    public Make createMake(String makeName, int userId);
    
    public List<Make> getAllMakes();
}
