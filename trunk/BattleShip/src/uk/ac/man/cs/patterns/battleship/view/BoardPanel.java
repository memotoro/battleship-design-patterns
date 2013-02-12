package uk.ac.man.cs.patterns.battleship.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import uk.ac.man.cs.patterns.battleship.domain.battle.Player;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;

/**
 * Class that represent a Board in the GUI.
 * @author Guillermo Antonio Toro Bayona
 */
public class BoardPanel extends JPanel {

    /**
     * Reference to a player owner of the board
     */
    private Player player;
    /**
     * JLabel aircraft
     */
    private JLabel jLabelAirCraft;
    /**
     * JLabel boat
     */
    private JLabel jLabelBoat1;
    private JLabel jLabelBoat2;
    /**
     * JLabel destroyer
     */
    private JLabel jLabelDestroyer1;
    private JLabel jLabelDestroyer2;
    /**
     * JLabel notification
     */
    private JLabel jLabelNotificationMessage;
    /**
     * JLabel player name
     */
    private JLabel jLabelPlayerName;
    /**
     *  JLabel name label
     */
    private JLabel jLabelPlayerNameLabel;
    /**
     * JLabel ships available
     */
    private JLabel jLabelShipsAvailable;
    private JLabel jLabelShipsAvailableLabel;
    /**
     * JLabel submarine
     */
    private JLabel jLabelSubmarine;
    /**
     * JPanel grid positions
     */
    private JPanel jPanelGridPositions;
    /**
     * JPanel info
     */
    private JPanel jPanelInfo;
    /**
     * JPanel message
     */
    private JPanel jPanelMessage;
    /**
     * JPanel ships info
     */
    private JPanel jPanelShipsInfo;

    /**
     * Constructor.
     * @param gameListener GameListener reference
     * @param player Reference to player
     */
    public BoardPanel(Player player) {
        this.player = player;
        this.initializeBoardPanel();
    }

    private void initializeBoardPanel() {
        // Set layout
        this.setLayout(new GridBagLayout());
        // Create panel info
        this.jPanelInfo = new JPanel();
        this.jPanelInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.jPanelInfo.setLayout(new GridLayout(0, 2));
        // Set properties to the labels in the panel info
        this.jLabelPlayerNameLabel = new JLabel();
        this.jLabelPlayerNameLabel.setText(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_013));
        this.jLabelPlayerName = new JLabel();
        this.jLabelShipsAvailableLabel = new JLabel();
        this.jLabelShipsAvailableLabel.setText(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_014));
        this.jLabelShipsAvailable = new JLabel();
        // Add labels to the panel info
        this.jPanelInfo.add(this.jLabelPlayerNameLabel);
        this.jPanelInfo.add(this.jLabelPlayerName);
        this.jPanelInfo.add(this.jLabelShipsAvailableLabel);
        this.jPanelInfo.add(this.jLabelShipsAvailable);
        // Create panel for ships info
        this.jPanelShipsInfo = new JPanel();
        this.jPanelShipsInfo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.jPanelShipsInfo.setLayout(new GridLayout(0, 4));
        this.jLabelAirCraft = new JLabel(Constants.SHIP_NAME_AIRCRAFT);
        this.jLabelAirCraft.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + Constants.SHIP_NAME_AIRCRAFT);
        this.jLabelSubmarine = new JLabel(Constants.SHIP_NAME_SUBMARINE);
        this.jLabelSubmarine.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + Constants.SHIP_NAME_SUBMARINE);
        this.jLabelBoat1 = new JLabel(Constants.SHIP_NAME_BOAT_1);
        this.jLabelBoat1.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + Constants.SHIP_NAME_BOAT_1);
        this.jLabelBoat2 = new JLabel(Constants.SHIP_NAME_BOAT_2);
        this.jLabelBoat2.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + Constants.SHIP_NAME_BOAT_2);
        this.jLabelDestroyer1 = new JLabel(Constants.SHIP_NAME_DESTROYER_1);
        this.jLabelDestroyer1.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + Constants.SHIP_NAME_DESTROYER_1);
        this.jLabelDestroyer2 = new JLabel(Constants.SHIP_NAME_DESTROYER_2);
        this.jLabelDestroyer2.setName(this.player.getName() + Constants.GAME_TEXT_SEPARATOR + Constants.SHIP_NAME_DESTROYER_2);
        // Add labels to the ships info panel
        this.jPanelShipsInfo.add(this.jLabelAirCraft);
        this.jPanelShipsInfo.add(this.jLabelSubmarine);
        this.jPanelShipsInfo.add(this.jLabelBoat1);
        this.jPanelShipsInfo.add(this.jLabelBoat2);
        this.jPanelShipsInfo.add(this.jLabelDestroyer1);
        this.jPanelShipsInfo.add(this.jLabelDestroyer2);
        // Create the panel grid
        this.jPanelGridPositions = new JPanel();
        this.jPanelGridPositions.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // Create the panel for message
        this.jPanelMessage = new JPanel();
        // Set the properties to the labels in the panel message
        this.jPanelMessage.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.jPanelMessage.setLayout(new GridLayout(0, 1));
        this.jLabelNotificationMessage = new JLabel();
        // Add labels        
        this.jPanelMessage.add(this.jLabelNotificationMessage);
        // Create constraints
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 30;
        this.add(this.jPanelInfo, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(this.jPanelShipsInfo, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(this.jPanelGridPositions, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.add(this.jPanelMessage, gridBagConstraints);
    }

    /**
     * Method that retrieve one button based on the name
     * @param playerName Player name
     * @param coordinateX Integer coordinate x
     * @param coordinateY Integer coordinate y
     * @return JButton
     */
    public JButton getJButtonByName(String playerName, Integer coordinateX, Integer coordinateY) {
        // Reference to the button
        JButton jButtonSpecificName = null;
        // Iterate for all the GUI componentes in the grid
        for (Component guiComponent : this.jPanelGridPositions.getComponents()) {
            // Validate if is a JButton
            if (guiComponent instanceof JButton) {
                // Take the object reference
                jButtonSpecificName = (JButton) guiComponent;
                // Validate the name
                if (jButtonSpecificName.getName().equals(playerName + Constants.GAME_TEXT_SEPARATOR + coordinateX + Constants.GAME_TEXT_SEPARATOR + coordinateY)) {
                    break;
                }
            }
        }
        // Return the object
        return jButtonSpecificName;
    }

    /**
     * Method that retrieve a label based on the name
     * @param playerName Player name
     * @param shipName String name of the label related with the ship
     * @return JLabel
     */
    public JLabel getJLabelByName(String playerName, String shipName) {
        // Referenc e to the JLabel
        JLabel jLabelSpecificName = null;
        // Iterate for all the GUI componentes in the grid
        for (Component guiComponent : this.jPanelShipsInfo.getComponents()) {
            // Validate if is a JLabel
            if (guiComponent instanceof JLabel) {
                // Take the object reference
                jLabelSpecificName = (JLabel) guiComponent;
                // Validate the name
                if (jLabelSpecificName.getName().equals(playerName + Constants.GAME_TEXT_SEPARATOR + shipName)) {
                    break;
                }
            }
        }
        // Return the object
        return jLabelSpecificName;
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

    public JLabel getjLabelPlayerName() {
        return jLabelPlayerName;
    }

    public JPanel getjPanelGridPositions() {
        return jPanelGridPositions;
    }
}
