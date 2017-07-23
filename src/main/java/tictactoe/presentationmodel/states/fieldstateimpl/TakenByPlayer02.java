package tictactoe.presentationmodel.states.fieldstateimpl;

import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.states.FieldState;

/**
 * Created by Degonas on 18.07.2017.
 */
public class TakenByPlayer02 extends FieldState {

    public TakenByPlayer02() {
        super(StatusCode.TAKEN_BY_PLAYER_02);
    }

    @Override
    public SVGPath getStateSymbol() {
        SVGPath circle = new SVGPath();
        circle.setContent("M10,0 C4.5,0 0,4.5 0,10 C0,15.5 4.5,20 10,20 C15.5,20 20,15.5 20,10 C20,4.5 15.5,0 10,0 L10,0 Z M10,18 C5.6,18 2,14.4 2,10 C2,5.6 5.6,2 10,2 C14.4,2 18,5.6 18,10 C18,14.4 14.4,18 10,18 L10,18 Z");

        return circle;
    }

    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }
}
