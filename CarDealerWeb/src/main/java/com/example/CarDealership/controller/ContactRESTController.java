/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.controller;


import com.example.CarDealership.entity.Contact;
import com.example.CarDealership.service.ContactService;
import com.example.CarDealership.service.NeedContactDetailsError;
import com.example.CarDealership.service.NeedContactMessageError;
import com.example.CarDealership.service.NeedContactNameError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
@RequestMapping("/contact/")
public class ContactRESTController {
    ContactService service;
    
    @Autowired
    public ContactRESTController(ContactService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Contact> makeContact(String name, String email, String phone, String message) throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        Contact contact = null;
        try {
            contact = service.makeContact(name, email, phone, message);
        } catch(NeedContactNameError | NeedContactDetailsError | NeedContactMessageError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(contact);
    }
}
