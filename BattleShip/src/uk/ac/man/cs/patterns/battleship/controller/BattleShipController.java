package uk.ac.man.cs.patterns.battleship.controller;

import uk.ac.man.cs.patterns.battleship.domain.battle.Game;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;

/**
 * Controller of the battle ship game. Represent the controller between the View and the domain model.
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
     * Method that represent an attack in the game. Received the player that is attacking and the coordinated to be attacked.
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
}
