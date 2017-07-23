package tictactoe.presentationmodel.states;

import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.states.fieldstateimpl.EmptyState;
import tictactoe.presentationmodel.states.fieldstateimpl.TakenByPlayer01;
import tictactoe.presentationmodel.states.fieldstateimpl.TakenByPlayer02;

/**
 * Created by Degonas on 18.07.2017.
 */
public abstract class FieldState {

    protected enum StatusCode {
        EMPTY, TAKEN_BY_PLAYER_01, TAKEN_BY_PLAYER_02
    }

    /* used for comparison between fields: A.getStatusCode() == B.getStatusCode() */
    private StatusCode statusCode;

    //initializes fields of this abstract class for its subclasses.
    protected FieldState(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public static FieldState getStateByPlayerId(int playerId){
        switch (playerId){
            case 1: return new TakenByPlayer01();
            case 2: return new TakenByPlayer02();
            default: return new EmptyState();
        }
    }

    public abstract SVGPath getStateSymbol();

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
