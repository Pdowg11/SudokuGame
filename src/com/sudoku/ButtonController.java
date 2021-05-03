package src.com.sudoku;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class controls all user actions from ButtonPanel.
 *
 * @author Preston Ward
 */
public class ButtonController implements ActionListener {
    private final Game game;

    /**
     * Constructor, sets game.
     *
     * @param game  Game to be set.
     */
    public ButtonController(Game game) {
        this.game = game;
    }

    /**
     * Performs action after user pressed button.
     *
     * @param e ActionEvent.
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New" -> game.newGame();
            case "Check" -> game.checkGame();
            case "Exit" -> System.exit(0);
            case "Help on" -> game.setHelp(((JCheckBox) e.getSource()).isSelected());
            default -> game.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
        }
    }
}