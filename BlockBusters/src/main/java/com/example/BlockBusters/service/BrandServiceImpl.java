/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.dao.BrandDao;
import com.example.BlockBusters.entity.Brand;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class BrandServiceImpl implements BrandService {
    BrandDao brandDao;
    
    @Autowired
    public BrandServiceImpl(BrandDao brandDao) {
        this.brandDao = brandDao;
    }
    
    public Brand addBrand(String brandName) {
        return brandDao.createBrand();
    }
    
    public List<Brand> readAllBrands() {
        return brandDao.readAllBrands();
    }
    
}
