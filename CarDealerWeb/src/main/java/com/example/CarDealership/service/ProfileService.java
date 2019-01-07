/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Profile;

/**
 *
 * @author chaseowens
 */
public interface ProfileService {
    public Profile createProfile(String name, String email, String phone) throws NeedContactNameError, NeedContactDetailsError;
    
    public Profile createProfile(String name, String email, String phone, String address, String zipcode) throws NeedContactNameError, NeedContactDetailsError;
    
    public void updateProfile(int id, String name, String email, String phone);
}
