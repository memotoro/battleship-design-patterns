package uk.ac.man.cs.patterns.battleship.domain.battle;

import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Subject;
import uk.ac.man.cs.patterns.battleship.domain.battle.template.Turn;
import java.util.ArrayList;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import java.util.List;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Observer;
import uk.ac.man.cs.patterns.battleship.domain.battle.template.TurnFactory;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;

/**
 * This class represent a session game.This class control specific steps before one turn was executed.
 * @author Guillermo Antonio Toro Bayona
 */
public class Game implements Observer {

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
     * Constructor.Initialise a game instance.
     */
    public Game() {
        this.players = new ArrayList<Player>();
        // Set the initial state
        this.state = Constants.GAME_STATE_PLAYING;
        // Set the players.
        this.players.add(new Player(Constants.GAME_PLAYER_NAME_HUMAN, Constants.GAME_PLAYER_TYPE_HUMAN));
        this.players.add(new Player(Constants.GAME_PLAYER_NAME_COMPUTER, Constants.GAME_PLAYER_TYPE_COMPUTER));
        // Set the first turn to the human player.
        this.playerAttacking = this.players.get(0);
        this.playerAttacked = this.players.get(1);
        // Register observer
        this.playerAttacking.getBoard().registerObserver(this);
        this.playerAttacked.getBoard().registerObserver(this);
    }

    /**
     * Attack is the main method of the game.Set specific steps before to play. Make some validation before and after each Turn.
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
            throw new BattleShipException(Constants.CODE_003);
        }
    }

    /**
     * Method that create an attack for each player. Based on each player, a specific Turn is created.
     * @param playerReceived Player attacking
     * @param positionReceived Position to be attacked
     * @throws BattleShipException
     */
    private void playAttack(Player playerReceived, Position positionReceived) throws BattleShipException {
        Turn turn = TurnFactory.createTurn(playerReceived);
        // Set the specific roles
        turn.setPlayers(this.playerAttacking, this.playerAttacked);
        // Play the turn for the player.
        turn.playTurn(positionReceived);
    }

    /**
     * Interchange the players.
     */
    private void swapPlayers() {
        // If the player attacking was the HumanPlayer
        if (this.playerAttacking == this.players.get(0)) {
            this.playerAttacking = this.players.get(1);
            this.playerAttacked = this.players.get(0);
        } // If the player attacking was the ComputerPlayer
        else {
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
     * Observer Pattern. Update of the state of the game based on the ships available.
     * @param subject Subject
     */
    public void update(Subject subject) {
        // Validate observer boad
        if (subject instanceof Board) {
            // If the player attacked don't have any ship available.
            if (((Board) subject).getShipsAvailable() == 0) {
                this.state = Constants.GAME_STATE_FINISHED;
            }
        }
    }
}
