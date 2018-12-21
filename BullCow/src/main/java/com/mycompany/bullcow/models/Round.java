/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.models;

import java.time.LocalDateTime;

/**
 *
 * @author chaseowens
 */
public class Round {
    public int roundId, roundNumber, guess, exactMatches = 0, partialMatches = 0;
    LocalDateTime time;
    
    public Round(int guess, int round) {
        this.guess = guess;
        this.roundNumber = round;
        this.time = time.now();
    }
    
    public Round() {
        
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setExactMatches(int exactMatches) {
        this.exactMatches = exactMatches;
    }

    public void setPartialMatches(int partialMatches) {
        this.partialMatches = partialMatches;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getExactMatches() {
        return exactMatches;
    }

    public int getPartialMatches() {
        return partialMatches;
    }

    public int getGuess() {
        return guess;
    }

    public int getRoundId() {
        return roundId;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }
    
}
