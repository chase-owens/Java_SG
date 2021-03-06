/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.service;

import com.example.CarDealership.entity.Model;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface ModelService {
    public Model createModel(int makeId, String modelName, int userId)throws DataValidationError;
    
    public Model readModelById(int id);
    
    public List<Model> readAllModels();
    
    public void updateModel(int makeId, String modelName, int modelId)throws DataValidationError;

    public List<Model> readAllModelsByMakeId(int makeId);

    public Model readModelByName(String modelName);
}
