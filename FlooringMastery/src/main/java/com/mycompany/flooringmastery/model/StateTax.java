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
public class StateTax {
    String state;
    BigDecimal taxRate;
    
    public StateTax(String state, BigDecimal taxRate) {
        this.state = state;
        this.taxRate = taxRate;
    }
    
    public StateTax() {
        
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.state);
        hash = 59 * hash + Objects.hashCode(this.taxRate);
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
        final StateTax other = (StateTax) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        return true;
    }
    
    
}
