/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.dto;

/**
 *
 * @author chaseowens
 */
public class DVD {
    private String title, releaseDate, MPAArating, directorsName, studio, userRating;
    
    public DVD(String title, String releaseDate, String MPAArating, String directorsName, String studio, String userRating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.MPAArating = MPAArating;
        this.directorsName = directorsName;
        this.studio = studio;
        this.userRating = userRating;
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return releaseDate;
    }

    public void setDate(String date) {
        this.releaseDate = date;
    }

    public String getMPAArating() {
        return MPAArating;
    }

    public void setMPAArating(String MPAArating) {
        this.MPAArating = MPAArating;
    }

    public String getDirectorsName() {
        return directorsName;
    }

    public void setDirectorsName(String directorsName) {
        this.directorsName = directorsName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRating() {
        return userRating;
    }

    public void setRating(String rating) {
        this.userRating = rating;
    }
    
    
}
