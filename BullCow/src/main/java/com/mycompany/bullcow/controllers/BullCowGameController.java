/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.controllers;

import com.mycompany.bullcow.models.Game;
import com.mycompany.bullcow.service.BullCowGameService;
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
@RequestMapping("/apiGame/")
public class BullCowGameController {

    @Autowired
    BullCowGameService service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame() {
        Game game = service.createGame();
        return game;
        // manage connection
    }

    @GetMapping("/games")
    public List<Game> getGames() {
        if (service.getGames().isEmpty()) {
            return null;
        }
        return service.getGames();
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game game = null;
        try {
            game = service.getGameById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }

}
