/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.service;

import com.mycompany.bullcow.data.BullCowRoundDao;
import com.mycompany.bullcow.models.Game;
import com.mycompany.bullcow.models.Round;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chaseowens
 */
@Service
public class BullCowRoundService {

    @Autowired
    BullCowRoundDao dao;
    
    @Autowired
    BullCowGameService service;
    // C
    public Round createRound(int guess, int gameId) {
        List<Round> rounds = dao.getRounds(gameId);
        Game game = service.getGameByIdUnfiltered(gameId);
        int roundNumber;
        if (rounds.isEmpty()) {
            roundNumber = 1;
        } else {
            // Returnung 2 of each of my things
            roundNumber = rounds.size() + 1;
        }
        Round round = new Round(guess, roundNumber);
        Round roundWithGuessChecked = checkGuess(round, game);
        return dao.createRound(roundWithGuessChecked, gameId);
    }
    
    // R
    public List<Round> getRounds(int gameId) {
        return dao.getRounds(gameId);
    }
    
    // R 
    public Round getRoundById(int roundId) {
        return dao.getRoundById(roundId);
    }

    public int[] buildArray(int number) {
        char[] num = Integer.toString(number).toCharArray();
        int[] array = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            array[i] = num[i];
        }
        return array;
    }

    public Round checkGuess(Round round, Game game) {
        if (round.getGuess() == game.getAnswer()) {
            round.exactMatches = 4;
            round.partialMatches = 0;
            service.updateGame(round, game.getGameId());
            return round;
        }
        
        int[] answerArray = buildArray(game.getAnswer());
        int[] guessArray = buildArray(round.getGuess());

        for (int i = 0; i < 4; i++) {
            if (guessArray[i] == answerArray[i]) {
                round.exactMatches += 1;
            } else {
                for (int k = i + 1; k < 4; k++) {
                    if (guessArray[i] == answerArray[k]) {
                        round.partialMatches += 1;
                    }
                }
            }
        }
        return round;
    }
}
