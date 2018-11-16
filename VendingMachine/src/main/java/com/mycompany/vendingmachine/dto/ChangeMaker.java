/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author chaseowens
 */
public final class ChangeMaker {
    int hundred, fifty, twenty, ten, five, one, quarter, dime, nickel, penny;
    
    public ChangeMaker(BigDecimal change) {
        makeChange(change);
    }
    
    private void makeChange(BigDecimal change) {
        BigDecimal changeOwed = change;
        
        while(changeOwed.compareTo(new BigDecimal("99.99")) > 0) {
            this.hundred += 1;
            changeOwed.subtract(new BigDecimal("100"));
        }
        while(changeOwed.compareTo(new BigDecimal("49.99")) > 0) {
            this.fifty += 1;
            changeOwed.subtract(new BigDecimal("50"));
        }
        while(changeOwed.compareTo(new BigDecimal("19.99")) > 0) {
            this.twenty += 1;
            changeOwed.subtract(new BigDecimal("20"));
        }
        while(changeOwed.compareTo(new BigDecimal("9.99")) > 0) {
            this.ten += 1;
            changeOwed.subtract(BigDecimal.TEN);
        }
        while(changeOwed.compareTo(new BigDecimal("4.99")) > 0) {
            this.five += 1;
            changeOwed.subtract(new BigDecimal("5"));
        }
        while(changeOwed.compareTo(new BigDecimal(".99")) > 0) {
            this.one += 1;
            changeOwed.subtract(BigDecimal.ONE);
        }
        while(changeOwed.compareTo(new BigDecimal(".24")) > 0) {
            this.quarter += 1;
            changeOwed.subtract(new BigDecimal(".25"));
        }
        while(changeOwed.compareTo(new BigDecimal(".09")) > 0) {
            this.dime += 1;
            changeOwed.subtract(new BigDecimal(".10"));
        }
        while(changeOwed.compareTo(new BigDecimal(".04")) > 0) {
            this.nickel += 1;
            changeOwed.subtract(new BigDecimal(".05"));
        }
        while(changeOwed.compareTo(new BigDecimal(".005")) > 0) {
            this.penny += 1;
            changeOwed.subtract(new BigDecimal(".01"));
        }
    }

    public int getHundred() {
        return hundred;
    }

    public int getFifty() {
        return fifty;
    }

    public int getTwenty() {
        return twenty;
    }

    public int getTen() {
        return ten;
    }

    public int getFive() {
        return five;
    }

    public int getOne() {
        return one;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getDime() {
        return dime;
    }

    public int getNickel() {
        return nickel;
    }

    public int getPenny() {
        return penny;
    }
    
}
