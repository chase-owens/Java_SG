/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.model;

import java.math.BigDecimal;

/**
 *
 * @author chaseowens
 */
public class Product {
    String product;
    BigDecimal costPerSquareFoot, laborPerSquareFoot;
    
    
    public Product(String product, BigDecimal costPerSquareFoot, BigDecimal laborPerSquareFoot) {
        this.product = product;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborPerSquareFoot = laborPerSquareFoot;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborPerSquareFoot() {
        return laborPerSquareFoot;
    }
    
}
