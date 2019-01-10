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
public interface MakeService {
    public Make createMake(String makeName, int userId) throws DataValidationError;
    
    public Make readMakeById(int id);
    
    public List<Make> getAllMakes();

    public void updateMake(String makeName, int makeId) throws DataValidationError;
}
