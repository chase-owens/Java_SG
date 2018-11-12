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
public class MovieDAOException extends Exception {
    String message;
    public MovieDAOException(String message, Throwable cause) {
        super(cause);
        this.message = message;  
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
