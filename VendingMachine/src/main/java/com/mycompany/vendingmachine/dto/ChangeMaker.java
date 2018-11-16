/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author chaseowens
 */
public final class ChangeMaker {
    int hundred = 0, fifty = 0, twenty = 0, ten = 0, five = 0, one = 0, quarter = 0, dime = 0, nickel = 0, penny = 0;
    
    public ChangeMaker(BigDecimal change) {
        makeChange(change);
    }
    
    private void makeChange(BigDecimal change) {
        BigDecimal bdChange = change.setScale(2, RoundingMode.HALF_UP);
        String changeToGive = bdChange.toString();
        float changeOwed = Float.parseFloat(changeToGive);
        
        while(changeOwed >= 100) {
            this.hundred += 1;
            changeOwed -= 100;
        }
        while(changeOwed >= 50) {
            this.fifty += 1;
            changeOwed -= 50;
        }
        while(changeOwed >= 20) {
            this.twenty += 1;
            changeOwed -= 20;
        }
        while(changeOwed >= 10) {
            this.ten += 1;
            changeOwed -= 10;
        }
        while(changeOwed >= 5) {
            this.five += 1;
            changeOwed -= 5;
        }
        while(changeOwed >= 1) {
            this.one += 1;
            changeOwed -= 1;
        }
        while(changeOwed >= .25) {
            this.quarter += 1;
            changeOwed -= .25;
        }
        while(changeOwed >= .10) {
            this.dime += 1;
            changeOwed -= .10;
        }
        while(changeOwed >= .05) {
            this.nickel += 1;
            changeOwed -= .05;
        }
        while(changeOwed >= .01) {
            this.penny += 1;
            changeOwed -= .01;
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
