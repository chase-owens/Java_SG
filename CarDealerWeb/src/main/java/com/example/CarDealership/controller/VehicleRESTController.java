/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.controller;

import com.example.CarDealership.service.VehicleService;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
public class VehicleRESTController {
    VehicleService service;
    public VehicleRESTController(VehicleService service) {
        this.service = service;
    }
}