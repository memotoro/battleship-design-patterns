package uk.ac.man.cs.patterns.battleship.domain.ships;

import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * AirCraft.
 * @author Guillermo Antonio Toro Bayona
 */
public class AirCraft extends Ship {

    /**
     * Constructor. Call the super with specific size for the ship.
     */
    public AirCraft(String name) {
        super(Constants.SHIP_SIZE_AIRCRAFT, name);
    }
}
