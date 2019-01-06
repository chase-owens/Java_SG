/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.controller;


import com.example.CarDealership.service.ContactService;
import com.example.CarDealership.service.MakeService;
import com.example.CarDealership.service.ModelService;

import com.example.CarDealership.service.ProfileService;
import com.example.CarDealership.service.PurchaseService;

import com.example.CarDealership.service.SpecialService;
import com.example.CarDealership.service.UserService;
import com.example.CarDealership.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
public class CarDealershipRESTController {
    ProfileService profileService;
    UserService userService;
    ContactService contactService;
    MakeService makeService;
    ModelService modelService;
    VehicleService vehicleService;
    SpecialService specialService;
    PurchaseService purchaseService;
    
    @Autowired
    public CarDealershipRESTController(ProfileService profileService, UserService userService, ContactService contactService, MakeService makeService, ModelService modelService, VehicleService vehicleService, SpecialService specialService, PurchaseService purchaseService) {
        this.profileService = profileService;
        this.userService = userService;
        this.contactService = contactService;
        this.makeService = makeService;
        this.modelService = modelService;
        this.vehicleService = vehicleService;
        this.specialService = specialService;
        this.purchaseService = purchaseService;
    }
}
