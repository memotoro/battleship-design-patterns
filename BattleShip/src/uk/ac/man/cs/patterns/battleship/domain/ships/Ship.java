package uk.ac.man.cs.patterns.battleship.domain.ships;

import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import java.util.ArrayList;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Observer;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Subject;

/**
 * Ship. This class represent a ship as a general concept. Concrete ships are related with Ship with inheritance relationship.
 * @author Guillermo Antonio Toro Bayona
 */
public abstract class Ship implements Subject {

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
     * List of Position that the ship is occupying in the board.
     */
    private List<Position> positionsOccupied;
    /**
     * List of Position from the ships that have been attacked.
     */
    private List<Position> positionsAttacked;
    /**
     * List of observers
     */
    private List<Observer> observers;

    /**
     * Constructor. Receive a specific size.
     * @param size
     */
    public Ship(Integer size, String name) {
        this.size = size;
        this.name = name;
        // Set the initial state for the ship as OK.
        this.state = Constants.SHIP_STATE_OK;
        // Initialize the arrays of positions.
        this.positionsOccupied = new ArrayList<Position>();
        this.positionsAttacked = new ArrayList<Position>();
        this.observers = new ArrayList<Observer>();
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
                // Set the state Destroyed
                this.state = Constants.SHIP_STATE_DETROYED;
            } else {
                // Set the state attacked
                this.state = Constants.SHIP_STATE_ATTACKED;
            }
            // Notify observer
            this.notifyObservers();
            // Return validation
            return true;
        } else {
            // Return validation
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

    /**
     * Observer Pattern. Method to register observer.
     * @param observer Observer
     */
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Observer Pattern. Method to remove observer.
     * @param observer Observer
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Observer Pattern. Method to notify observer of changes
     */
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.updateObserver(this);
        }
    }
}
