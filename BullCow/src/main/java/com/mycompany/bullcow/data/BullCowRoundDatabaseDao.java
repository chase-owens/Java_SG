/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.data;

import com.mycompany.bullcow.models.Round;
import com.mycompany.bullcow.models.RoundMapper;
import java.sql.Timestamp;
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
public class BullCowRoundDatabaseDao implements BullCowRoundDao {
    JdbcTemplate jdbc;
    
    @Autowired
    public BullCowRoundDatabaseDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    @Transactional
    public Round createRound(Round round,  int gameId) {
        // Add round to round table
        Timestamp timeStamp = Timestamp.valueOf(round.getTime());
        final String CREATE_ROUND = "INSERT INTO aRound(roundNumber, timeOfGuess, exactMatches, partialMatches, guess) VALUES(?, ?, ?, ?, ?);";
        jdbc.update(CREATE_ROUND,
                round.getRoundNumber(),
                timeStamp, 
                round.getExactMatches(),
                round.getPartialMatches(),
                round.getGuess());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        // Add round to GameRound table
        final String POPULATE_GAMEROUND_BRIDGE = "INSERT INTO GameRound(gameId, roundId) VALUES (?,?);";
        jdbc.update(POPULATE_GAMEROUND_BRIDGE, gameId, newId);
        
        // Set the assigned id to the round and return it
        round.setRoundId(newId);
        return round;
    }

    @Override
    public List<Round> getRounds(int gameId) {
        final String GET_ROUNDS = "SELECT * FROM aRound INNER JOIN GameRound ON GameRound.roundId = aRound.roundId WHERE GameRound.gameId = ?;";
        List<Round> gameRounds = jdbc.query(GET_ROUNDS, new RoundMapper(), gameId);
        return gameRounds;
    }

    @Override
    public Round getRoundById(int roundId) {
        final String GET_ROUND_BY_ID = "SELECT * FROM aRound WHERE roundId = ?;";
        Round round = jdbc.queryForObject(GET_ROUND_BY_ID, new RoundMapper(), roundId);
        return round;
    }
    
}
