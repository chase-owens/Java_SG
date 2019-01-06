/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Vehicle;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface VehicleDao {
    //CRUD methods
    public Vehicle createVehicle();
    public List<Vehicle> readAllVehicles();
    public Vehicle readVehicleById(int id);
    public void updateVehicle(int id);
    public void deleteVehicle(int id);
}
