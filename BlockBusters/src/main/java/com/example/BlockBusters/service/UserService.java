/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.entity.User;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface UserService {
    public User createUser(String firstName, String lastName, String phone, String email, String role, String password1, String password2, int adminId) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError;
    public List<User> readAllUsers();
    public User readUserByid(int id);
    public void updateUser(int userId, String firstName, String lastName, String phone, String email, String role, String password1, String password2, int adminId) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError;
}
