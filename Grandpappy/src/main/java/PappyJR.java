/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class PappyJR extends Grandpappy {
    boolean hasHair = true;
    
    public PappyJR(String hairColor) {
        this.hairColor = hairColor;
    }
    
    public PappyJR() {
        
    }

    public boolean isHasHair() {
        return hasHair;
    }

    public void setHasHair(boolean hasHair) {
        this.hasHair = hasHair;
    }
    
}
