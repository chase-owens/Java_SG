/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.controller;

import com.example.CarDealership.dao.DataPersistenceError;
import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.service.NeedContactDetailsError;
import com.example.CarDealership.service.NeedContactNameError;
import com.example.CarDealership.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/profile/")
public class ProfileRESTController {

    ProfileService service;

    @Autowired
    public ProfileRESTController(ProfileService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Profile> createProfile(String name, String email, String phone) throws NeedContactNameError, NeedContactDetailsError {
        Profile profile = null;
        try {
            profile = service.createProfile(name, email, phone);
        } catch(NeedContactNameError | NeedContactDetailsError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(profile);
    }
    
    @PostMapping("/createFull")
    public ResponseEntity<Profile> createProfile(String name, String email, String phone, String address, String zipcode) throws NeedContactNameError, NeedContactDetailsError {
        Profile profile = null;
        try {
            profile = service.createProfile(name, email, phone, address, zipcode);
        } catch(NeedContactNameError | NeedContactDetailsError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(profile);
    }
    
    @GetMapping("/readOne")
    public ResponseEntity<Profile> readProfileByInt(int id) throws DataPersistenceError {
        Profile profile = null;
        try {
            profile = service.readProfileByInt(id);
        } catch(DataPersistenceError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(profile);
    }
    
    @PutMapping("/update")
    public boolean updateProfile(int id, String name, String email, String phone) throws NeedContactNameError, NeedContactDetailsError, DataPersistenceError {
        boolean didUpdate = true;
        try {
            service.updateProfile(id, name, email, phone);
        } catch(NeedContactNameError | NeedContactDetailsError | DataPersistenceError e) {
            didUpdate = false;
        }
        return didUpdate;
    }
}
