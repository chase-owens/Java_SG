/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.moviedatabase.controller;

import java.time.LocalDateTime;

/**
 *
 * @author chaseowens
 */
public class Error {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private String message;

    public Error(String message) {
        this.message = message;
    }

    public Error() {

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
