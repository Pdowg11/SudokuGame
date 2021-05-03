package src.com.sudoku;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Observable;
import java.util.Observer;

/**
 * This class draws the button panel and reacts to updates from the model.
 *
 * @author Preston Ward
 */
public class ButtonPanel extends JPanel implements Observer {
    @Serial
    private static final long serialVersionUID = 1L;
    JButton btnNew, btnCheck, btnExit;   // Used buttons.
    JCheckBox cbHelp;               // Used check box.
    ButtonGroup bgNumbers;          // Group for grouping the toggle buttons.
    JToggleButton[] btnNumbers;     // Used toggle buttons.

    /**
     * Constructs the panel and arranges all components.
     */
    public ButtonPanel() {
        super(new BorderLayout());

        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.NORTH);

        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
        pnlAlign.add(pnlOptions);

        btnNew = new JButton("New");
        btnNew.setFocusable(false);
        pnlOptions.add(btnNew);

        btnCheck = new JButton("Check");
        btnCheck.setFocusable(false);
        pnlOptions.add(btnCheck);

        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        pnlOptions.add(btnExit);

        JPanel pnlNumbers = new JPanel();
        pnlNumbers.setLayout(new BoxLayout(pnlNumbers, BoxLayout.PAGE_AXIS));
        pnlNumbers.setBorder(BorderFactory.createTitledBorder(" Numbers "));
        pnlAlign.add(pnlNumbers);

        JPanel pnlNumbersHelp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersHelp);

        cbHelp = new JCheckBox("Help on", true);
        cbHelp.setFocusable(false);
        pnlNumbersHelp.add(cbHelp);

        JPanel pnlNumbersNumbers = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersNumbers);

        bgNumbers = new ButtonGroup();
        btnNumbers = new JToggleButton[9];
        for (int i = 0; i < 9; i++) {
            btnNumbers[i] = new JToggleButton("" + (i + 1));
            btnNumbers[i].setPreferredSize(new Dimension(40, 40));
            btnNumbers[i].setFocusable(false);
            bgNumbers.add(btnNumbers[i]);
            pnlNumbersNumbers.add(btnNumbers[i]);
        }
    }

    /**
     * Method called when model sends update notification.
     *
     * @param o     The model.
     * @param arg   The UpdateAction.
     */
    public void update(Observable o, Object arg) {
        switch ((UpdateAction) arg) {
            case NEW_GAME, CHECK -> bgNumbers.clearSelection();
            default -> throw new IllegalStateException("Unexpected value: " + arg);
        }
    }

    /**
     * Adds controller to all components.
     *
     * @param buttonController  Controller which controls all user actions.
     */
    public void setController(ButtonController buttonController) {
        btnNew.addActionListener(buttonController);
        btnCheck.addActionListener(buttonController);
        btnExit.addActionListener(buttonController);
        cbHelp.addActionListener(buttonController);
        for (int i = 0; i < 9; i++)
            btnNumbers[i].addActionListener(buttonController);
    }
}