package uk.ac.man.cs.patterns.battleship.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.man.cs.patterns.battleship.utils.Constants;
import uk.ac.man.cs.patterns.battleship.utils.PropertiesUtil;
import uk.ac.man.cs.patterns.battleship.view.listeners.MessageDialogListener;

/**
 * Class that represent pop up messages with confirmation or not.
 * @author Guillermo Antonio Toro Bayona
 */
public class MessageDialog extends JDialog {

    /**
     * JButton cancel
     */
    private JButton jButtonCancel;
    private JButton jButtonOk;
    private JLabel jLabelMessage;
    /**
     * Integer Return cancel and OK states
     */
    public static final int RET_CANCEL = 0;
    public static final int RET_OK = 1;
    private int returnStatus = RET_CANCEL;
    /**
     * MessageDialogListener reference
     */
    private MessageDialogListener messageDialogListener;

    /**
     * Constructor. Message dialog to display message in pop-up windows
     * @param parent JFrame
     * @param modal boolean modal mode
     * @param confirmation boolean confirmation mode
     * @param message String message to display
     */
    public MessageDialog(JFrame parent, boolean modal, boolean confirmation, String message) {
        super(parent, modal);
        // Initialize
        this.messageDialogListener = new MessageDialogListener(this);
        // Create the OK Button
        this.jButtonOk = new JButton();
        this.jButtonOk.setName(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_017) + Constants.GAME_TEXT_SEPARATOR + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_015));
        this.jButtonOk.setText(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_015));
        this.jButtonOk.addActionListener(this.messageDialogListener);
        // Create the cancel button
        this.jButtonCancel = new JButton();
        this.jButtonCancel.setName(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_017) + Constants.GAME_TEXT_SEPARATOR + PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_016));
        this.jButtonCancel.setText(PropertiesUtil.getInstance().getMessageByCode(Constants.CODE_016));
        this.jButtonCancel.addActionListener(this.messageDialogListener);
        if (!confirmation) {
            this.jButtonCancel.setVisible(false);
        }
        // Set the message
        this.jLabelMessage = new JLabel();
        this.jLabelMessage.setText(message);
        // Set layout
        this.setLayout(new GridLayout(0, 1));
        this.setSize(400, 100);
        // Add elements
        JPanel jPanelButton = new JPanel();
        this.add(this.jLabelMessage);
        this.add(jPanelButton);
        jPanelButton.setLayout(new GridLayout(0, 2));
        jPanelButton.add(this.jButtonOk);
        jPanelButton.add(this.jButtonCancel);
        // Set visible
        this.setVisible(true);
    }

    /**
     * Get the status
     * @return Integer Status
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * Method to set the behaviour on close
     * @param retStatus
     */
    public void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
}
