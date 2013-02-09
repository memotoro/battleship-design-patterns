/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle.strategy;

import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;

/**
 *
 * @author memotoro
 */
public abstract class PositionSearching {

    private Position positionHelper;

    public PositionSearching(Position positionHelper) {
        this.positionHelper = positionHelper;
    }

    public abstract Position searchPositionToAttack();

    public Position getPositionHelper() {
        return positionHelper;
    }
}
