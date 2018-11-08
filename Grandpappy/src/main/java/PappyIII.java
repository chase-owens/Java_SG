/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chaseowens
 */
public class PappyIII extends PappyJR {
    String favoriteSport;
    
    public PappyIII(String favoriteSport) {
        super(favoriteSport);
        this.favoriteSport = favoriteSport;
    }
    
    public PappyIII() {
        
    }

    public String getFavoriteSport() {
        return favoriteSport;
    }

    public void setFavoriteSport(String favoriteSport) {
        this.favoriteSport = favoriteSport;
    }
    
}
