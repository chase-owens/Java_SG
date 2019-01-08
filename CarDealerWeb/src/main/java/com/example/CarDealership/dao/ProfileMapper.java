/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Profile;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class ProfileMapper implements RowMapper<Profile>{

    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        
        profile.setFullName(rs.getString("fullName"));
        profile.setEmail(rs.getString("email"));
        profile.setNumber(rs.getString("phone"));
        profile.setStreetAddress(rs.getString("streetAddress"));
        profile.setZipcode(rs.getString("zipcode"));
        profile.setProfileId(rs.getInt("id"));
        
        return profile;
    }
    
}
