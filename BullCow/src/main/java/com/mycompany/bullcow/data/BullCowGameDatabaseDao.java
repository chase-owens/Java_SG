/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.data;

import com.mycompany.bullcow.models.Game;
import com.mycompany.bullcow.models.GameMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chaseowens
 */
@Repository
public class BullCowGameDatabaseDao implements BullCowGameDao {
    JdbcTemplate jdbc = new JdbcTemplate();
    
    @Autowired
    public BullCowGameDatabaseDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    @Transactional
    public Game createGame(Game game) {
        final String CREATE_GAME = "INSERT INTO Game(answer, gameStatus) VALUES(?,?);";
        
        jdbc.update(CREATE_GAME, 
                game.getAnswer(),
                game.isGameStatus());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        game.setGameId(newId);
        
        return game;
    }

    @Override
    public List<Game> getGames() {
        List<Game> allGames;
        
        final String GET_GAMES_WITH_ANSWER = "SELECT * FROM Game WHERE gameStatus = ?;";
        List<Game> gamesWithAnswer = jdbc.query(GET_GAMES_WITH_ANSWER, new GameMapper(), false);
        
        final String GET_GAMES_WITHOUT_ANSWER = "SELECT gameId, gameStatus FROM Game WHERE gameStatus = ?;";
        List<Game> gamesWithoutAnswer = jdbc.query(GET_GAMES_WITH_ANSWER, new GameMapper(), true);
        
        allGames = gamesWithAnswer;
        allGames.addAll(gamesWithoutAnswer);
        return allGames;
    }

    @Override
    public Game getGameById(int id) {
        final String GET_GAMES_BY_ID = "SELECT * FROM Game WHERE gameId = ?;";
        return jdbc.queryForObject(GET_GAMES_BY_ID, new GameMapper(), id);
    }

    @Override
    public boolean updateGame(Game game) {
        boolean isUpdated = true;
        final String UPDATE_GAME = "UPDATE Game SET gameStatus = ? WHERE Game.gameId = ?;";
        jdbc.update(UPDATE_GAME, game.isGameStatus(), game.getGameId());
        return isUpdated;
    }
    
    
}
