/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.dao.ProfileDao;
import com.example.BlockBusters.entity.Profile;
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

    @Override
    public Profile createProfile(String name, String email, String phone) throws NeedContactNameError, NeedContactDetailsError {
        // Check name
        if (name.equals("")) {
            throw new NeedContactNameError();
        }
        
        // Check contact details
        if (email.equals("") && phone.equals("")) {
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
    public void updateProfile(int id, String name, String email, String phone) throws NeedContactNameError, NeedContactDetailsError {
        // Check name
        if (name.equals("")) {
            throw new NeedContactNameError();
        }
        
        // Check contact details
        if (email.equals("") && phone.equals("")) {
            throw new NeedContactDetailsError();
        }
        Profile profile = profileDao.readProfileById(id);
        profile.setEmail(email);
        profile.setFullName(name);
        profile.setNumber(name);
        profileDao.updateProfile(profile);
    }
}
