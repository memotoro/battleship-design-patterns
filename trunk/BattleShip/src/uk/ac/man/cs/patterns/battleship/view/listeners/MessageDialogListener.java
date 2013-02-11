package uk.ac.man.cs.patterns.battleship.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;
import uk.ac.man.cs.patterns.battleship.view.MessageDialog;

/**
 * Class that represent message dialog in pop up form
 * @author Guillermo Antonio Toro Bayona
 */
public class MessageDialogListener implements ActionListener {

    /**
     * Message dialog
     */
    private MessageDialog messageDialog;

    /**
     * Constructor
     * @param messageDialog
     */
    public MessageDialogListener(MessageDialog messageDialog) {
        this.messageDialog = messageDialog;
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
            // Validate if the button is OK
            if (infoButton[0].equals(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_017))
                    && infoButton[1].equals(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_015))) {
                // Set the status
                this.messageDialog.doClose(MessageDialog.RET_OK);
            } // Validate if the button is Cancel
            else if (infoButton[0].equals(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_017))
                    && infoButton[1].equals(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_016))) {
                // Set the status
                this.messageDialog.doClose(MessageDialog.RET_CANCEL);
            }
        }
    }
}
