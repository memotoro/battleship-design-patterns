package uk.ac.man.cs.patterns.battleship.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;
import uk.ac.man.cs.patterns.battleship.view.listeners.GameListener;

/**
 * Class that represent the main frame of the game
 * @author Guillermo Antonio Toro Bayona
 */
public class BattleShipMainFrame extends JFrame {

    /**
     * GameListener of the game
     */
    private GameListener gameListener;
    /**
     * JPanel for new button
     */
    private JPanel jPanelButton;
    /**
     * JPanel for game
     */
    private JPanel jPanelGame;

    /**
     * Constructor
     */
    public BattleShipMainFrame() {
        this.gameListener = new GameListener(this);
        // Display properties
        this.setSize(1200, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.createNewGameButtonPanel();
    }

    /**
     * Method to display the menu
     */
    private void createNewGameButtonPanel() {
        // Create the new panels
        this.jPanelButton = new JPanel();
        this.jPanelButton.setLayout(new GridLayout(0, 1));
        this.jPanelGame = new JPanel();
        // Button New Game
        JButton jButtonNewGame = new JButton(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_012));
        jButtonNewGame.setName(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_012) + Constants.GAME_TEXT_SEPARATOR + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_000));
        this.jPanelButton.add(jButtonNewGame);
        // Add panels to the main content pane
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 15;
        this.add(this.jPanelButton, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(this.jPanelGame, gridBagConstraints);
        // Set action listener to the new game button
        jButtonNewGame.addActionListener(this.gameListener);
    }

    /**
     * Prepare frame to draw
     */
    public void prepare() {
        this.remove(this.jPanelButton);
        this.remove(this.jPanelGame);
        this.createNewGameButtonPanel();
    }

    /**
     * Prepare frame to refresh
     */
    public void refresh() {
        this.pack();
        this.repaint();
    }

    /**
     * Get JPanelGame
     * @return JPanel game
     */
    public JPanel getjPanelGame() {
        return jPanelGame;
    }
}
