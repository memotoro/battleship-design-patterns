package uk.ac.man.cs.patterns.battleship.domain.battle.strategy;

import uk.ac.man.cs.patterns.battleship.domain.battle.Position;

/**
 * Strategy Pattern. Abstract class to interchange different strategies to look for positions to attack.
 * @author Guillermo Antonio Toro Bayona
 */
public abstract class PositionSearching {

    /**
     * Position useful to calculate the next position to attack.
     */
    private Position positionHelper;

    /**
     * Constructor. Received a position to be used in the strategies.
     * @param positionHelper
     */
    public PositionSearching(Position positionHelper) {
        this.positionHelper = positionHelper;
    }

    /**
     * Abstract method that all the concrete classes must be implement. Inside this method every concrete strategy must apply an algorithm to search the next position to attack.
     * @return Position
     */
    public abstract Position searchPositionToAttack();

    /**
     * Get the position helper
     * @return Position
     */
    public Position getPositionHelper() {
        return positionHelper;
    }
}
