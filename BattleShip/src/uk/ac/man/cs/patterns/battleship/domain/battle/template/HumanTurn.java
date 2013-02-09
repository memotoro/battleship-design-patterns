/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle.template;

import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.domain.battle.Shoot;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.ConcreteSearching;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.PositionSearching;

/**
 * Human Turn. Concrete turn for human player.
 * This class implement the abstract method from the abstract Turn.
 * Template Pattern.
 * @author Guillermo Antonio Toro Bayona
 */
public class HumanTurn extends Turn {

    /**
     * Concrete implementation of the create shoot step.
     * @param positionReceived Position to be attacked.
     * @return Shoot created
     */
    @Override
    protected Shoot createShoot(Position positionReceived) {
        // Create a concrete strategy with the position to be attacked.
        PositionSearching positionSearching = new ConcreteSearching(positionReceived);
        // Create a shot and set the position.
        Shoot shoot = new Shoot(positionSearching);
        // Executed the searching strategy method.
        shoot.searchPosition();
        // Return shoot.
        return shoot;
    }
}
