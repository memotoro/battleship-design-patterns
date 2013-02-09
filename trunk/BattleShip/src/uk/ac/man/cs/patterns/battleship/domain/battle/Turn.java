/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 *
 * @author memotoro
 */
public abstract class Turn {

    private Shoot shoot;
    private Player playerAttacking;
    private Player playerAttacked;

    public void setPlayers(Player playerAttacking, Player playerAttacked) {
        this.playerAttacking = playerAttacking;
        this.playerAttacked = playerAttacked;
    }

    public void playTurn(Position positionReceived) throws BattleShipException {
        this.shoot = this.createShoot(positionReceived);
        this.validateShootCorrectnes(this.shoot);
        this.validateShootStatus();
        this.saveTurn();
    }

    protected abstract Shoot createShoot(Position positionReceived);

    private void validateShootCorrectnes(Shoot shoot) throws BattleShipException {
        if (!(this.getPlayerAttacked().getBoard().validateShootPosition(shoot.getPosition()))) {
            throw new BattleShipException(Constants.CODE_002);
        }
    }

    private void validateShootStatus() {
        if (this.playerAttacked.getBoard().validateShootSuccessful(this.shoot.getPosition())) {
            shoot.setState(Constants.SHOOT_STATE_SUCCESSFUL);
        } else {
            shoot.setState(Constants.SHOOT_STATE_MISSED);
        }
    }

    private void saveTurn() {
        this.playerAttacking.getPreviousTurns().add(this);
    }

    public Shoot getShoot() {
        return shoot;
    }

    public Player getPlayerAttacked() {
        return playerAttacked;
    }

    public Player getPlayerAttacking() {
        return playerAttacking;
    }
}
