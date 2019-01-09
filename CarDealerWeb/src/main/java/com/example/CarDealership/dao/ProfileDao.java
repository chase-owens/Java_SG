/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Profile;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface ProfileDao {
    //CRUD methods
    public Profile createProfile(String name, String email, String phone);
    public List<Profile> readAllProfiles();
    public Profile readProfileById(int id) throws DataPersistenceError;
    public void updateProfile(Profile profile);
    public void deleteProfile(int id);
}
