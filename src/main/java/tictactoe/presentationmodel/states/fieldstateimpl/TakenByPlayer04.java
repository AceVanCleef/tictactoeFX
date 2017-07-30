package tictactoe.presentationmodel.states.fieldstateimpl;

import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.states.FieldState;

/**
 * Created by Degonas on 30.07.2017.
 */
public class TakenByPlayer04 extends FieldState {

    //Constructor
    public TakenByPlayer04() {
        super(StatusCode.TAKEN_BY_PLAYER_04);
    }

    @Override
    public SVGPath getStateSymbol() {
        SVGPath square = new SVGPath();
        square.setContent("M243.996203,480 L252.003797,480 C254.21117,480 256,481.789161 256,483.996203 L256,492.003797 C256,494.21117 254.210839,496 252.003797,496 L243.996203,496 C241.78883,496 240,494.210839 240,492.003797 L240,483.996203 C240,481.78883 241.789161,480 243.996203,480 Z M244.003351,481 C242.344646,481 241,482.342379 241,484.003351 L241,491.996649 C241,493.655354 242.342379,495 244.003351,495 L251.996649,495 C253.655354,495 255,493.657621 255,491.996649 L255,484.003351 C255,482.344646 253.657621,481 251.996649,481 Z M244.003351,481");    //todo

        return square;
    }

    @Override
    public StatusCode getStatusCode() {
        return super.getStatusCode();
    }
}
