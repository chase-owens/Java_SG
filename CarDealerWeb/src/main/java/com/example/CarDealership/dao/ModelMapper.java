/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Make;
import com.example.CarDealership.entity.Model;
import com.example.CarDealership.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class ModelMapper implements RowMapper<Model>{

    @Override
    public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
        Model model = new Model();
        
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        
        Make make = new Make();
        make.setMakeId(rs.getInt("makeId"));
        
        model.setCreatedBy(user);
        model.setMake(make);
        model.setDateAdded(rs.getTimestamp("dateAdded").toLocalDateTime());
        model.setModelName(rs.getString("modelName"));
        model.setId(rs.getInt("id"));
        
        return model;
    }
    
}
