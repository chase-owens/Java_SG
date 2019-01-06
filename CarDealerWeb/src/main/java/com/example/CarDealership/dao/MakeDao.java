/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface MakeDao {
    //CRUD methods
    public Make createMake();
    public List<Make> readAllMakes();
    public Make readMakeById(int id);
    public void updateMake(int id);
    public void deleteMake(int id);
}
