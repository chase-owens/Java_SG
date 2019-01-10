/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.controller;

import com.example.CarDealership.entity.User;
import com.example.CarDealership.service.NeedContactDetailsError;
import com.example.CarDealership.service.NeedContactNameError;
import com.example.CarDealership.service.PasswordsNotMatchingError;
import com.example.CarDealership.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
@RequestMapping("/userApi/")
public class UserRESTController {

    UserService service;

    public UserRESTController(UserService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<User> createUser(String firstName, String lastName, String phone, String email, String role, String password1, String password2) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError {
        User user = null;
        try {
            user = service.createUser(firstName, lastName, phone, email, role, password1, password2);
        } catch (PasswordsNotMatchingError| NeedContactNameError | NeedContactDetailsError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/readAll")
    public List<User> readAllUsers() {
        return service.readAllUsers();
    }
    
    @GetMapping("/readOne")
    public ResponseEntity<User> readUserById(int id) {
        User user = service.readUserById(id);
        if (user == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/update")
    public void updateUser(int userId, String firstName, String lastName, String phone, String email, String role, String password1, String password2) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError {
        service.updateUser(userId, firstName, lastName, phone, email, role, password1, password2);
    }
}
