/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle.strategy;

import uk.ac.man.cs.patterns.battleship.domain.battle.Position;

/**
 *
 * @author memotoro
 */
public class ConcreteSearching extends PositionSearching {

    public ConcreteSearching(Position positionHelper) {
        super(positionHelper);
    }

    public Position searchPositionToAttack() {
        return new Position(getPositionHelper().getCoordinateX(), getPositionHelper().getCoordinateY());
    }
}
