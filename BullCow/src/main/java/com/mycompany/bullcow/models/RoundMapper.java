/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author chaseowens
 */
public class RoundMapper implements RowMapper<Round> {

    @Override
    public Round mapRow(ResultSet rs, int i) throws SQLException {
        LocalDateTime time = rs.getTimestamp("timeOfGuess").toLocalDateTime();
        Round round = new Round();
        
        round.setRoundId(rs.getInt("roundId"));
        round.setRoundNumber(rs.getInt("roundNumber"));
        round.setTime(time);
        round.setExactMatches(rs.getInt("exactMatches"));
        round.setPartialMatches(rs.getInt("partialMatches"));
        round.setGuess(rs.getInt("guess"));
        
        return round;
    }
    
}
