package uk.ac.man.cs.patterns.battleship.domain.ships;

import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * Boat.
 * @author Guillermo Antonio Toro Bayona
 */
public class Boat extends Ship {

    /**
     * Constructor. Call the super with specific size for the ship.
     */
    public Boat(String name) {
        super(Constants.SHIP_SIZE_BOAT, name);
    }
}
