/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.man.cs.patterns.battleship.controller;

import uk.ac.man.cs.patterns.battleship.domain.battle.Game;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;

/**
 *
 * @author memotoro
 */
public interface IBattleShipController {

    public Game startNewGame();  

    public Position attack(Player playerReceived, int coordinateX, int coordinateY) throws BattleShipException;

    public Position attack(Player playerReceived) throws BattleShipException;

    public int shipsAvailable(Player player);
    
    public int lastShootState(Player player);

    public int gameStatus();
}
