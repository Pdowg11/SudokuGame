package src.com.sudoku;

/**
 * Enumeration used to inform observers what to update.
 *
 * @author Preston Ward
 */
public enum UpdateAction {
    NEW_GAME,
    CHECK,
    SELECTED_NUMBER,
    CANDIDATES,
    HELP
}