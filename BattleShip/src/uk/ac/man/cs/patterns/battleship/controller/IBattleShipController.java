package uk.ac.man.cs.patterns.battleship.controller;

import uk.ac.man.cs.patterns.battleship.domain.battle.Game;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;

/**
 * Interface of the Controller of the battle ship game. Expose the public operations that controller offers to the View.
 * @author Guillermo Antonio Toro Bayona
 */
public interface IBattleShipController {

    /**
     * Create a new instance of the game.
     * @return Game created
     */
    public Game startNewGame();

    /**
     * Method that represent an attack in the game. Received the player that is attacking and the coordinated to be attacked.
     * @param playerReceived Player attacking
     * @param coordinateX Integer coordinate X
     * @param coordinateY Integer coordinate Y
     * @return Position attacked
     * @throws BattleShipException
     */
    public Position attack(Player playerReceived, Integer coordinateX, Integer coordinateY) throws BattleShipException;
}
