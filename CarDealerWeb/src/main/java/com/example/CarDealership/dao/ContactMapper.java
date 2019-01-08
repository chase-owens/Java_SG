/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Contact;
import com.example.CarDealership.entity.Profile;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class ContactMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profileId"));
        
        Contact contact = new Contact();
        contact.setProfile(profile);
        contact.setContactId(rs.getInt("id"));
        contact.setProfile(profile);
        contact.setMessage(rs.getString("message"));
        contact.setTimePosted(rs.getTimestamp(rs.getInt("timePosted")).toLocalDateTime());
        
        return contact;
    }
    
}
