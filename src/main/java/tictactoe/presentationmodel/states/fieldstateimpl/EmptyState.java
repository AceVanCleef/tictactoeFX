package tictactoe.presentationmodel.states.fieldstateimpl;

import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.states.FieldState;

/**
 * Created by Degonas on 18.07.2017.
 */
public class EmptyState extends FieldState{

    //Constructor
    public EmptyState() {
        super(StatusCode.EMPTY);
    }

    @Override
    public SVGPath getStateSymbol() {
        return null;
    }

    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }
}
