/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.User;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface UserDao {
    //CRUD methods
    public User createUser(Profile profile, String role, String password);
    public List<User> readAllUsers();
    public User readUserById(int id);
    public void updateUser(User user);
    public void deleteUser(int id);
}
