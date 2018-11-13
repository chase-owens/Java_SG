/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.greatparty;

/**
 *
 * @author chaseowens
 */
public class GreatParty {
    
    // When squirrels get together for a party, they like to have cigars. 
    // A squirrel party is  successful when the number of cigars is between 
    // 40 and 60, inclusive. Unless it is the weekend, in which case there  
    // is no upper bound on the number of cigars. Return true if the party 
    // with the given values is successful, or false otherwise. 
    //
    // greatParty(30, false) → false
    // greatParty(50, false) → true
    // greatParty(70, true) → true
    
    int numberOfCigars;
    boolean isWeekend;
    boolean isASuccess;
    
    public GreatParty(int numberOfCigars, boolean isWeekend) {
        this.numberOfCigars = numberOfCigars;
        this.isWeekend = isWeekend;
        
        checkSuccess(numberOfCigars, isWeekend);
    }
    
    public GreatParty() {
        
    }

    public int getNumberOfCigars() {
        return numberOfCigars;
    }

    public void setNumberOfCigars(int numberOfCigars) {
        this.numberOfCigars = numberOfCigars;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        this.isWeekend = weekend;
    }

    public final boolean checkSuccess(int numberOfCigars, boolean isWeekend) {
        boolean success;
        if ((numberOfCigars >= 40 && numberOfCigars <= 60 && isWeekend == false) || (numberOfCigars >= 40 && isWeekend == true)) {
            this.isASuccess = true;
            success = true;
        } else {
            this.isASuccess = false;
            success = false;
        }
        return success;
    }
}
