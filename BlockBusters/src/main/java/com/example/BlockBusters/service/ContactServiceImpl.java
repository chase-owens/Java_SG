/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.dao.ContactDao;
import com.example.BlockBusters.entity.Contact;
import com.example.BlockBusters.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class ContactServiceImpl implements ContactService {
    ContactDao contactDao;
    ProfileService profileService;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao, ProfileService profileService) {
        this.contactDao = contactDao;
        this.profileService = profileService;
    }

    @Override
    public Contact makeContact(String name, String email, String phone, String message) throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        Profile profile = profileService.createProfile(name, email, phone);
        if (message.equals("")) {
            throw new NeedContactMessageError();
        }
        Contact contact = contactDao.createContact(profile, message);
        return contact;

    }
}
