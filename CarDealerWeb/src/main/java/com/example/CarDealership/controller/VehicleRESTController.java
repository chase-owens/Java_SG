/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.controller;

import com.example.CarDealership.entity.Vehicle;
import com.example.CarDealership.service.DataValidationError;
import com.example.CarDealership.service.TooManyMilesToBeNewError;
import com.example.CarDealership.service.VehicleService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
@RequestMapping("/vehicleApi/")
public class VehicleRESTController {

    VehicleService service;

    public VehicleRESTController(VehicleService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Vehicle> createVehicle(int makeId, int modelId, int mileage, int year, String vehicleType, String vehicleDescription, 
            String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin,
            String msrpString, String listPriceString, int userId) throws TooManyMilesToBeNewError, DataValidationError {
        Vehicle vehicle = null;
        try {
            vehicle = service.createVehicle(makeId, modelId, mileage, year, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrpString, listPriceString, userId);
        } catch(TooManyMilesToBeNewError | DataValidationError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(vehicle);
    }
    
    @GetMapping("/readOne")
    public ResponseEntity<Vehicle> readVehicleById(int vehicleId) {
        Vehicle vehicle = service.readVehicleById(vehicleId);
        if (vehicle == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(vehicle);
    }
    
    @GetMapping("/vehicles")
    public List<Vehicle> query20VehiclesWithFilters(String query, String type, BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear) {
        return service.query20VehiclesWithFilters(query, type, minPrice, maxPrice, minYear, maxYear);
    }
    
    @PutMapping("/update")
    public void updateVehicle(int vehicleId, int makeId, int modelId, int mileage, int year, String vehicleType, String vehicleDescription, 
            String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin,
            String msrp, String listPrice, String isFeatured, int userId) throws TooManyMilesToBeNewError, DataValidationError {
        service.updateVehicle(vehicleId, makeId, modelId, mileage, year, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp, listPrice, isFeatured, userId);
    }
    
    @PostMapping("/markSold")
    public void markAsSold(int vehicleId) {
        service.markAsSold(vehicleId);
    }
    
    public List<Vehicle> readAllVehicles() {
        return service.readAllVehicles();
    }
}
