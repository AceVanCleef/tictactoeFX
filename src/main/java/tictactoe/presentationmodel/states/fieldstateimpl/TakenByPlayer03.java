package tictactoe.presentationmodel.states.fieldstateimpl;

import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.states.FieldState;

/**
 * Created by Degonas on 30.07.2017.
 */
public class TakenByPlayer03 extends FieldState {

    //Constructor
    public TakenByPlayer03() {
        super(StatusCode.TAKEN_BY_PLAYER_03);
    }

    @Override
    public SVGPath getStateSymbol() {
        SVGPath triangle = new SVGPath();
        triangle.setContent("M536,480 L544,496 L528,496 Z M536,482 L530,495 L542,495 Z M536,482");    //todo

        return triangle;
    }

    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }
}
