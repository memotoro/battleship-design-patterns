package uk.ac.man.cs.patterns.battleship.domain.battle.template;

import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * Simple Factory. Class that represents a Factory for Turns
 * @author Guillermo Antonio Toro Bayona
 */
public class TurnFactory {

    /**
     * Simple Factory. Method to create turns based on the player type
     * @param playerReceived Player
     * @return Turn created
     */
    public static Turn createTurn(Player playerReceived) {
        // If the player is the Human-Player
        if (playerReceived.getType().equals(Constants.GAME_PLAYER_TYPE_HUMAN)) {
            // Create specific Human Turn.
            return new HumanTurn();
        } // If the player is the Pc-Player
        else {
            // Create specific Pc turn
            return new PcTurn();
        }
    }
}
