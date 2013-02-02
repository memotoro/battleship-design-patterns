/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.utils.Constants;
import java.util.Date;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;

/**
 *
 * @author memotoro
 */
public class Game {

    private int state;
    private Player[] players;
    private Player playerAttacking;
    private Player playerAttacked;

    public Game() {
        System.out.println("Creating Game...");
        this.state = Constants.GAME_STATE_STARTING;
        this.players = new Player[2];
    }

    public void processState() {
        if (this.state == Constants.GAME_STATE_STARTING) {
            this.players[0] = new Player(Constants.GAME_PLAYER_MAN);
            this.players[1] = new Player(Constants.GAME_PLAYER_PC);
            this.state = Constants.GAME_STATE_PLAYING;
            this.playerAttacking = this.players[0];
            this.playerAttacked = this.players[1];
        } else if (this.state == Constants.GAME_STATE_PLAYING) {
//            attack();
            validateShipsAvailable();
            swapPlayers();
        } else if (this.state == Constants.GAME_STATE_FINISHED) {
            System.out.println("Game Over: ");
            System.out.println("Ships available PC: " + this.players[0].getBoard().getShipsAvailable());
            System.out.println("Ships available HUMAN: " + this.players[1].getBoard().getShipsAvailable());
            if (this.players[0].getBoard().getShipsAvailable() > this.players[1].getBoard().getShipsAvailable()) {
                System.out.println("PC Win !!!: ");
            } else {
                System.out.println("HUMAN Win !!!: ");
            }
            System.exit(1);
        }
    }

    private boolean validatePlayerInTurn(Player playerReceived) {
        if (playerReceived == this.playerAttacking) {
            return true;
        } else {
            return false;
        }
    }

    public Position createAttack(Player playerReceived) throws BattleShipException {
        Position position = null;
        if (this.state == Constants.GAME_STATE_PLAYING) {
            if (validatePlayerInTurn(playerReceived)) {
                Turn turn = new Turn(new Date(), this.playerAttacking);
                boolean validShoot = false;
                while (!validShoot) {
                    Shoot shoot = turn.createShoot(this.playerAttacked.getBoard().availablePosition());
                    if (this.playerAttacked.getBoard().validateShootPosition(shoot.getPosition())) {
                        validShoot = true;
                        position = shoot.getPosition();
                        if (this.playerAttacked.getBoard().validateShootSuccessful(shoot.getPosition())) {
                            shoot.setState(Constants.SHOOT_STATE_SUCCESSFUL);
                        } else {
                            shoot.setState(Constants.SHOOT_STATE_MISSED);
                        }
                    }
                }
                this.playerAttacking.getPreviousTurns().add(turn);
                this.validateShipsAvailable();
                this.swapPlayers();
            } else {
                throw new BattleShipException(Constants.CODE_001);
            }
        } else {
            throw new BattleShipException(Constants.CODE_005);
        }
        return position;
    }

    public void createAttack(Player playerReceived, Position positionReceived) throws BattleShipException {
        if (this.state == Constants.GAME_STATE_PLAYING) {
            if (validatePlayerInTurn(playerReceived)) {
                Turn turn = new Turn(new Date(), this.playerAttacking);
                Shoot shoot = turn.createShoot(positionReceived);
                if (this.playerAttacked.getBoard().validateShootPosition(shoot.getPosition())) {
                    if (this.playerAttacked.getBoard().validateShootSuccessful(shoot.getPosition())) {
                        shoot.setState(Constants.SHOOT_STATE_SUCCESSFUL);
                    } else {
                        shoot.setState(Constants.SHOOT_STATE_MISSED);
                    }
                    this.playerAttacking.getPreviousTurns().add(turn);
                    this.validateShipsAvailable();
                    this.swapPlayers();
                } else {
                    throw new BattleShipException(Constants.CODE_002);
                }
            } else {
                throw new BattleShipException(Constants.CODE_001);
            }
        } else {
            throw new BattleShipException(Constants.CODE_005);
        }
    }

    private void swapPlayers() {
        if (this.playerAttacking == this.players[0]) {
            this.playerAttacking = this.players[1];
            this.playerAttacked = this.players[0];
        } else {
            this.playerAttacking = this.players[0];
            this.playerAttacked = this.players[1];
        }
    }

    private void validateShipsAvailable() {
        if (this.playerAttacked.getBoard().getShipsAvailable() == 0) {
            this.state = Constants.GAME_STATE_FINISHED;
        }
    }

    public Player getPlayerAttacked() {
        return playerAttacked;
    }

    public void setPlayerAttacked(Player playerAttacked) {
        this.playerAttacked = playerAttacked;
    }

    public Player getPlayerAttacking() {
        return playerAttacking;
    }

    public void setPlayerAttacking(Player playerAttacking) {
        this.playerAttacking = playerAttacking;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
