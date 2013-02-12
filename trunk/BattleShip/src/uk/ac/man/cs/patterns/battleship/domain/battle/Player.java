package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.domain.battle.template.Turn;
import java.util.ArrayList;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * Class that represent a player in the game.
 * @author Guillermo Antonio Toro Bayona
 */
public class Player {

    /**
     * String with the name of the player.
     */
    private String name;
    /**
     * String with the type of the player.
     */
    private String type;
    /**
     * List of the previous turns of the player.
     */
    private List<Turn> previousTurns;
    /**
     * Board of the player.
     */
    private Board board;

    /**
     * Constructor.Received the name and the type as parameters.
     * @param name String name.
     * @param type String type.
     */
    public Player(String name, String type) {
        // Initialise the variables
        this.name = name;
        this.type = type;
        this.previousTurns = new ArrayList<Turn>();
        // Create the board of the player.
        this.board = new Board();
    }

    /**
     * Method that look for the last successful turn in the previous turns of the player.
     * @return Turn
     */
    public Turn getLastSuccessfulTurn() {
        // Size of the list
        int sizePreviousTurns = this.previousTurns.size();
        Turn turn = null;
        // Control step back
        int controlStepsBack = 0;
        for (int index = sizePreviousTurns - 1; index >= 0; index--) {
            controlStepsBack++;
            turn = this.previousTurns.get(index);
            // If the turn is successful break the loop.
            if (turn.getShoot().getState() == Constants.SHOOT_STATE_SUCCESSFUL) {
                break;
            } else {
                // Validate if the control is equal to the maximum steps back and break the loop.
                if (controlStepsBack == Constants.SHOOT_CONTROL_SUCCESSFUL) {
                    break;
                }
            }
        }
        return turn;
    }

    /**
     * Method that return the last turn in the list.
     * @return
     */
    public Turn getLastTurn() {
        // Validate size and return
        if (this.previousTurns.size() > 0) {
            return this.previousTurns.get(this.previousTurns.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Get board of the player.
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the name of the player
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of previous turns of the player
     * @return List of Turn
     */
    public List<Turn> getPreviousTurns() {
        return previousTurns;
    }

    /**
     * Get the type of the player
     * @return
     */
    public String getType() {
        return type;
    }
}
