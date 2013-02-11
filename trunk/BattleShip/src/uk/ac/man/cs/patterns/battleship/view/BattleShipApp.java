package uk.ac.man.cs.patterns.battleship.view;

/**
 * Class that execute the main application and initialise the app.
 * @author Guillermo Antonio Toro Bayona
 */
public class BattleShipApp {

    /**
     * Main method.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialise one instance of BattleShipMainFrame
        new BattleShipMainFrame().setVisible(true);
    }
}
