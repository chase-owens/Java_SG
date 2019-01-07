/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Vehicle;

/**
 *
 * @author chaseowens
 */
public interface VehicleService {
    Vehicle readVehicleByInt(int vehicleId);

    void updateVehicle(int vehicleId);
}
