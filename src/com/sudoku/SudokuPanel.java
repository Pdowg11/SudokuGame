package src.com.sudoku;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.Observable;
import java.util.Observer;

/**
 * This class draws the sudoku panel and reacts to updates from the model.
 *
 * @author Preston Ward
 */
public class SudokuPanel extends JPanel implements Observer {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    // Color constant for candidates.
    private static final Color COLOR_CANDIDATE = new Color(102, 153, 255);

    private final Field[][] fields;       // Array of fields.
    private final JPanel[][] panels;      // Panels holding the fields.

    /**
     * Constructs the panel, adds sub panels and adds fields to these sub panels.
     */
    public SudokuPanel() {
        super(new GridLayout(3, 3));

        panels = new JPanel[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                panels[y][x] = new JPanel(new GridLayout(3, 3));
                panels[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(panels[y][x]);
            }
        }

        fields = new Field[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x] = new Field(x, y);
                panels[y / 3][x / 3].add(fields[y][x]);
            }
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
            case NEW_GAME -> setGame((Game) o);
            case CHECK -> setGameCheck((Game) o);
            case SELECTED_NUMBER, CANDIDATES, HELP -> setCandidates((Game) o);
        }
    }

    /**
     * Sets the fields corresponding to given game.
     *
     * @param game  Game to be set.
     */
    public void setGame(Game game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x].setBackground(Color.WHITE);
                fields[y][x].setNumber(game.getNumber(x, y), false);
            }
        }
    }

    /**
     * Sets fields validity according to given game.
     *
     * @param game  Current game.
     */
    private void setGameCheck(Game game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x].setBackground(Color.WHITE);
                if (fields[y][x].getForeground().equals(Color.BLUE))
                    fields[y][x].setBackground(game.isCheckValid(x, y) ? Color.GREEN : Color.RED);
            }
        }
    }

    /**
     * Shows the candidates according to given game.
     *
     * @param game  Current game.
     */
    private void setCandidates(Game game) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                fields[y][x].setBackground(Color.WHITE);
                if (game.isHelp() && game.isSelectedNumberCandidate(x, y))
                    fields[y][x].setBackground(COLOR_CANDIDATE);
            }
        }
    }

    /**
     * Adds controller to all sub panels.
     *
     * @param sudokuController  Controller which controls all user actions.
     */
    public void setController(SudokuController sudokuController) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++)
                panels[y][x].addMouseListener(sudokuController);
        }
    }
}