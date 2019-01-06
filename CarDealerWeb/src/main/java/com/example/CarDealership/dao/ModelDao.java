/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.dao;

import com.example.CarDealership.entity.Model;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface ModelDao {
    //CRUD methods
    public Model createModel();
    public List<Model> readAllModels();
    public Model readModelById(int id);
    public void updateModel(int id);
    public void deleteModel(int id);
}
