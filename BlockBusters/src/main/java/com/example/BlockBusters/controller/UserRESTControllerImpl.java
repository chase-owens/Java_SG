/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.controller;

import com.example.BlockBusters.service.UserService;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
public class UserRESTControllerImpl implements UserRESTController {
    UserService service;
    public UserRESTControllerImpl(UserService service) {
        this.service = service;
    }
}
