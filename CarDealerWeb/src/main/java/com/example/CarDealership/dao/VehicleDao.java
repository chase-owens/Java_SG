/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Vehicle;
import java.math.BigDecimal;
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
    public void updateVehicle(Vehicle vehicle);
    public void deleteVehicle(int id);
    
    //App specific methods
    public List<Vehicle> query20VehiclesByTypePriceAndYearDescendingMSRP(String query, String type, BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear);
}
