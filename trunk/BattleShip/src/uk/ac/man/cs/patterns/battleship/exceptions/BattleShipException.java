package uk.ac.man.cs.patterns.battleship.exceptions;

import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;

/**
 * Class that represent a controlled exception for specific events in the game
 * @author Guillermo Antonio Toro Bayona
 */
public class BattleShipException extends Exception {

    /**
     * String with the description of the exception.
     */
    private String description;

    /**
     * Constructor. Call super and set description.
     * @param exceptionCode
     */
    public BattleShipException(String exceptionCode) {
        super();
        this.description = PropertiesUtil.getInstance().getMessageByCode(exceptionCode);
    }

    /**
     * Get the description of the exception.
     * @return String description
     */
    public String getDescription() {
        return description;
    }
}
