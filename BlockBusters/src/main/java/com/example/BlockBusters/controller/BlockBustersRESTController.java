/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.controller;

import com.example.BlockBusters.service.BrandService;
import com.example.BlockBusters.service.ContactService;
import com.example.BlockBusters.service.CustomService;
import com.example.BlockBusters.service.ProfileService;
import com.example.BlockBusters.service.PurchaseService;
import com.example.BlockBusters.service.ShoeService;
import com.example.BlockBusters.service.SpecialService;
import com.example.BlockBusters.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
public class BlockBustersRESTController {
    ProfileService profileService;
    UserService userService;
    ContactService contactService;
    BrandService brandService;
    ShoeService shoeService;
    CustomService customService;
    SpecialService specialService;
    PurchaseService purchaseService;
    
    @Autowired
    public BlockBustersRESTController(ProfileService profileService, UserService userService, ContactService contactService, BrandService brandService, ShoeService shoeService, CustomService customService, SpecialService specialService, PurchaseService purchaseService) {
        this.profileService = profileService;
        this.userService = userService;
        this.contactService = contactService;
        this.brandService = brandService;
        this.shoeService = shoeService;
        this.customService = customService;
        this.specialService = specialService;
        this.purchaseService = purchaseService;
    }
}
