/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.man.cs.patterns.battleship.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import uk.ac.man.cs.patterns.battleship.controller.BattleShipController;
import uk.ac.man.cs.patterns.battleship.controller.IBattleShipController;
import uk.ac.man.cs.patterns.battleship.domain.battle.Game;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.exceptions.BattleShipException;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;

/**
 *
 * @author memotoro
 */
public class GameListener implements ActionListener {

    private Game game;
    private IBattleShipController battleShipController;
    private BoardDisplayer boardDisplayer;

    public GameListener(BattleShipMainFrame battleShipMainFrame) {
        this.battleShipController = new BattleShipController();
        this.boardDisplayer = new BoardDisplayer(battleShipMainFrame, this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e != null) {
            if (e.getSource() instanceof JButton) {
                JButton jButtonExecuted = (JButton) e.getSource();
                String[] infoButton = jButtonExecuted.getName().split(Constants.GAME_COORDS_SEPARATOR);
                String opponentPlayerName = infoButton[0];
                Integer coordinateX = Integer.valueOf(infoButton[1]);
                Integer coordinateY = Integer.valueOf(infoButton[2]);
                Player opponentPlayer = this.boardDisplayer.getOpponentPlayerByName(opponentPlayerName);
                Player activePlayer = this.boardDisplayer.getActivePlayerByOpponentName(opponentPlayerName);
                try {
                    Position position = this.battleShipController.attack(activePlayer, coordinateX, coordinateY);
                    this.processAction(activePlayer, opponentPlayer, position);
                    this.actionEvent(activePlayer.getName());
                } catch (BattleShipException ex) {
                    this.boardDisplayer.updateNotificationMessage(opponentPlayer, ex.getDescription());
                }
            } else if (e.getSource() instanceof JMenuItem) {
                if (this.game != null) {
                    if (this.boardDisplayer.displayPopUpConfirmationMessage(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_007)) == 1) {
                        this.game = battleShipController.startNewGame();
                        this.boardDisplayer.drawInitialBoard(this.game);
                    }
                } else {
                    this.game = battleShipController.startNewGame();
                    this.boardDisplayer.drawInitialBoard(this.game);
                }
            }
        }
    }

    private void actionEvent(String opponentPlayerName) {
        Player activePlayer = this.boardDisplayer.getActivePlayerByOpponentName(opponentPlayerName);
        Player opponentPlayer = this.boardDisplayer.getOpponentPlayerByName(opponentPlayerName);
        try {
            Position position = this.battleShipController.attack(activePlayer);
            this.processAction(activePlayer, opponentPlayer, position);
        } catch (BattleShipException ex) {
            this.boardDisplayer.updateNotificationMessage(opponentPlayer, ex.getDescription());
        }
    }

    private void processAction(Player activePlayer, Player opponentPlayer, Position position) {
        this.boardDisplayer.updateImageIconInButton(opponentPlayer, position.getCoordinateX(), position.getCoordinateY(), this.battleShipController.lastShootState(activePlayer));
        this.boardDisplayer.updateShipsAvailable(opponentPlayer, this.battleShipController.shipsAvailable(opponentPlayer));
        if (this.battleShipController.gameStatus() == Constants.GAME_STATE_PLAYING) {
            this.boardDisplayer.updateNotificationMessage(opponentPlayer, PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_003));
            this.boardDisplayer.updateNotificationMessage(activePlayer, PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_004));
        } else if (this.battleShipController.gameStatus() == Constants.GAME_STATE_FINISHED) {
            this.boardDisplayer.displayPopUpMessage(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_005) + " " + activePlayer.getName() + " " + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_006));
        }
    }
}
