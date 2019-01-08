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

    @Override
    public Brand createBrand(String makeName, int userId) {
        return brandDao.createBrand(makeName, userId);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandDao.readAllBrands();
    }

    @Override
    public Brand readBrandById(int id) {
        return brandDao.readBrandById(id);
    }
    
}
