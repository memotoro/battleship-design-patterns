package uk.ac.man.cs.patterns.battleship.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import uk.ac.man.cs.patterns.battleship.controller.BattleShipController;
import uk.ac.man.cs.patterns.battleship.controller.IBattleShipController;
import uk.ac.man.cs.patterns.battleship.domain.battle.Game;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Shoot;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;
import uk.ac.man.cs.patterns.battleship.view.BattleShipMainFrame;
import uk.ac.man.cs.patterns.battleship.view.BoardDisplayer;

/**
 * Class that implement action listener to catch every action in the panels and frames
 * @author Guillermo Antonio Toro Bayona
 */
public class GameListener implements ActionListener {

    /**
     * Game of battle ship
     */
    private Game game;
    /**
     * Interface IBattleShipController controller
     */
    private IBattleShipController battleShipController;
    /**
     * BattleShipMainFrame with reference to the main frame.
     */
    private BattleShipMainFrame battleShipMainFrame;
    /**
     * BoardDisplayer for the human player
     */
    private BoardDisplayer boardPlayerDisplayer;
    /**
     * BoardDisplayer for computer player
     */
    private BoardDisplayer boardComputerDisplayer;

    /**
     * Constructor.
     * Take the frame and save  the reference.
     * @param battleShipMainFrame
     */
    public GameListener(BattleShipMainFrame battleShipMainFrame) {
        // Set the reference
        this.battleShipController = new BattleShipController();
        this.battleShipMainFrame = battleShipMainFrame;
        // Create one board displayer for human player
        this.boardPlayerDisplayer = new BoardDisplayer(battleShipMainFrame, this);
        // Create one board displayer for computer player
        this.boardComputerDisplayer = new BoardDisplayer(this.battleShipMainFrame, this);
    }

    /**
     * Method that process the action events
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        // Validate if the source is a button
        if (e.getSource() instanceof JButton) {
            // Take the button
            JButton jButtonExecuted = (JButton) e.getSource();
            // Take the name and split with one specific separator
            String[] infoButton = jButtonExecuted.getName().split(Constants.GAME_TEXT_SEPARATOR);
            // Validate name of button
            if (infoButton[0].equals(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_012))) {
                // If the game is not null
                if (this.game != null) {
                    // Validate the response of a pop up confirmation message
                    if (this.boardPlayerDisplayer.displayPopUpConfirmationMessage(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_007)) == 1) {
                        // Draw game
                        this.drawGame();
                    }
                } else {
                    // Draw game
                    this.drawGame();
                }
                this.battleShipMainFrame.refresh();
            } // If a button of the board
            else {
                // Take the coordinates of the button
                Integer coordinateX = Integer.valueOf(infoButton[1]);
                Integer coordinateY = Integer.valueOf(infoButton[2]);
                try {
                    // Call the controller and take the shoot back
                    Shoot shoot = this.battleShipController.attack(this.boardPlayerDisplayer.getPlayer(), coordinateX, coordinateY);
                    // Process the action in GUI
                    this.processAction(this.boardPlayerDisplayer.getPlayer(), this.boardComputerDisplayer.getPlayer(), shoot);
                    // Take the action from the computer player
                    this.actionComputerEvent();
                } catch (BattleShipException ex) {
                    // Update the message
                    this.boardComputerDisplayer.updateNotificationMessage(ex.getDescription());
                }
            }
        }
    }

    /**
     * Method that draw a game.
     */
    private void drawGame() {
        // Create a new game
        this.game = battleShipController.startNewGame();
        // Prepare the frame
        this.battleShipMainFrame.prepare();
        // Draw the board for player
        this.boardPlayerDisplayer.drawInitialBoard(this.game, this.game.getPlayerAttacking());
        // Draw the board for computer
        this.boardComputerDisplayer.drawInitialBoard(this.game, this.game.getPlayerAttacked());
    }

    /**
     * Method that simulate the turn of the Computer
     */
    private void actionComputerEvent() {
        try {
            // Call the controller with the computer player and take the shoot.
            Shoot shoot = this.battleShipController.attack(this.boardComputerDisplayer.getPlayer(), null, null);
            // Process the action in GUI
            this.processAction(this.boardComputerDisplayer.getPlayer(), this.boardPlayerDisplayer.getPlayer(), shoot);
        } catch (BattleShipException ex) {
            // Update the message
            this.boardPlayerDisplayer.updateNotificationMessage(ex.getDescription());
        }
    }

    /**
     * Method that process the action in GUI
     * @param activePlayer
     * @param opponentPlayer
     * @param position
     */
    private void processAction(Player activePlayer, Player opponentPlayer, Shoot shoot) {
        // Validate player as human player
        if (activePlayer.getType().equals(Constants.GAME_PLAYER_TYPE_HUMAN)) {
            // Update image icon of the button
            this.boardComputerDisplayer.updateImageIconInButton(opponentPlayer, shoot.getPosition().getCoordinateX(), shoot.getPosition().getCoordinateY(), shoot.getState());
            this.boardComputerDisplayer.updateNotificationMessage(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_004));
        } // Validate player as Computer player
        else if (activePlayer.getType().equals(Constants.GAME_PLAYER_TYPE_COMPUTER)) {
            // Update image icon of the button
            this.boardPlayerDisplayer.updateImageIconInButton(opponentPlayer, shoot.getPosition().getCoordinateX(), shoot.getPosition().getCoordinateY(), shoot.getState());
            this.boardPlayerDisplayer.updateNotificationMessage(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_003));
        }
    }
}
