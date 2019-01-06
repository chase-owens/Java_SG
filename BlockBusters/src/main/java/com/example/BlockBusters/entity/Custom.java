/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.BlockBusters.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author chaseowens
 */
public class Custom {
    int customId, yearReleased;
    float mileage;
    Brand brand;
    Shoe shoe;
    String shoeType, customDescription, image, createdBy;
    BigDecimal price;
    boolean isFeatured, isAvailable;
    LocalDateTime dateAdded;
}
