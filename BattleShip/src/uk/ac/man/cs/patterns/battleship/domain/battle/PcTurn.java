/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.NearbySearching;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.PositionSearching;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.RandomSearching;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 *
 * @author memotoro
 */
public class PcTurn extends Turn {

    @Override
    protected Shoot createShoot(Position positionReceived) {
        PositionSearching positionSearching = null;
        Shoot shoot = null;
        boolean validation = false;
        int sizeTurns = getPlayerAttacking().getPreviousTurns().size();
        while (!validation) {
            if (sizeTurns > 0) {
                Turn previousTurn = getPlayerAttacking().getLastSuccessfulTurn();
                if (previousTurn.getShoot().getState() == Constants.SHOOT_STATE_SUCCESSFUL) {
                    Position previousPosition = previousTurn.getShoot().getPosition();
                    positionSearching = new NearbySearching(previousPosition, getPlayerAttacked());
                    shoot = new Shoot(positionSearching);
                } else if (previousTurn.getShoot() != null && previousTurn.getShoot().getState() == Constants.SHOOT_STATE_MISSED) {
                    positionSearching = new RandomSearching(null, getPlayerAttacked());
                    shoot = new Shoot(positionSearching);
                }
            } else {
                positionSearching = new RandomSearching(null, getPlayerAttacked());
                shoot = new Shoot(positionSearching);
            }
            shoot.searchPosition();
            if (shoot.getPosition() == null) {
                sizeTurns = 0;
            } else {
                validation = true;
            }
        }
        return shoot;
    }
}
