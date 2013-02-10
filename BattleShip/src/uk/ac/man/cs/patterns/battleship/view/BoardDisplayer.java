/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import uk.ac.man.cs.patterns.battleship.domain.battle.Game;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Observer;
import uk.ac.man.cs.patterns.battleship.domain.battle.observer.Subject;
import uk.ac.man.cs.patterns.battleship.domain.ships.Ship;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 *
 * @author Guillermo Antonio Toro Bayona
 */
public class BoardDisplayer implements Observer {

    private BoardPanel boardPanel;
    private BattleShipMainFrame battleShipMainFrame;
    private GameListener gameListener;

    public BoardDisplayer(BattleShipMainFrame battleShipMainFrame, GameListener gameListener) {
        this.battleShipMainFrame = battleShipMainFrame;
        this.gameListener = gameListener;
    }

    public void drawInitialBoard(Game game, Player player) {
        // Register observer
        this.registerObserver(player);
        this.boardPanel = new BoardPanel(this.gameListener, player);
        this.battleShipMainFrame.getjPanelGame().add(this.boardPanel);
    }

    public void updateImageIconInButton(Player player, Integer coordinateX, Integer coordinateY, Integer shootState) {
        JButton jButton = null;
        jButton = this.boardPanel.getJButtonByName(player.getName(), coordinateX, coordinateY);

        if (shootState == Constants.SHOOT_STATE_MISSED) {
            jButton.setIcon(new ImageIcon(Constants.GAME_PATH_IMAGE_MISSED));
        } else {
            jButton.setIcon(new ImageIcon(Constants.GAME_PATH_IMAGE_HITTED));
        }
    }

    public void updateNotificationMessage(String message) {
        this.boardPanel.setNotificationMessage(message);
    }

    public void updateShipsAvailable(Integer shipAvailable) {
        this.boardPanel.getjLabelShipsAvailable().setText(shipAvailable.toString());
    }

    public Player getPlayer() {
        return this.boardPanel.getPlayer();
    }

    public Integer displayPopUpConfirmationMessage(String message) {
        MessageDialog messageDialog = new MessageDialog(this.battleShipMainFrame, true, true, message);
        messageDialog.setVisible(true);
        return messageDialog.getReturnStatus();
    }

    public void displayPopUpMessage(String message) {
        new MessageDialog(this.battleShipMainFrame, true, false, message).setVisible(true);
    }

    private void registerObserver(Player player) {
        for (Ship ship : player.getBoard().getShips()) {
            ship.registerObserver(this);
        }
    }

    public void updateObserver(Subject subject) {
        // Validate observer ship
        if (subject instanceof Ship) {
            // Casting to ship
            Ship ship = (Ship) subject;

            if (ship.getName().equals(Constants.SHIP_NAME_AIRCRAFT)) {
                this.updateColorLabel(this.boardPanel.getjLabelAirCraft(), ship.getState());
            } else if (ship.getName().equals(Constants.SHIP_NAME_SUBMARINE)) {
                this.updateColorLabel(this.boardPanel.getjLabelSubmarine(), ship.getState());
            } else if (ship.getName().equals(Constants.SHIP_NAME_DESTROYER_1)) {
                this.updateColorLabel(this.boardPanel.getjLabelDestroyer1(), ship.getState());
            } else if (ship.getName().equals(Constants.SHIP_NAME_DESTROYER_2)) {
                this.updateColorLabel(this.boardPanel.getjLabelDestroyer2(), ship.getState());
            } else if (ship.getName().equals(Constants.SHIP_NAME_CRUISER_1)) {
                this.updateColorLabel(this.boardPanel.getjLabelCruiser1(), ship.getState());
            } else if (ship.getName().equals(Constants.SHIP_NAME_CRUISER_2)) {
                this.updateColorLabel(this.boardPanel.getjLabelCruiser2(), ship.getState());
            } else if (ship.getName().equals(Constants.SHIP_NAME_BOAT_1)) {
                this.updateColorLabel(this.boardPanel.getjLabelBoat1(), ship.getState());
            } else if (ship.getName().equals(Constants.SHIP_NAME_BOAT_2)) {
                this.updateColorLabel(this.boardPanel.getjLabelBoat2(), ship.getState());
            }
        }
    }

    private void updateColorLabel(JLabel labelToUpdate, Integer shipState) {
        if (shipState == Constants.SHIP_STATE_OK) {
            labelToUpdate.setForeground(Color.green);
        } else if (shipState == Constants.SHIP_STATE_ATTACKED) {
            labelToUpdate.setForeground(Color.orange);
        } else if (shipState == Constants.SHIP_STATE_DETROYED) {
            labelToUpdate.setForeground(Color.red);
        }
    }
}
