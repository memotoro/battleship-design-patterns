/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.ships;

import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author memotoro
 */
public abstract class Ship {

    private int size;
    private int state;
    private List<Position> positionsOccupied;
    private List<Position> positionsAttacked;

    public Ship(int size) {
        this.size = size;
        this.state = Constants.SHIP_STATE_OK;
        this.positionsOccupied = new ArrayList<Position>();
        this.positionsAttacked = new ArrayList<Position>();
    }

    public boolean validatePositionAttacked(Position positionAttacked) {
        if (this.positionsOccupied.contains(positionAttacked)) {
            this.positionsOccupied.remove(positionAttacked);
            this.positionsAttacked.add(positionAttacked);
            if (this.positionsOccupied.isEmpty()
                    && this.positionsAttacked.size() == this.size) {
                this.state = Constants.SHIP_STATE_DETROYED;
            } else {
                this.state = Constants.SHIP_STATE_HITTED;
            }
            return true;
        } else {
            return false;
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Position> getPositionsOccupied() {
        return positionsOccupied;
    }

    public void setPositionsOccupied(List<Position> positionsOccupied) {
        this.positionsOccupied = positionsOccupied;
    }

    public List<Position> getPositionsAttacked() {
        return positionsAttacked;
    }

    public void setPositionsAttacked(List<Position> positionsAttacked) {
        this.positionsAttacked = positionsAttacked;
    }
}
