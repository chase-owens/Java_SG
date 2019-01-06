/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Special;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface SpecialDao {
    //CRUD methods
    public Special createSpecial();
    public List<Special> readAllSpecials();
    public Special readSpecialById(int id);
    public void updateSpecial(int id);
    public void deleteSpecial(int id);
}
