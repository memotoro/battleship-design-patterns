package uk.ac.man.cs.patterns.battleship.domain.battle.strategy;

import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;

/**
 * Strategy Pattern.Random Strategy is a concrete strategy to look for position randomly.
 * The position to attack are selected randomly from the available position in the board.
 * @author Guillermo Antonio Toro Bayona
 */
public class RandomSearching extends PositionSearching {

    /**
     * Player to be attacked. Is necessary to have access to its board.
     */
    private Player playerAttacekd;

    /**
     * Constructor. Received a helper position and the player to be attacked.
     * @param positionHelper
     * @param playerAttacked
     */
    public RandomSearching(Position positionHelper, Player playerAttacked) {
        super(positionHelper);
        this.playerAttacekd = playerAttacked;
    }

    /**
     * Concrete implementation of search position to attack. In this case, the algorithm look for available positions in the board and select one randomly.
     * @return Position
     */
    public Position searchPositionToAttack() {
        // Call the board for available position.
        return this.playerAttacekd.getBoard().getAvailablePosition();
    }
}
