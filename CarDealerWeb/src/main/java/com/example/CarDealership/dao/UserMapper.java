/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Profile;
import com.example.CarDealership.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profileId"));
        user.setProfile(profile);
        
        LocalDateTime dateAdded = rs.getTimestamp("dateAdded").toLocalDateTime();
        
        user.setPassword(rs.getString("userPassword"));
        user.setRole(rs.getString("userRole"));
        user.setDateAdded(dateAdded);
        user.setUserId(rs.getInt("id"));
//        user.setNumberOfSales(rs.getInt("numberOfSales"));
//        user.setNetSales(rs.getBigDecimal("netSales"));
        
        return user;
    }
    
}
