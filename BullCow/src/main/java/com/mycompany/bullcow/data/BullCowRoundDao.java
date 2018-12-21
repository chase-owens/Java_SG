/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullcow.data;

import com.mycompany.bullcow.models.Round;
import java.util.List;

/**
 *
 * @author chaseowens
 */
public interface BullCowRoundDao {

    public Round createRound(Round roundWithGuessChecked, int gameId);

    public List<Round> getRounds(int gameId);

    public Round getRoundById(int roundId);
    
}
