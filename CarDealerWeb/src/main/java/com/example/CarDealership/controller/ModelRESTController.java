/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.controller;

import com.example.CarDealership.entity.Model;
import com.example.CarDealership.service.DataValidationError;
import com.example.CarDealership.service.ModelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
@RequestMapping("/modelApi/")
public class ModelRESTController {

    ModelService service;
    
    @Autowired
    public ModelRESTController(ModelService service) {
        this.service = service;
    }
    
        @PostMapping("/createModel")
    public ResponseEntity<Model> createModel(int makeId, String modelName, int userId) {
        Model model = null;
        try {
            model = service.createModel(makeId, modelName, userId);
        } catch (DataValidationError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(model);
    }
    
    @GetMapping("/readModel")
    public ResponseEntity<Model> readMakeById(int id) {
        Model model = service.readModelById(id);
        if (model == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(model);
    }
    
    @GetMapping("/readModels")
    public ResponseEntity<List<Model>> getAllMakes(){
        List<Model> model = service.readAllModels();
        if (model == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(model);
    }
    
    @PutMapping("/updateModel")
    public boolean updateModel(int makeId, String modelName, int modelId){
        boolean didUpdate = true;
        try {
            service.updateModel(makeId, modelName, modelId);
        } catch (DataValidationError e) {
            didUpdate = false;
        }
        return didUpdate;
    }
}
