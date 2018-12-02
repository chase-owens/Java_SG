/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmastery.model;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.product);
        hash = 37 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 37 * hash + Objects.hashCode(this.laborPerSquareFoot);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborPerSquareFoot, other.laborPerSquareFoot)) {
            return false;
        }
        return true;
    }
    
}
