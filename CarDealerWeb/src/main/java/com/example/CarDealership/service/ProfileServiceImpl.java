/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.dao.ProfileDao;
import com.example.CarDealership.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class ProfileServiceImpl implements ProfileService {
    ProfileDao profileDao;
    
    @Autowired
    public ProfileServiceImpl(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public Profile createProfile(String name, String email, String phone) throws NeedContactNameError, NeedContactDetailsError {
        // Check name
        if (name == null) {
            throw new NeedContactNameError();
        }
        
        // Check contact details
        if (email == null && phone == null) {
            throw new NeedContactDetailsError();
        }
        
        return profileDao.createProfile(name, email, phone);
    }
    
    @Override
    public Profile createProfile(String name, String email, String phone, String address, String zipcode) throws NeedContactNameError, NeedContactDetailsError {
        Profile profile = createProfile(name, email, phone);
        profile.setStreetAddress(address);
        profile.setZipcode(zipcode);
        profileDao.updateProfile(profile);
        return profile;
    }
    
    @Override
    public void updateProfile(int id, String name, String email, String phone) {
        Profile profile = profileDao.readProfileById(id);
        profile.setEmail(email);
        profile.setFullName(name);
        profile.setNumber(name);
        profileDao.updateProfile(profile);
    }
}
