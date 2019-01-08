/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class MakeMapper implements RowMapper<Make>{

    @Override
    public Make mapRow(ResultSet rs, int rowNum) throws SQLException {
        Make make = new Make();
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        
        make.setMakeName(rs.getString("makeName"));
        make.setDateAdded(rs.getTimestamp("dateAdded").toLocalDateTime());
        make.setMakeId(rs.getInt("id"));
        make.setCreatedBy(user);
        
        return make;
    }
    
}
