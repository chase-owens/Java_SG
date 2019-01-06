/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.dao;

import com.example.BlockBusters.entity.Brand;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface BrandDao {
    //CRUD methods
    public Brand createBrand();
    public List<Brand> readAllBrands();
    public Brand readBrandById(int id);
    public void updateBrand(int id);
    public void deleteBrand(int id);
}
