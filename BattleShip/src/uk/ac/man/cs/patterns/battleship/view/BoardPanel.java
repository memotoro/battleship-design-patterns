/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BoardPanel.java
 *
 * Created on 01-Feb-2013, 09:21:59
 */
package uk.ac.man.cs.patterns.battleship.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.domain.battle.Position;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;

/**
 *
 * @author Guillermo Antonio Toro Bayona
 */
public class BoardPanel extends javax.swing.JPanel {

    private GameListener gameListener;
    private Player player;

    /** Creates new form BoardPanel */
    public BoardPanel(GameListener gameListener, Player player) {
        initComponents();
        this.gameListener = gameListener;
        this.player = player;
        initializeBoardPanel();
    }

    private void initializeBoardPanel() {
        this.jLabelPlayerName.setText(this.player.getName());
        this.jLabelShipsAvailable.setText(this.player.getBoard().getShipsAvailable().toString());
        this.jLabelNotificationMessage.setText(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_008));
        this.jPanelGridPositions.setLayout(new GridLayout(Constants.BOARD_SIZE_WIDTH, Constants.BOARD_SIZE_HEIGHT));
        // labels
//        this.jLabelAirCraft.setText(Constants.SHIP_NAME_AIRCRAFT + Constants.GAME_TEXT_OPEN_BRAKET + Constants.SHIP_SIZE_AIRCRAFT + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_011) + Constants.GAME_TEXT_CLOSE_BRAKET);
        this.jLabelAirCraft.setForeground(Color.green);
//        this.jLabelSubmarine.setText(Constants.SHIP_NAME_SUBMARINE + Constants.GAME_TEXT_OPEN_BRAKET + Constants.SHIP_SIZE_AIRCRAFT + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_011) + Constants.GAME_TEXT_CLOSE_BRAKET);
        this.jLabelSubmarine.setForeground(Color.green);
//        this.jLabelDestroyer1.setText(Constants.SHIP_NAME_DESTROYER_1 + Constants.GAME_TEXT_OPEN_BRAKET + Constants.SHIP_SIZE_DESTROYER + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_011) + Constants.GAME_TEXT_CLOSE_BRAKET);
        this.jLabelDestroyer1.setForeground(Color.green);
//        this.jLabelDestroyer2.setText(Constants.SHIP_NAME_DESTROYER_2 + Constants.GAME_TEXT_OPEN_BRAKET + Constants.SHIP_SIZE_DESTROYER + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_011) + Constants.GAME_TEXT_CLOSE_BRAKET);
        this.jLabelDestroyer2.setForeground(Color.green);
//        this.jLabelCruiser1.setText(Constants.SHIP_NAME_CRUISER_1 + Constants.GAME_TEXT_OPEN_BRAKET + Constants.SHIP_SIZE_CRUISER + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_011) + Constants.GAME_TEXT_CLOSE_BRAKET);
        this.jLabelCruiser1.setForeground(Color.green);
//        this.jLabelCruiser2.setText(Constants.SHIP_NAME_CRUISER_2 + Constants.GAME_TEXT_OPEN_BRAKET + Constants.SHIP_SIZE_CRUISER + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_011) + Constants.GAME_TEXT_CLOSE_BRAKET);
        this.jLabelCruiser2.setForeground(Color.green);
//        this.jLabelBoat1.setText(Constants.SHIP_NAME_BOAT_1 + Constants.GAME_TEXT_OPEN_BRAKET + Constants.SHIP_SIZE_BOAT + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_011) + Constants.GAME_TEXT_CLOSE_BRAKET);
        this.jLabelBoat1.setForeground(Color.green);
//        this.jLabelBoat2.setText(Constants.SHIP_NAME_BOAT_2 + Constants.GAME_TEXT_OPEN_BRAKET + Constants.SHIP_SIZE_BOAT + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_011) + Constants.GAME_TEXT_CLOSE_BRAKET);
        this.jLabelBoat2.setForeground(Color.green);
        if (this.player.getType().contains(Constants.GAME_PLAYER_TYPE_PC)) {
            for (int y = 0; y < Constants.BOARD_SIZE_HEIGHT; y++) {
                for (int x = 0; x < Constants.BOARD_SIZE_WIDTH; x++) {
                    JButton jButtonInGrid = new JButton();
                    jButtonInGrid.setPreferredSize(new Dimension(10, 10));
                    ImageIcon imageIcon = new ImageIcon(Constants.GAME_PATH_IMAGE_SEA);
                    jButtonInGrid.setIcon(imageIcon);
                    jButtonInGrid.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + x + Constants.GAME_TEXT_SEPARATOR + y);
                    jButtonInGrid.addActionListener(this.gameListener);
                    this.jPanelGridPositions.add(jButtonInGrid);
                }
            }
        } else if (this.player.getType().contains(Constants.GAME_PLAYER_TYPE_HUMAN)) {
            for (int y = 0; y < Constants.BOARD_SIZE_HEIGHT; y++) {
                for (int x = 0; x < Constants.BOARD_SIZE_WIDTH; x++) {
                    Position position = new Position(x, y);
                    JButton jButtonInGrid = new JButton();
                    ImageIcon imageIcon = new ImageIcon(Constants.GAME_PATH_IMAGE_SEA);
                    if (this.player.getBoard().getPositionsOccupied().contains(position)) {
                        imageIcon = new ImageIcon(Constants.GAME_PATH_IMAGE_SHIP);
                    }
                    jButtonInGrid.setIcon(imageIcon);
                    jButtonInGrid.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + x + Constants.GAME_TEXT_SEPARATOR + y);
                    this.jPanelGridPositions.add(jButtonInGrid);
                }
            }
        }
    }

    public JButton getJButtonByName(String playerName, Integer coordinateX, Integer coordinateY) {
        JButton jButtonSpecificName = null;
        for (Component guiComponent : this.jPanelGridPositions.getComponents()) {
            if (guiComponent instanceof JButton) {
                jButtonSpecificName = (JButton) guiComponent;
                if (jButtonSpecificName.getName().equals(playerName + Constants.GAME_TEXT_SEPARATOR + coordinateX + Constants.GAME_TEXT_SEPARATOR + coordinateY)) {
                    break;
                }
            }
        }
        return jButtonSpecificName;
    }

    public void setNotificationMessage(String message) {
        this.jLabelNotificationMessage.setText(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_008) + message);
    }

    public Player getPlayer() {
        return player;
    }

    public JLabel getjLabelShipsAvailable() {
        return jLabelShipsAvailable;
    }

    public JLabel getjLabelAirCraft() {
        return jLabelAirCraft;
    }

    public JLabel getjLabelBoat1() {
        return jLabelBoat1;
    }

    public JLabel getjLabelBoat2() {
        return jLabelBoat2;
    }

    public JLabel getjLabelCruiser1() {
        return jLabelCruiser1;
    }

    public JLabel getjLabelCruiser2() {
        return jLabelCruiser2;
    }

    public JLabel getjLabelDestroyer1() {
        return jLabelDestroyer1;
    }

    public JLabel getjLabelDestroyer2() {
        return jLabelDestroyer2;
    }

    public JLabel getjLabelSubmarine() {
        return jLabelSubmarine;
    }

    /**
     * This method is called from within the constructor to
     * initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelInfo = new javax.swing.JPanel();
        jLabelPlayerNameLabel = new javax.swing.JLabel();
        jLabelPlayerName = new javax.swing.JLabel();
        jLabelShipsAvailableLabel = new javax.swing.JLabel();
        jLabelShipsAvailable = new javax.swing.JLabel();
        jPanelGridPositions = new javax.swing.JPanel();
        jPanelMessage = new javax.swing.JPanel();
        jLabelNotificationMessage = new javax.swing.JLabel();
        jPanelShipsInfo = new javax.swing.JPanel();
        jLabelAirCraft = new javax.swing.JLabel();
        jLabelDestroyer1 = new javax.swing.JLabel();
        jLabelCruiser1 = new javax.swing.JLabel();
        jLabelBoat1 = new javax.swing.JLabel();
        jLabelBoat2 = new javax.swing.JLabel();
        jLabelCruiser2 = new javax.swing.JLabel();
        jLabelDestroyer2 = new javax.swing.JLabel();
        jLabelSubmarine = new javax.swing.JLabel();

        jPanelInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelPlayerNameLabel.setText("Player Name");

        jLabelPlayerName.setText("Player Name");

        jLabelShipsAvailableLabel.setText("Ships Available");

        jLabelShipsAvailable.setText("Ships Available");

        javax.swing.GroupLayout jPanelInfoLayout = new javax.swing.GroupLayout(jPanelInfo);
        jPanelInfo.setLayout(jPanelInfoLayout);
        jPanelInfoLayout.setHorizontalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPlayerNameLabel)
                    .addComponent(jLabelShipsAvailableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPlayerName)
                    .addComponent(jLabelShipsAvailable))
                .addGap(147, 147, 147))
        );
        jPanelInfoLayout.setVerticalGroup(
            jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPlayerNameLabel)
                    .addComponent(jLabelPlayerName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelShipsAvailableLabel)
                    .addComponent(jLabelShipsAvailable))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelGridPositions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelGridPositionsLayout = new javax.swing.GroupLayout(jPanelGridPositions);
        jPanelGridPositions.setLayout(jPanelGridPositionsLayout);
        jPanelGridPositionsLayout.setHorizontalGroup(
            jPanelGridPositionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );
        jPanelGridPositionsLayout.setVerticalGroup(
            jPanelGridPositionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );

        jPanelMessage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelNotificationMessage.setText("jLabel1");

        javax.swing.GroupLayout jPanelMessageLayout = new javax.swing.GroupLayout(jPanelMessage);
        jPanelMessage.setLayout(jPanelMessageLayout);
        jPanelMessageLayout.setHorizontalGroup(
            jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNotificationMessage)
                .addContainerGap(319, Short.MAX_VALUE))
        );
        jPanelMessageLayout.setVerticalGroup(
            jPanelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNotificationMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelShipsInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelAirCraft.setText("Air Craft");

        jLabelDestroyer1.setText("Destroyer 1");

        jLabelCruiser1.setText("Cruiser 1");

        jLabelBoat1.setText("Boat 1");

        jLabelBoat2.setText("Boat 2");

        jLabelCruiser2.setText("Cruiser 2");

        jLabelDestroyer2.setText("Destroyer 2");

        jLabelSubmarine.setText("Submarine");

        javax.swing.GroupLayout jPanelShipsInfoLayout = new javax.swing.GroupLayout(jPanelShipsInfo);
        jPanelShipsInfo.setLayout(jPanelShipsInfoLayout);
        jPanelShipsInfoLayout.setHorizontalGroup(
            jPanelShipsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelShipsInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelShipsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCruiser1)
                    .addComponent(jLabelAirCraft))
                .addGap(18, 18, 18)
                .addGroup(jPanelShipsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSubmarine, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCruiser2))
                .addGap(18, 18, 18)
                .addGroup(jPanelShipsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBoat1)
                    .addComponent(jLabelDestroyer1))
                .addGap(18, 18, 18)
                .addGroup(jPanelShipsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDestroyer2)
                    .addComponent(jLabelBoat2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelShipsInfoLayout.setVerticalGroup(
            jPanelShipsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelShipsInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelShipsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAirCraft)
                    .addComponent(jLabelSubmarine)
                    .addComponent(jLabelDestroyer1)
                    .addComponent(jLabelDestroyer2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelShipsInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCruiser1)
                    .addComponent(jLabelBoat1)
                    .addComponent(jLabelBoat2)
                    .addComponent(jLabelCruiser2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelShipsInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelGridPositions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMessage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelInfo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelShipsInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelGridPositions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanelMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAirCraft;
    private javax.swing.JLabel jLabelBoat1;
    private javax.swing.JLabel jLabelBoat2;
    private javax.swing.JLabel jLabelCruiser1;
    private javax.swing.JLabel jLabelCruiser2;
    private javax.swing.JLabel jLabelDestroyer1;
    private javax.swing.JLabel jLabelDestroyer2;
    private javax.swing.JLabel jLabelNotificationMessage;
    private javax.swing.JLabel jLabelPlayerName;
    private javax.swing.JLabel jLabelPlayerNameLabel;
    private javax.swing.JLabel jLabelShipsAvailable;
    private javax.swing.JLabel jLabelShipsAvailableLabel;
    private javax.swing.JLabel jLabelSubmarine;
    private javax.swing.JPanel jPanelGridPositions;
    private javax.swing.JPanel jPanelInfo;
    private javax.swing.JPanel jPanelMessage;
    private javax.swing.JPanel jPanelShipsInfo;
    // End of variables declaration//GEN-END:variables
}
