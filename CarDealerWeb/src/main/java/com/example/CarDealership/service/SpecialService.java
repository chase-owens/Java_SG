/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Special;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface SpecialService {
    public Special createSpecial(String title, String description, int vehicleId, int userId) throws DataValidationError;
    public List<Special> getAllSpecials();
    public Special getSpecialById(int id);
    public void updateSpecial(Special special);
    public void deleteSpecial(int id);
}
