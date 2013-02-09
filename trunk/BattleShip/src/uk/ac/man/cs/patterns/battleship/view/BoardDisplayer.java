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
 * @author Guillermo Antonio Toro Bayona
 */
public class BoardDisplayer {

    private BoardPanel boardPlayer1Panel;
    private BoardPanel boardPlayer2Panel;
    private BattleShipMainFrame battleShipMainFrame;
    private GameListener gameListener;

    public BoardDisplayer(BattleShipMainFrame battleShipMainFrame, GameListener gameListener) {
        this.battleShipMainFrame = battleShipMainFrame;
        this.gameListener = gameListener;
    }

    public void drawInitialBoard(Game game) {
        this.battleShipMainFrame.getjPanelGame().removeAll();
        this.battleShipMainFrame.getjPanelGame().setLayout(new GridLayout(1, 0));

        this.boardPlayer1Panel = new BoardPanel(this.gameListener, game.getPlayerAttacking());
        this.battleShipMainFrame.getjPanelGame().add(this.boardPlayer1Panel);
        this.boardPlayer2Panel = new BoardPanel(this.gameListener, game.getPlayerAttacked());
        this.battleShipMainFrame.getjPanelGame().add(this.boardPlayer2Panel);

        this.battleShipMainFrame.pack();
        this.battleShipMainFrame.repaint();
    }

    public void updateImageIconInButton(Player player, Integer coordinateX, Integer coordinateY, Integer shootState) {
        JButton jButton = null;
        if (player.getType().equals(Constants.GAME_PLAYER_TYPE_HUMAN)) {
            jButton = this.boardPlayer1Panel.getJButtonByName(player.getName(), coordinateX, coordinateY);
        } else {
            jButton = this.boardPlayer2Panel.getJButtonByName(player.getName(), coordinateX, coordinateY);
        }
        if (shootState == Constants.SHOOT_STATE_MISSED) {
            jButton.setIcon(new ImageIcon(Constants.GAME_PATH_IMAGE_MISSED));
        } else {
            jButton.setIcon(new ImageIcon(Constants.GAME_PATH_IMAGE_HITTED));
        }
    }

    public void updateNotificationMessage(Player player, String message) {
        if (player.getType().equals(Constants.GAME_PLAYER_TYPE_HUMAN)) {
            this.boardPlayer1Panel.setNotificationMessage(message);
        } else {
            this.boardPlayer2Panel.setNotificationMessage(message);
        }
    }

    public void updateShipsAvailable(Player player, Integer shipAvailable) {
        if (player.getType().equals(Constants.GAME_PLAYER_TYPE_HUMAN)) {
            this.boardPlayer1Panel.getjLabelShipsAvailable().setText(shipAvailable.toString());
        } else {
            this.boardPlayer2Panel.getjLabelShipsAvailable().setText(shipAvailable.toString());
        }
    }

    public Player getOpponentPlayerByName(String playerName) {
        if (playerName.equals(this.boardPlayer1Panel.getPlayer().getName())) {
            return this.boardPlayer1Panel.getPlayer();
        } else {
            return this.boardPlayer2Panel.getPlayer();
        }
    }

    public Player getActivePlayerByOpponentName(String playerName) {
        if (playerName.equals(this.boardPlayer1Panel.getPlayer().getName())) {
            return this.boardPlayer2Panel.getPlayer();
        } else {
            return this.boardPlayer1Panel.getPlayer();
        }
    }

    public Integer displayPopUpConfirmationMessage(String message) {
        MessageDialog messageDialog = new MessageDialog(this.battleShipMainFrame, true, true, message);
        messageDialog.setVisible(true);
        return messageDialog.getReturnStatus();
    }

    public void displayPopUpMessage(String message) {
        new MessageDialog(this.battleShipMainFrame, true, false, message).setVisible(true);
    }
}
