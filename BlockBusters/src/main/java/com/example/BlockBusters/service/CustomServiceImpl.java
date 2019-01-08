/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.service;

import com.example.BlockBusters.dao.CustomDao;
import com.example.BlockBusters.entity.Brand;
import com.example.BlockBusters.entity.Custom;
import com.example.BlockBusters.entity.Shoe;
import com.example.BlockBusters.entity.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class CustomServiceImpl implements CustomService {

    CustomDao customDao;
    BrandService brandService;
    ShoeService shoeService;
    UserService userService;

    @Autowired
    public CustomServiceImpl(CustomDao customDao, BrandService brandService, ShoeService shoeService, UserService userService) {
        this.customDao = customDao;
        this.brandService = brandService;
        this.shoeService = shoeService;
        this.userService = userService;
    }

    @Override
    public Custom createCustom(int brandId, int shoeId, String sizeString, int yearReleased, String shoeType, String customDescription, String image, String priceString, int userId) throws DataValidationError {
        BigDecimal price;
        float size;
        Brand brand;
        Shoe shoe;
        if (shoeType.equals("") || customDescription.equals("") || image.equals("") || priceString.equals("")) {
            throw new DataValidationError();
        }

        if (!image.endsWith("jpg") || !image.endsWith("jepg") || !image.endsWith("png")) {
            throw new DataValidationError();
        }

        if (!shoeType.equals("low") || !shoeType.equals("mid") || !shoeType.equals("high")) {
            throw new DataValidationError();
        }

        try {
            size = Float.parseFloat(sizeString);
            
            price = new BigDecimal(priceString);
            if (price.compareTo(BigDecimal.ZERO) < 1) {
                throw new DataValidationError();
            }

            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            if (yearReleased < 2000 || yearReleased > currentYear + 1) {
                throw new DataValidationError();
            }

            brand = brandService.readBrandById(userId);
            shoe = shoeService.readShoeById(shoeId);
        } catch (NumberFormatException e) {
            throw new DataValidationError();
        }

        Custom newCustom = customDao.createCustom(brand, shoe, size, yearReleased, shoeType, customDescription, image, priceString, userId);
        return newCustom;
    }

    @Override
    public Custom readCustomByInt(int customId) {
        return customDao.readCustomById(customId);
    }

    @Override
    public List<Custom> query20CustomWithFilters(String query, String artist, BigDecimal minPrice, BigDecimal maxPrice, int size) {
        return customDao.query20CustomsByArtistPriceAndSizeDescendingPrice(query, artist, minPrice, maxPrice, size);
    }

    @Override
    public void updateCustom(int customId, int shoeId, String sizeString, int yearReleased, String shoeType, String customDescription, String image, String priceString, String isFeatured, int userId) throws DataValidationError {
        BigDecimal price;
        float size;
        Brand brand;
        Shoe shoe;
        
        Custom customBeingUpdated;
        
        if (shoeType.equals("") || customDescription.equals("") || priceString.equals("")) {
            throw new DataValidationError();
        }

        if (!image.endsWith("jpg") || !image.endsWith("jepg") || !image.endsWith("png") || image.equals("")) {
            throw new DataValidationError();
        }

        if (!shoeType.equals("low") || !shoeType.equals("mid") || !shoeType.equals("high")) {
            throw new DataValidationError();
        }

        try {
            customBeingUpdated = customDao.readCustomById(customId);
            
            size = Float.parseFloat(sizeString);
                
            price = new BigDecimal(priceString);
            if (price.compareTo(BigDecimal.ZERO) < 1) {
                throw new DataValidationError();
            }

            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            if (yearReleased < 2000 || yearReleased > customBeingUpdated.getDateAdded().getYear()) {
                throw new DataValidationError();
            }

            brand = brandService.readBrandById(userId);
            shoe = shoeService.readShoeById(shoeId);
        } catch (NumberFormatException e) {
            throw new DataValidationError();
        }
        
        customBeingUpdated.setBrand(brand);
        customBeingUpdated.setShoe(shoe);
        customBeingUpdated.setSizee(size);
        customBeingUpdated.setCustomDescription(customDescription);
        customBeingUpdated.setYearReleased(yearReleased);
        customBeingUpdated.setShoeType(shoeType);
        if (!image.equals("")) {
            customBeingUpdated.setImage(image);
        }
        customBeingUpdated.setPrice(price);
        User currentUser = userService.readUserByid(userId);
        customBeingUpdated.setCreatedBy(currentUser);

        customDao.updateCustom(customBeingUpdated);
    }

    @Override
    public void markAsSold(int customId) {
        Custom custom = customDao.readCustomById(customId);
        custom.setIsAvailable(false);
        customDao.updateCustom(custom);
    }
}
