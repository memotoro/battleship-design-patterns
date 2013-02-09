/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle.strategy;

import uk.ac.man.cs.patterns.battleship.domain.battle.Position;

/**
 * Concrete searching is a strategy to set the position to attack with specific position given.
 * This strategy is used by the human player.
 * Strategy Pattern.
 * @author Guillermo Antonio Toro Bayona
 */
public class ConcreteSearching extends PositionSearching {

    /**
     * Constructor.
     * Received a helper position and the player to be attacked.
     * @param positionHelper
     * @param playerAttacked
     */
    public ConcreteSearching(Position positionHelper) {
        super(positionHelper);
    }

    /**
     * Concrete implementation of the searching algorithm for concrete searching.
     * In this case, the method return the position helper.
     * @return Position
     */
    public Position searchPositionToAttack() {
        return new Position(getPositionHelper().getCoordinateX(), getPositionHelper().getCoordinateY());
    }
}
