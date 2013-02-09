/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.controller;

import java.util.List;
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

    public Position attack(Player playerReceived, Integer coordinateX, Integer coordinateY) throws BattleShipException;

    public Integer shipsAvailable(Player player);

    public Integer lastShootState(Player player);

    public Integer gameStatus();
}
