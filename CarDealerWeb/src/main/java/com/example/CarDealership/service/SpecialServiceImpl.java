/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.dao.SpecialDao;
import com.example.CarDealership.entity.Special;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class SpecialServiceImpl implements SpecialService{
    SpecialDao specialDao;
    
    @Autowired
    public SpecialServiceImpl(SpecialDao specialDao) {
        this.specialDao = specialDao;
    }

    @Override
    public Special createSpecial(String title, String description, int vehicleId,LocalDate dateBegin, LocalDate dateEnd, int userId) {
        return specialDao.createSpecial(title, description, vehicleId, dateBegin, dateEnd, userId);
    }

    @Override
    public List<Special> getAllSpecials() {
        return specialDao.readAllSpecials();
    }

    @Override
    public Special getSpecialById(int id) {
        return specialDao.readSpecialById(id);
    }

    @Override
    public void deleteSpecial(int id) {
        specialDao.deleteSpecial(id);
    }
}
