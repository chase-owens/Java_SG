/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.User;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface UserService {
    public User createUser(String firstName, String lastName, String phone, String email, String role, String password1, String password2) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError;
    public User createUserWithProfile(Profile profile, String role, String password) throws NeedContactNameError, NeedContactDetailsError;
    public List<User> readAllUsers();
    public User readUserByid(int id);
    public void updateUser(int userId, String firstName, String lastName, String phone, String email, String role, String password1, String password2) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError;
}
