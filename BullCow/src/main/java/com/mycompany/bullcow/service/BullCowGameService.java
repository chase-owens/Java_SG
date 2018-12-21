/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.service;

import com.mycompany.bullcow.data.BullCowGameDao;
import com.mycompany.bullcow.models.Game;
import com.mycompany.bullcow.models.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class BullCowGameService {
    @Autowired
    BullCowGameDao dao;
    
    @Autowired
    BullCowRoundService service;
    
    // C
    public Game createGame() {
        int randomNumber = getNumber();
        Game game = new Game(randomNumber);
        return filterGame(dao.createGame(game));
    }
    
    // R
    public List<Game> getGames() {
        return filterGames(dao.getGames());
    }
    
    // R
    public Game getGameById(int id) {
        return filterGame(dao.getGameById(id));
    }
    
    // U
    public boolean updateGame(Round round, int gameId) {
        boolean needsUpdating = false;
        Game game = dao.getGameById(gameId);
        ResponseEntity response = null;
        if (round.exactMatches == 4) {
            game.setGameStatus(false);
            boolean isUpdated = dao.updateGame(game);
            if (isUpdated) {
                response = new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }
        return needsUpdating;
    }
    
    // Generate a 4 digit number where no digits are repeated
    public int getNumber() {
        int[] numberArray = new int[4];
        Random randomizer = new Random();
        
        do {
            numberArray[0] = randomizer.nextInt(9);
        } while (numberArray[0] == 0);
        
        do {
            numberArray[1] = randomizer.nextInt(9);
        } while (numberArray[1] == numberArray[0]);
        
        do {
            numberArray[2] = randomizer.nextInt(9);
        } while (numberArray[2] == numberArray[1] || numberArray[2] == numberArray[0]);
        
        do {
            numberArray[3] = randomizer.nextInt(9);
        } while (numberArray[3] == numberArray[2] || numberArray[3] == numberArray[1] || numberArray[3] == numberArray[0]);
        
        int number = 0;
        for (int num : numberArray) {
            number = 10*number + num;
        }
        return number;
    }
    
    public Game filterGame(Game game) {
        if(game.isGameStatus()) {
            game.setAnswer(0);
        }
        return game;
    }
    
    public List<Game> filterGames(List<Game> games) {
        games.stream().forEach(game -> filterGame(game));
        return games;
    }

    Game getGameByIdUnfiltered(int gameId) {
        return dao.getGameById(gameId);
    }
}
