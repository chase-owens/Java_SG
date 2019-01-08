/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Contact;
import com.example.BlockBusters.entity.Profile;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface ContactDao {
    //CRUD methods
    public Contact createContact(Profile profile, String message);
    public List<Contact> readAllContacts();
    public Contact readContactById(int id);
    public void updateContact(Contact contact);
    public void deleteContact(int id);
}
