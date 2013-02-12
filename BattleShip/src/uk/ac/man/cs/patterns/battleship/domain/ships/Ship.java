package uk.ac.man.cs.patterns.battleship.domain.ships;

import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import java.util.ArrayList;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Subject;

/**
 * Ship. This class represent a ship as a general concept. Concrete ships are related with Ship with inheritance relationship.
 * @author Guillermo Antonio Toro Bayona
 */
public abstract class Ship extends Subject {

    /**
     * Integer that represent the size
     */
    private Integer size;
    /**
     * String name
     */
    private String name;
    /*
     * Integer that represent the state
     */
    private Integer state;
    /**
     * List of Position that the ship is occupying in the board and attacked positions.
     */
    private List<Position> positionsOccupied;
    private List<Position> positionsAttacked;

    /**
     * Constructor. Receive a specific size.
     * @param size
     */
    public Ship(Integer size, String name) {
        this.size = size;
        this.name = name;
        // Set the initial state for the ship as OK.
        this.state = Constants.SHIP_STATE_OK;
        this.positionsOccupied = new ArrayList<Position>();
        this.positionsAttacked = new ArrayList<Position>();
    }

    /**
     * Method that validate if one position attacked is occupied by the ship.
     * @param positionAttacked Position to be attacked
     * @return boolean with the validation
     */
    public boolean validatePositionAttacked(Position positionAttacked) {
        // Validate if the position attacked is part of the positions occupied.
        if (this.positionsOccupied.contains(positionAttacked)) {
            // Remove the position from the occupied and copy to the list of attacked positions.
            this.positionsOccupied.remove(positionAttacked);
            this.positionsAttacked.add(positionAttacked);
            // Validate if the ships was destroyed.
            if (this.positionsOccupied.isEmpty()
                    && this.positionsAttacked.size() == this.size) {
                this.state = Constants.SHIP_STATE_DETROYED;
            } else {
                this.state = Constants.SHIP_STATE_ATTACKED;
            }
            // Notify observer
            this.notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get the State of the ship.
     * @return
     */
    public Integer getState() {
        return state;
    }

    /**
     * Get the size of the ship.
     * @return
     */
    public Integer getSize() {
        return size;
    }

    /**
     * String name of the ship
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get positions occupied
     * @return List of positions
     */
    public List<Position> getPositionsAttacked() {
        return positionsAttacked;
    }

    /**
     * Set the positions occupied by one ship.
     * @param positionsOccupied
     */
    public void setPositionsOccupied(List<Position> positionsOccupied) {
        this.positionsOccupied = positionsOccupied;
    }
}
