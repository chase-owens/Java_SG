/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.dao.ModelDao;
import com.example.CarDealership.entity.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class ModelServiceImpl implements ModelService{
    ModelDao modelDao;
    
    @Autowired
    public ModelServiceImpl(ModelDao modelDao) {
        this.modelDao = modelDao;
    }
    
    @Override
    public Model createModel(int makeId, String modelName, int userId) {
        return modelDao.createModel(makeId, modelName, userId) ;
    }
    
    @Override
    public List<Model> readAllModels() {
        return modelDao.readAllModels();
    }
}
