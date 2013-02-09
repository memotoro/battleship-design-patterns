/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.PositionSearching;

/**
 *
 * @author memotoro
 */
public class Shoot {

    private Integer state;
    private Position position;
    private PositionSearching positionSearching;

    public Shoot(PositionSearching positionSearching) {
        this.positionSearching = positionSearching;
    }

    public void searchPosition() {
        this.position = this.positionSearching.searchPositionToAttack();
    }

    public Position getPosition() {
        return position;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
