/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Profile;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface ProfileDao {
    //CRUD methods
    public Profile createProfile();
    public List<Profile> readAllProfiles();
    public Profile readProfileById(int id);
    public void updateProfile(Profile profile);
    public void deleteProfile(int id);
}
