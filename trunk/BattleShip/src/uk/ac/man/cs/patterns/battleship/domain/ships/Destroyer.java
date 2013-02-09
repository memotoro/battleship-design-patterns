/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.ships;

import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * Destroyer.
 * @author Guillermo Antonio Toro Bayona
 */
public class Destroyer extends Ship {

    /**
     * Constructor.
     * Call the super with specific size for the ship.
     */
    public Destroyer() {
        super(Constants.SHIP_SIZE_DESTROYER);
    }
}
