/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class GameMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet rs, int i) throws SQLException {
        Game game = new Game();
        game.setGameId(rs.getInt("gameId"));
        game.setAnswer(rs.getInt("answer"));
        game.setGameStatus(rs.getBoolean("gameStatus"));
        
        return game;
    }
    
}
