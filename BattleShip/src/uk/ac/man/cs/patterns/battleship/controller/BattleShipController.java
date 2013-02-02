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
public class BattleShipController implements IBattleShipController {

    private Game game;

    public Game startNewGame() {
        this.game = new Game();
        this.game.processState();
        return this.game;
    }

    public Position attack(Player playerReceived, int coordinateX, int coordinateY) throws BattleShipException {
        Position position = new Position(coordinateX, coordinateY);
        this.game.createAttack(playerReceived, position);
        return position;
    }

    public Position attack(Player playerReceived) throws BattleShipException {
        return this.game.createAttack(playerReceived);
    }

    public int lastShootState(Player player) {
        return player.getPreviousTurns().get(player.getPreviousTurns().size() - 1).getShoot().getState();
    }

    public int shipsAvailable(Player player) {
        return player.getBoard().getShipsAvailable();
    }

    public int gameStatus(){
        return this.game.getState();
    }


}
