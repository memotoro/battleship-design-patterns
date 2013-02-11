package uk.ac.man.cs.patterns.battleship.domain.battle.strategy;

import uk.ac.man.cs.patterns.battleship.domain.battle.Position;

/**
 * Strategy Pattern. Concrete searching is a strategy to set the position to attack with specific position given.
 * @author Guillermo Antonio Toro Bayona
 */
public class ConcreteSearching extends PositionSearching {

    /**
     * Constructor. Received a helper position and the player to be attacked.
     * @param positionHelper
     * @param playerAttacked
     */
    public ConcreteSearching(Position positionHelper) {
        super(positionHelper);
    }

    /**
     * Concrete implementation of the searching algorithm for concrete searching.In this case, the method return the position helper.
     * @return Position
     */
    public Position searchPositionToAttack() {
        return new Position(getPositionHelper().getCoordinateX(), getPositionHelper().getCoordinateY());
    }
}
