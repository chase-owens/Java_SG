/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author chaseowens
 */
public class BullCowControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String CONSTRAINT_MESSAGE = "Couldn't save the game.";
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSQLException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
