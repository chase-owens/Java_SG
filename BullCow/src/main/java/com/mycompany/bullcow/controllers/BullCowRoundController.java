/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.controllers;


import com.mycompany.bullcow.models.Round;
import com.mycompany.bullcow.service.BullCowRoundService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author chaseowens
 */
@RestController
@RequestMapping("/apiRound/")
public class BullCowRoundController {
    
    @Autowired
    BullCowRoundService service;
    
    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public Round createRound(int guess, int gameId) {
        Round round = service.createRound(guess, gameId);
        return round;
    }
    
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getRounds(@PathVariable int gameId) {
        List<Round> rounds = null;
        try {
            rounds = service.getRounds(gameId);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(rounds);
    }
    
    @GetMapping("/round/{roundId}")
    public Round getRound(@PathVariable int roundId) {
        Round round = service.getRoundById(roundId);
        return round;
    }
}
