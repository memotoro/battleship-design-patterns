/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Observer;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Subject;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.PositionSearching;
import uk.ac.man.cs.patterns.battleship.domain.ships.Ship;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * Shoot.
 * Represent a shot in the game.
 * @author Guillermo Antonio Toro Bayona
 */
public class Shoot {

    /**
     * Integer with the state of the shoot.
     */
    private Integer state;
    /**
     * Position attacked in the shoot.
     */
    private Position position;
    /**
     * PositionSearching related with the strategy to be selected to create positions.
     */
    private PositionSearching positionSearching;

    /**
     * Constructor.
     * Received the searching strategy to look for positions to be attacked.
     * @param positionSearching PositionSearching
     */
    public Shoot(PositionSearching positionSearching) {
        this.positionSearching = positionSearching;
    }

    /**
     * Specific method that execute the concrete strategy.
     * Strategy Pattern.
     */
    public void searchPosition() {
        this.position = this.positionSearching.searchPositionToAttack();
    }

    /**
     * Get position
     * @return Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Get state
     * @return Integer state
     */
    public Integer getState() {
        return state;
    }

    /**
     * Set state
     * @param state Integer with state.
     */
    public void setState(Integer state) {
        this.state = state;
    }
}
