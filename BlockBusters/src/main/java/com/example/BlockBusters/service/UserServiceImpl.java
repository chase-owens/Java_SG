/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.dao.UserDao;
import com.example.BlockBusters.entity.Profile;
import com.example.BlockBusters.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class UserServiceImpl implements UserService {
    UserDao userDao;
    ProfileService profileService;
    
    @Autowired
    public UserServiceImpl(UserDao userDao, ProfileService profileService) {
        this.userDao = userDao;
        this.profileService = profileService;
    }

    @Override
    public User createUser(String firstName, String lastName, String phone, String email, String role, String password1, String password2, int adminId) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError {
        if (!password1.equals(password2)) {
            throw new PasswordsNotMatchingError();
        }
        
        Profile profile = profileService.createProfile(firstName+" "+lastName, email, phone);
        User user = userDao.createUser(profile, role, password1, adminId);
        return user;
    }

    @Override
    public List<User> readAllUsers() {
        return userDao.readAllUsers();
    }

    @Override
    public User readUserByid(int id) {
        return userDao.readUserById(id);
    }

    @Override
    public void updateUser(int userId, String firstName, String lastName, String phone, String email, String role, String password1, String password2, int adminId) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError {
        if (!password1.equals(password2)) {
            throw new PasswordsNotMatchingError();
        }
        
        // Get user
        User user = userDao.readUserById(userId);
        
        // Update properties
        Profile profile = user.getProfile();
        profile.setEmail(email);
        profile.setFullName(firstName+" "+lastName);
        profile.setNumber(phone);
        user.setRole(role);
        user.setPassword(password2);
        
        // Update user in DB
        userDao.updateUser(user);
    }
}
