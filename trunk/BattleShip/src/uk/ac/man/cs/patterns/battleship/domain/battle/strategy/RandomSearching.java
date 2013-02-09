/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle.strategy;

import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.utils.RandomUtil;

/**
 *
 * @author memotoro
 */
public class RandomSearching extends PositionSearching {

    private Player playerAttacekd;

    public RandomSearching(Position positionHelper, Player playerAttacked) {
        super(positionHelper);
        this.playerAttacekd = playerAttacked;
    }

    public Position searchPositionToAttack() {
        return this.playerAttacekd.getBoard().getAvailablePosition();
    }
}
