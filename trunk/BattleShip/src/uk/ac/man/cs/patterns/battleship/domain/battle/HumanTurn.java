/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.ConcreteSearching;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.PositionSearching;

/**
 *
 * @author memotoro
 */
public class HumanTurn extends Turn {

    @Override
    protected Shoot createShoot(Position positionReceived) {
        PositionSearching positionSearching = new ConcreteSearching(positionReceived);
        Shoot shoot = new Shoot(positionSearching);
        shoot.searchPosition();
        return shoot;
    }
}
