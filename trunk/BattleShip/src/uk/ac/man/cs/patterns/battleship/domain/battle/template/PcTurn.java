package uk.ac.man.cs.patterns.battleship.domain.battle.template;

import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.domain.battle.Shoot;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.NearbySearching;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.PositionSearching;
import uk.ac.man.cs.patterns.battleship.domain.battle.strategy.RandomSearching;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * Template Pattern.PC Turn. Concrete turn for human player. This class implement the abstract method from the abstract Turn.
 * @author Guillermo Antonio Toro Bayona
 */
public class PcTurn extends Turn {

    /**
     * Concrete implementation of the create shoot step.
     * @param positionReceived Position to be used as helper in the different algorithms.
     * @return Shoot created
     */
    @Override
    protected Shoot createShoot(Position positionReceived) {
        // Create a position searching
        PositionSearching positionSearching = null;
        Shoot shoot = null;
        // Validation for the selected shoot.
        boolean validationShoot = false;
        // Size of the previous turn list.
        int sizeTurns = getPlayerAttacking().getPreviousTurns().size();
        // While the validation shoot was invalid
        while (!validationShoot) {
            if (sizeTurns > 0) {
                // Take the last successfull turn from the player if any.
                Turn previousTurn = getPlayerAttacking().getLastSuccessfulTurn();
                // If the previous turn correspond to a successful shoot
                if (previousTurn.getShoot().getState() == Constants.SHOOT_STATE_SUCCESSFUL) {
                    // Set the new strategy to look for new possible position nearby.
                    positionSearching = new NearbySearching(previousTurn.getShoot().getPosition(), getPlayerAttacked());
                } // If there are no previous successful shoot.
                else if (previousTurn.getShoot() != null && previousTurn.getShoot().getState() == Constants.SHOOT_STATE_MISSED) {
                    // Set strategy as random strategy.
                    positionSearching = new RandomSearching(null, getPlayerAttacked());
                }
            } // If the are no previous turns
            else {
                // Set strategy as random strategy.
                positionSearching = new RandomSearching(null, getPlayerAttacked());
            }// Set the strategy.
            shoot = new Shoot(positionSearching);
            // Executed the strategy selected.
            shoot.searchPosition();
            // If the shoot take a position invalid
            if (shoot.getPosition() == null) {
                // Change the variable to force that in the next iteration take a random shoot.
                sizeTurns = 0;
            } else {
                // Change the validation to break the loop.
                validationShoot = true;
            }
        }
        return shoot;
    }
}
