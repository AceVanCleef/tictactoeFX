package tictactoe.presentationmodel.states;

import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.states.fieldstateimpl.*;

/**
 * represents the state of an individual BoardFieldPM. Possible states are listed in enum StatusCode.
 *
 * Created by Degonas on 18.07.2017.
 */
public abstract class FieldState {

    /* lists the types of all available FieldStates */
    public enum StatusCode {
        EMPTY, TAKEN_BY_PLAYER_01, TAKEN_BY_PLAYER_02, TAKEN_BY_PLAYER_03, TAKEN_BY_PLAYER_04
    }

    /** stores the StatusCode of this FieldState instance (e.g new TakenByPlayer01() -> TAKEN_BY_PLAYER_01)
     *
     * Use Cases:
     *  - Used in GameRule for checking against win and draw conditions by COMPARISON between fields.
     *    E.g. fieldA.getState().getStatusCode() == fieldB.getState().getStatusCode()
     */
    private StatusCode statusCode;


    //Constructor: initializes fields of this abstract class for its subclasses.
    protected FieldState(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    //factory method
    public static FieldState getStateByPlayerId(int playerId){
        switch (playerId){
            case 1: return new TakenByPlayer01();
            case 2: return new TakenByPlayer02();
            case 3: return new TakenByPlayer03();
            case 4: return new TakenByPlayer04();
            default: return new EmptyState();
        }
    }

    /**
     * returns the icon for the currentPlayer.
     *
     * How to use this method: From the BoardField view, call pm.updateGameBy(fieldId). The RootPM pm will
     * call this method internally and hand it over (return) to the view.
     * @return SVGPath fieldMarker representing the implemented state.
     */
    public abstract SVGPath getStateSymbol();

    /**
     * allows comparison of states between two (or more) BoardFieldPMs.
     *
     * Note: used in GameRules' isWon() and isDraw() - checks.
     * @return
     */
    public StatusCode getStatusCode() {
        return statusCode;
    }
}
