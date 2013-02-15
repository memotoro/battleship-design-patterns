package uk.ac.man.cs.patterns.battleship.view;

import uk.ac.man.cs.patterns.battleship.view.listeners.GameListener;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import uk.ac.man.cs.patterns.battleship.domain.battle.Board;
import uk.ac.man.cs.patterns.battleship.domain.battle.Game;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Observer;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Subject;
import uk.ac.man.cs.patterns.battleship.domain.ships.Ship;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;

/**
 * Class that is in charge of update and draw elements in a specific BoardPanel.
 * Decouple the Panel and the View from elements of the Model.
 * @author Guillermo Antonio Toro Bayona
 */
public class BoardDisplayer implements Observer {

    /**
     * BoardPanel to update and draw.
     */
    private BoardPanel boardPanel;
    /**
     * Reference to a player owner of the board
     */
    private Player player;
    /**
     * BattleShipMainFrame frame of the game
     */
    private BattleShipMainFrame battleShipMainFrame;
    /**
     * GameListener to catch events
     */
    private GameListener gameListener;

    /**
     * Constructor.Receive a reference of the frame and the game listener
     * @param battleShipMainFrame BattleShipMainFrame frame
     * @param gameListener GameListener
     */
    public BoardDisplayer(BattleShipMainFrame battleShipMainFrame, GameListener gameListener) {
        this.battleShipMainFrame = battleShipMainFrame;
        this.gameListener = gameListener;
    }

    /**
     * Method to prepare the board and draw elements
     * @param game Game created
     * @param player Player
     */
    public void drawInitialBoard(Game game, Player player) {
        // Register observer
        this.player = player;
        this.registerObserverInShips(player);
        this.boardPanel = new BoardPanel(player.getName());
        this.battleShipMainFrame.getjPanelGame().add(this.boardPanel);
        this.initializeBoardPanel();
    }

    /**
     * Method to update the image icon in one specific coordinate
     * @param player Player to localise a specific button in its board
     * @param coordinateX Integer coordinate x
     * @param coordinateY Integer coordinate y
     * @param shootState Integer shoot state
     */
    public void updateImageIconInButton(Player player, Integer coordinateX, Integer coordinateY, Integer shootState) {
        JButton jButtonToUpdate = null;
        // Get the button based on the name of the player and coordinates
        jButtonToUpdate = this.boardPanel.getJButtonByName(player.getName(), coordinateX, coordinateY);
        // Validate the state of the shoot and update with specific image
        if (shootState == Constants.SHOOT_STATE_MISSED) {
            jButtonToUpdate.setIcon(new ImageIcon(Constants.GAME_PATH_IMAGE_MISSED));
        } else {
            jButtonToUpdate.setIcon(new ImageIcon(Constants.GAME_PATH_IMAGE_ATTACKED));
        }
    }

    /**
     * Method to update the message in the notification area
     * @param message String with message.
     */
    public void updateNotificationMessage(String message) {
        this.boardPanel.setNotificationMessage(message);
    }

    /**
     * Get player
     * @return Player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Method to display a pop up confirmation message dialog
     * @param message String with message
     * @return Integer with the state of the confirmation
     */
    public Integer displayPopUpConfirmationMessage(String message) {
        // Create message dialog
        MessageDialog messageDialog = new MessageDialog(this.battleShipMainFrame, true, true, message);
        // Return status
        return messageDialog.getReturnStatus();
    }

    /**
     * Method to display a pop up message
     * @param message String with  message
     */
    public void displayPopUpMessage(String message) {
        // Create message dialog
        new MessageDialog(this.battleShipMainFrame, true, false, message);
    }

    /**
     * Method that initialise a board
     */
    private void initializeBoardPanel() {
        // Set the values based on the information of the game, player
        this.boardPanel.getjLabelPlayerName().setText(this.player.getName());
        this.boardPanel.getjLabelShipsAvailable().setText(this.player.getBoard().getShipsAvailable().toString());
        this.boardPanel.setNotificationMessage(Constants.GAME_TEXT_SEPARATOR);
        this.boardPanel.getjPanelGridPositions().setLayout(new GridLayout(Constants.BOARD_SIZE_HEIGHT, Constants.BOARD_SIZE_WIDTH));
        // Create the button with specific coordinates and player name
        for (int y = 0; y < Constants.BOARD_SIZE_HEIGHT; y++) {
            for (int x = 0; x < Constants.BOARD_SIZE_WIDTH; x++) {
                // Create a position with coordinates
                Position position = new Position(x, y);
                // Create a button
                JButton jButtonInGrid = new JButton();
                // Create a defaul image icon
                ImageIcon imageIcon = new ImageIcon(Constants.GAME_PATH_IMAGE_SEA);
                // Validate if the player is the human player and if the position is occupied.
                if (this.player.getBoard().validatePositionsOccupied(position)
                        && this.player.getType().contains(Constants.GAME_PLAYER_TYPE_HUMAN)) {
                    // Set new image icon with the ship position
                    imageIcon = new ImageIcon(Constants.GAME_PATH_IMAGE_SHIP);
                }
                // Set the icon to the button
                jButtonInGrid.setIcon(imageIcon);
                // Set the name of the button for the player
                jButtonInGrid.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + x + Constants.GAME_TEXT_SEPARATOR + y);
                // Validate if the player is pc
                if (this.player.getType().contains(Constants.GAME_PLAYER_TYPE_PC)) {
                    // Set the action listener to the buttons of the pc player
                    jButtonInGrid.addActionListener(this.gameListener);
                }
                this.boardPanel.getjPanelGridPositions().add(jButtonInGrid);
            }
        }
        // Take the ship for the player
        for (Ship ship : this.player.getBoard().getShips()) {
            this.update(ship);
        }
    }

    /**
     * Method to register the observer
     * @param player Player
     */
    private void registerObserverInShips(Player player) {
        // Take the ship for the player
        for (Ship ship : player.getBoard().getShips()) {
            // Register
            ship.registerObserver(this);
        }
        player.getBoard().registerObserver(this);
    }

    /**
     * Method that update the Colour of the label based the state of the ship
     * @param ship Ship received from the Observer
     */
    private void updateColorLabel(Ship ship) {
        JLabel labelToUpdate = null;
        // Get the jlabel with the name of the player and the ship
        labelToUpdate = this.boardPanel.getJLabelByName(this.player.getName(), ship.getName());
        // Validate states
        if (ship.getState() == Constants.SHIP_STATE_OK) {
            labelToUpdate.setForeground(Color.green);
        } else if (ship.getState() == Constants.SHIP_STATE_ATTACKED) {
            labelToUpdate.setForeground(Color.orange);
        } else if (ship.getState() == Constants.SHIP_STATE_DETROYED) {
            labelToUpdate.setForeground(Color.red);
        }
    }

    /**
     * Method to update the observer information.
     * Observer Pattern.
     * @param subject
     */
    public void update(Subject subject) {
        // Validate observer ship
        if (subject instanceof Ship) {
            // Update color of the label
            this.updateColorLabel((Ship) subject);
        } // Validate observer boad
        else if (subject instanceof Board) {
            // Update label
            this.boardPanel.getjLabelShipsAvailable().setText(((Board) subject).getShipsAvailable().toString());
            // If the player attacked don't have any ship available.
            if (((Board) subject).getShipsAvailable() == 0) {
                // Create a popup message
                this.displayPopUpMessage(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_005) + " " + this.player.getName() + " " + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_006));
            }
        }
    }
}
