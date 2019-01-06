/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Contact;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface ContactDao {
    //CRUD methods
    public Contact createContact();
    public List<Contact> readAllContacts();
    public Contact readContactById(int id);
    public void updateContact(int id);
    public void deleteContact(int id);
}
