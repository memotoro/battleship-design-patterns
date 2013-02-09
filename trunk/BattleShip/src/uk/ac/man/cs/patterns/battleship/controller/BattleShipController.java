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
public class BattleShipController implements IBattleShipController {

    private Game game;

    public Game startNewGame() {
        this.game = new Game();
        return this.game;
    }

    public Position attack(Player playerReceived, Integer coordinateX, Integer coordinateY) throws BattleShipException {
        Position position = new Position(coordinateX, coordinateY);
        this.game.attack(playerReceived, position);
        return this.game.getPlayerAttacked().getLastTurn().getShoot().getPosition();
    }

    public Integer shipsAvailable(Player player) {
        return player.getBoard().getShipsAvailable();
    }

    public Integer lastShootState(Player player) {
        return player.getPreviousTurns().get(player.getPreviousTurns().size() - 1).getShoot().getState();
    }

    public Integer gameStatus() {
        return this.game.getState();
    }
}
