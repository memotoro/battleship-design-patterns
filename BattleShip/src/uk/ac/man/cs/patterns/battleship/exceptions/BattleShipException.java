/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.exceptions;

import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;

/**
 *
 * @author memotoro
 */
public class BattleShipException extends Exception {

    private String description;

    public BattleShipException(String exceptionCode) {
        this.description = PropertiesUtil.getInstance().getMessageByCode(exceptionCode);
    }

    public String getDescription() {
        return description;
    }
}
