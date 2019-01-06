/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.CarDealership.entity;

import java.time.LocalDateTime;

/**
 *
 * @author chaseowens
 */
public class Model {
    int modelId;
    String modelName, createdBy;
    Make make;
    LocalDateTime dateAdded;

    public int getId() {
        return modelId;
    }

    public void setId(int id) {
        this.modelId = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    
}
