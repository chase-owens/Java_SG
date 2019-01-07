/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.dao.VehicleDao;
import com.example.CarDealership.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class VehicleServiceImpl implements VehicleService{
    VehicleDao vehicleDao;
    
    @Autowired
    public VehicleServiceImpl(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    public Vehicle readVehicleByInt(int vehicleId) {
        return vehicleDao.readVehicleById(vehicleId);
    }

    @Override
    public void updateVehicle(int vehicleId) {
        Vehicle vehicle = vehicleDao.readVehicleById(vehicleId);
        vehicle.setIsAvailable(false);
        vehicleDao.updateVehicle(vehicle);
    }
}
