package uk.ac.man.cs.patterns.battleship.domain.ships;

import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * Simple Factory. Class that represents a Factory for Ships
 * @author Guillermo Antonio Toro Bayona
 */
public class ShipFactory {

    /**
     * Simple Factory. Method to create ships based on name
     * @param shipName String name
     * @return Ship
     */
    public static Ship createShip(String shipName) {
        Ship ship = null;
        // Validate types
        if (shipName.equals(Constants.SHIP_NAME_AIRCRAFT)) {
            ship = new AirCraft(Constants.SHIP_NAME_AIRCRAFT);
        } else if (shipName.equals(Constants.SHIP_NAME_SUBMARINE)) {
            ship = new Submarine(Constants.SHIP_NAME_SUBMARINE);
        } else if (shipName.equals(Constants.SHIP_NAME_DESTROYER)) {
            ship = new Destroyer(Constants.SHIP_NAME_DESTROYER);
        } else if (shipName.equals(Constants.SHIP_NAME_CRUISER)) {
            ship = new Cruiser(Constants.SHIP_NAME_CRUISER);
        } else if (shipName.equals(Constants.SHIP_NAME_BOAT)) {
            ship = new Boat(Constants.SHIP_NAME_BOAT);
        }
        return ship;
    }
}
