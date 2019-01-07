/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Contact;

/**
 *
 * @author chaseowens
 */
interface ContactService {
    public Contact makeContact(String name, String email, String phone, String message) throws NeedContactNameError, NeedContactDetailsError;
}
