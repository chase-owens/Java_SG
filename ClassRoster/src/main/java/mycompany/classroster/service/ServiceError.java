/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycompany.classroster.service;

/**
 *
 * @author chaseowens
 */
public class ServiceError extends Exception {
    public String message;
    
    public ServiceError(String message) {
        this.message = message;
    }
}
