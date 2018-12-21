/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.models;

/**
 *
 * @author chaseowens
 */
public class Game {
    int gameId;
    int answer;
    boolean gameStatus = true;
    
    public Game(int number) {
        this.answer = number;
    }
    
    public Game() {
        
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }

    public boolean isGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }
    
    public void setGameId(int id) {
        this.gameId = id;
    }

    public int getGameId() {
        return gameId;
    }
    
}
