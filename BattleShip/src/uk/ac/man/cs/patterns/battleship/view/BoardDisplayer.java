/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.view;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import uk.ac.man.cs.patterns.battleship.domain.battle.Game;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.utils.Constants;

/**
 *
 * @author memotoro
 */
public class BoardDisplayer {

    private BoardPanel boardPlayerPanel;
    private BoardPanel boardPcPanel;
    private BattleShipMainFrame battleShipMainFrame;
    private GameListener gameListener;

    public BoardDisplayer(BattleShipMainFrame battleShipMainFrame, GameListener gameListener) {
        this.battleShipMainFrame = battleShipMainFrame;
        this.gameListener = gameListener;
    }

    public void drawInitialBoard(Game game) {
        this.battleShipMainFrame.getjPanelGame().removeAll();
        this.battleShipMainFrame.getjPanelGame().setLayout(new GridLayout(1, 0));

        this.boardPlayerPanel = new BoardPanel(this.gameListener, game.getPlayerAttacking());
        this.battleShipMainFrame.getjPanelGame().add(this.boardPlayerPanel);
        this.boardPcPanel = new BoardPanel(this.gameListener, game.getPlayerAttacked());
        this.battleShipMainFrame.getjPanelGame().add(this.boardPcPanel);

        this.battleShipMainFrame.pack();
        this.battleShipMainFrame.repaint();
    }

    public void updateImageIconInButton(Player player, Integer coordinateX, Integer coordinateY, int shootState) {
        JButton jButton = null;
        if (player.getName().equals(Constants.GAME_PLAYER_MAN)) {
            jButton = this.boardPlayerPanel.getJButtonByName(player.getName(), coordinateX, coordinateY);
        } else {
            jButton = this.boardPcPanel.getJButtonByName(player.getName(), coordinateX, coordinateY);
        }
        if (shootState == Constants.SHOOT_STATE_MISSED) {
            jButton.setIcon(new ImageIcon(Constants.GAME_PATH_IMAGE_MISSED));
        } else {
            jButton.setIcon(new ImageIcon(Constants.GAME_PATH_IMAGE_HITTED));
        }
    }

    public void updateNotificationMessage(Player player, String message) {
        if (player.getName().equals(Constants.GAME_PLAYER_MAN)) {
            this.boardPlayerPanel.setNotificationMessage(message);
        } else {
            this.boardPcPanel.setNotificationMessage(message);
        }
    }

    public void updateShipsAvailable(Player player, int shipAvailable) {
        if (player.getName().equals(Constants.GAME_PLAYER_MAN)) {
            this.boardPlayerPanel.getjLabelShipsAvailable().setText(String.valueOf(shipAvailable));
        } else {
            this.boardPcPanel.getjLabelShipsAvailable().setText(String.valueOf(shipAvailable));
        }
    }

    public Player getOpponentPlayerByName(String playerName) {
        if (playerName.equals(Constants.GAME_PLAYER_MAN)) {
            return this.boardPlayerPanel.getPlayer();
        } else {
            return this.boardPcPanel.getPlayer();
        }
    }

    public Player getActivePlayerByOpponentName(String playerName) {
        if (playerName.equals(Constants.GAME_PLAYER_MAN)) {
            return this.boardPcPanel.getPlayer();
        } else {
            return this.boardPlayerPanel.getPlayer();
        }
    }

    public int displayPopUpConfirmationMessage(String message){
        MessageDialog messageDialog = new MessageDialog(this.battleShipMainFrame, true, true, message);
        messageDialog.setVisible(true);
        return messageDialog.getReturnStatus();
    }

    public void displayPopUpMessage(String message){
        new MessageDialog(this.battleShipMainFrame, true, false, message).setVisible(true);
    }
}
