/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Vehicle;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface VehicleService {
    Vehicle createVehicle(int makeId, int modelId, int mileage, int year, String vehicleType, String vehicleDescription, 
            String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin,
            String msrpString, String listPriceString, int userId) throws TooManyMilesToBeNewError, DataValidationError;
    
    Vehicle readVehicleByInt(int vehicleId);
    
    List<Vehicle> query20VehiclesWithFilters(String query, String type, BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear);
    
    void updateVehicle(int vehicleId, int makeId, int modelId, int mileage, int year, String vehicleType, String vehicleDescription, 
            String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin,
            String msrp, String listPrice, String isFeatured, int userId) throws TooManyMilesToBeNewError, DataValidationError;

    void markAsSold(int vehicleId);
}
