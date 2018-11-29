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
public class StateTax {
    String state;
    BigDecimal taxRate;
    
    public StateTax(String state, BigDecimal taxRate) {
        this.state = state;
        this.taxRate = taxRate;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
    
}
