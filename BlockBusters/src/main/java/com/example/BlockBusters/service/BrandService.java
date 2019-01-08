/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.entity.Brand;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface BrandService {
    public Brand createBrand(String makeName, int userId);
    
    public List<Brand> getAllBrands();
    
    public Brand readBrandById(int id);
}
