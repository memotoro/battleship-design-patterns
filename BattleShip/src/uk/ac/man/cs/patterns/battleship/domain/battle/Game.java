/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.domain.battle.template.Turn;
import uk.ac.man.cs.patterns.battleship.domain.battle.template.HumanTurn;
import uk.ac.man.cs.patterns.battleship.domain.battle.template.PcTurn;
import java.util.ArrayList;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;

/**
 * Game. This class represent a session game.
 * This class control specific steps before one turn was executed.
 * @author Guillermo Antonio Toro Bayona
 */
public class Game {

    /**
     * Integer with the state of the game
     */
    private Integer state;
    /**
     * List of the players
     */
    private List<Player> players;
    /**
     * Player that is attacking.
     */
    private Player playerAttacking;
    /**
     * Player that is being attacked.
     */
    private Player playerAttacked;

    /**
     * Constructor.
     * Initialise a game instance.
     */
    public Game() {
        this.players = new ArrayList<Player>();
        // Create a game.
        this.createGame();
    }

    /**
     * Method that create a game.
     * Set the players
     */
    private void createGame() {
        // Set the initial state
        this.state = Constants.GAME_STATE_PLAYING;
        // Set the players.
        this.players.add(new Player(Constants.GAME_PLAYER_NAME_HUMAN, Constants.GAME_PLAYER_TYPE_HUMAN));
        this.players.add(new Player(Constants.GAME_PLAYER_NAME_PC, Constants.GAME_PLAYER_TYPE_PC));
        // Set the first turn to the human player.
        this.playerAttacking = this.players.get(0);
        this.playerAttacked = this.players.get(1);
    }

    /**
     * Attack is the main method of the game.
     * Set specific steps before to play. Make some validation before and after each Turn.
     * @param playerReceived Player that executed the attack.
     * @param positionReceived Position to be attacked
     * @throws BattleShipException
     */
    public void attack(Player playerReceived, Position positionReceived) throws BattleShipException {
        // Validate the game status
        this.validateGameStatus();
        // Validate if the player is in turn.
        this.validatePlayerInTurn(playerReceived);
        // Create an attack
        this.playAttack(playerReceived, positionReceived);
        // Validate the board of the player attacked
        this.validateBoardPlayerAttacked();
        // Interchange the players
        this.swapPlayers();
    }

    /**
     * Method that validate the game status.
     * @throws BattleShipException
     */
    private void validateGameStatus() throws BattleShipException {
        // If the game is finished
        if (this.state == Constants.GAME_STATE_FINISHED) {
            // Raise an exception.
            throw new BattleShipException(Constants.CODE_005);
        }
    }

    /**
     * Method that validate the turn for each player.
     * @param playerReceived Player that invoke the attack method.
     * @throws BattleShipException
     */
    private void validatePlayerInTurn(Player playerReceived) throws BattleShipException {
        // Validate the player attacking
        if (playerReceived == this.playerAttacked) {
            // Raise an exception.
            throw new BattleShipException(Constants.CODE_003);
        }
    }

    /**
     * Method that create an attack for each player.
     * Based on each player, a specific Turn is created.
     * @param playerReceived Player attacking
     * @param positionReceived Position to be attacked
     * @throws BattleShipException
     */
    private void playAttack(Player playerReceived, Position positionReceived) throws BattleShipException {
        // Turn
        Turn turn = null;
        // If the player is the Human-Player
        if (playerReceived.getType().equals(Constants.GAME_PLAYER_TYPE_HUMAN)) {
            // Create specific Human Turn.
            turn = new HumanTurn();
        } // If the player is the Pc-Player
        else if (playerReceived.getType().equals(Constants.GAME_PLAYER_TYPE_PC)) {
            // Create specific Pc turn
            turn = new PcTurn();
        }
        // Set the specific roles
        turn.setPlayers(this.playerAttacking, this.playerAttacked);
        // Play the turn for the player.
        turn.playTurn(positionReceived);
    }

    /**
     * Method to validate the board of the attacked player
     */
    private void validateBoardPlayerAttacked() {
        // If the player attacked don't have any ship available.
        if (this.playerAttacked.getBoard().getShipsAvailable() == 0) {
            // Set the game Finished.
            this.state = Constants.GAME_STATE_FINISHED;
        }
    }

    /**
     * Interchange the players.
     */
    private void swapPlayers() {
        // If the player attacking was the Human-Player
        if (this.playerAttacking == this.players.get(0)) {
            // Swap the player
            this.playerAttacking = this.players.get(1);
            this.playerAttacked = this.players.get(0);
        } // If the player attacking was the Pc-Player
        else {
            // Swap the player
            this.playerAttacking = this.players.get(0);
            this.playerAttacked = this.players.get(1);
        }
    }

    /**
     * Get the player attacked
     * @return Player
     */
    public Player getPlayerAttacked() {
        return playerAttacked;
    }

    /**
     * Get the player attacking
     * @return
     */
    public Player getPlayerAttacking() {
        return playerAttacking;
    }

    /**
     * Get the game status
     * @return Integer
     */
    public Integer getState() {
        return state;
    }
}
