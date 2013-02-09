/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import java.util.ArrayList;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;

/**
 *
 * @author memotoro
 */
public class Game {

    private Integer state;
    private List<Player> players;
    private Player playerAttacking;
    private Player playerAttacked;

    public Game() {
        this.state = Constants.GAME_STATE_PLAYING;
        this.players = new ArrayList<Player>();
        this.createGame();
    }

    private void createGame() {
        this.state = Constants.GAME_STATE_PLAYING;
        this.players.add(new Player(Constants.GAME_PLAYER_NAME_HUMAN, Constants.GAME_PLAYER_TYPE_HUMAN));
        this.players.add(new Player(Constants.GAME_PLAYER_NAME_PC, Constants.GAME_PLAYER_TYPE_PC));
        this.playerAttacking = this.players.get(0);
        this.playerAttacked = this.players.get(1);
    }

    public void attack(Player playerReceived, Position positionReceived) throws BattleShipException {
        this.validateGameStatus();
        this.validatePlayerInTurn(playerReceived);
        this.playAttack(playerReceived, positionReceived);
        this.validateBoardOpponent();
        this.swapPlayers();
    }

    private void validateGameStatus() throws BattleShipException {
        if (this.state == Constants.GAME_STATE_FINISHED) {
            throw new BattleShipException(Constants.CODE_005);
        }
    }

    private void validatePlayerInTurn(Player playerReceived) throws BattleShipException {
        if (playerReceived == this.playerAttacked) {
            throw new BattleShipException(Constants.CODE_003);
        }
    }

    private void playAttack(Player playerReceived, Position positionReceived) throws BattleShipException {
        Turn turn = null;
        if (playerReceived.getType().equals(Constants.GAME_PLAYER_TYPE_HUMAN)) {
            turn = new HumanTurn();
        } else if (playerReceived.getType().equals(Constants.GAME_PLAYER_TYPE_PC)) {
            turn = new PcTurn();
        }
        turn.setPlayers(this.playerAttacking, this.playerAttacked);
        turn.playTurn(positionReceived);
    }

    private void validateBoardOpponent() {
        if (this.playerAttacked.getBoard().getShipsAvailable() == 0) {
            this.state = Constants.GAME_STATE_FINISHED;
        }
    }

    private void swapPlayers() {
        if (this.playerAttacking == this.players.get(0)) {
            this.playerAttacking = this.players.get(1);
            this.playerAttacked = this.players.get(0);
        } else {
            this.playerAttacking = this.players.get(0);
            this.playerAttacked = this.players.get(1);
        }
    }

    public Player getPlayerAttacked() {
        return playerAttacked;
    }

    public Player getPlayerAttacking() {
        return playerAttacking;
    }

    public Integer getState() {
        return state;
    }
}
