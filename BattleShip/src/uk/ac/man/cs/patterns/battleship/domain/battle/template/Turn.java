/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle.template;

import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.domain.battle.Shoot;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 * Abstract class that represent a generic Turn.
 * This class set specific steps in one player's turn.
 * Some specific steps must be implement by specific players.
 * Template Pattern.
 * @author Guillermo Antonio Toro Bayona
 */
public abstract class Turn {

    /**
     * Shoot. Every turn implies a shoot.
     */
    private Shoot shoot;
    /**
     * Reference to the players who is attacking.
     */
    private Player playerAttacking;
    /**
     * Reference to the players who is being attacked.
     */
    private Player playerAttacked;

    /**
     * Method to set the players with specific roles in the turn.
     * @param playerAttacking
     * @param playerAttacked
     */
    public void setPlayers(Player playerAttacking, Player playerAttacked) {
        this.playerAttacking = playerAttacking;
        this.playerAttacked = playerAttacked;
    }

    /**
     * Template method.
     * This method is part of the Template Pattern.
     * In this case, this method set the specific steps that one turn must have.
     * Template Pattern.
     * @param positionReceived Position received to be attacked
     * @throws BattleShipException
     */
    public void playTurn(Position positionReceived) throws BattleShipException {
        // Create a shoot.
        this.shoot = this.createShoot(positionReceived);
        // Validation for the previous created shoot.
        this.validateShootCorrectnes(this.shoot);
        // Execute shoot
        this.executeShoot();
        // Save the turn in the historic list of turns.
        this.saveTurn();
    }

    /**
     * Abstract method of the Template.
     * This method must be implemented by every type of player.
     * The Human-Player and the PC-Player executed this method in different ways.
     * Template Pattern.
     * @param positionReceived  Position received to be attacked
     * @return Shoot created.
     */
    protected abstract Shoot createShoot(Position positionReceived);

    /**
     * Concrete step in the Turn.
     * This method validate if the Shoot received is valid in the board of the attacked player.
     * @param shoot Shoot to validate
     * @throws BattleShipException
     */
    private void validateShootCorrectnes(Shoot shoot) throws BattleShipException {
        // Validate the shoot in the board
        if (!(this.getPlayerAttacked().getBoard().validateShootPosition(shoot.getPosition()))) {
            // Raise an exception
            throw new BattleShipException(Constants.CODE_002);
        }
    }

    /**
     * Concrete step in the Turn.
     * This method execute the shoot.
     */
    private void executeShoot() {
        // Validate the shoot state with the specific value.
        if (this.playerAttacked.getBoard().executeAndValidateShoot(this.shoot.getPosition())) {
            shoot.setState(Constants.SHOOT_STATE_SUCCESSFUL);
        } else {
            shoot.setState(Constants.SHOOT_STATE_MISSED);
        }
    }

    /**
     * Concrete step in the turn.
     * Once the turn was played and the shoot was updated,
     * the turn is saved in the list of the previous turns.
     */
    private void saveTurn() {
        this.playerAttacking.getPreviousTurns().add(this);
    }

    /**
     * Get Shoot
     * @return Shoot
     */
    public Shoot getShoot() {
        return shoot;
    }

    /**
     * Get player attacked
     * @return Player
     */
    public Player getPlayerAttacked() {
        return playerAttacked;
    }

    /**
     * Get player attacking
     * @return Player
     */
    public Player getPlayerAttacking() {
        return playerAttacking;
    }
}
