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
 * Controller of the battle ship game.
 * Represent the controller between the View and the domain model.
 * @author Guillermo Antonio Toro Bayona
 */
public class BattleShipController implements IBattleShipController {

    /**
     * Game of battle ship
     */
    private Game game;

    /**
     * Create a new instance of the game.
     * @return Game created
     */
    public Game startNewGame() {
        this.game = new Game();
        return this.game;
    }

    /**
     * Method that represent an attack in the game.
     * Received the player that is attacking and the coordinated to be attacked.
     * @param playerReceived Player attacking
     * @param coordinateX Integer coordinate X
     * @param coordinateY Integer coordinate Y
     * @return Position attacked
     * @throws BattleShipException
     */
    public Position attack(Player playerReceived, Integer coordinateX, Integer coordinateY) throws BattleShipException {
        // Create a position with the coordinates
        Position position = new Position(coordinateX, coordinateY);
        // Call the attack method of the game
        this.game.attack(playerReceived, position);
        // Return the last position attacked in the turn.
        return this.game.getPlayerAttacked().getLastTurn().getShoot().getPosition();
    }

    /**
     * Method that return the number of the ships available in the board of the player
     * @param player Player of interest.
     * @return Integer of ships available
     */
    public Integer shipsAvailable(Player player) {
        return player.getBoard().getShipsAvailable();
    }

    /**
     * Method that return the state of the last shoot
     * @param player Player of interest
     * @return Integer with the state of the shoot.
     */
    public Integer lastShootState(Player player) {
        return player.getPreviousTurns().get(player.getPreviousTurns().size() - 1).getShoot().getState();
    }

    /**
     * Method that return the status of the game
     * @return Integer with the state of the game
     */
    public Integer gameStatus() {
        return this.game.getState();
    }
}
