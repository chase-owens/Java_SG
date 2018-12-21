/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.data;

import com.mycompany.bullcow.models.Game;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface BullCowGameDao {

    public Game createGame(Game game);

    public List<Game> getGames();

    public Game getGameById(int id);

    public boolean updateGame(Game game);
    
}
