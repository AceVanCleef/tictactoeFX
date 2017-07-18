package tictactoe.presentationmodel;

import javafx.beans.property.*;

/**
 * Created by Degonas on 18.07.2017.
 */
public class BoardFieldPM {

    private final IntegerProperty id = new SimpleIntegerProperty();
    /* the state of this BoardFieldPM (empty, taken by player 01 or player 02 */
    private final ObjectProperty<FieldState> state = new SimpleObjectProperty<>();


    public BoardFieldPM(int id){
        setId(id);
        setState(FieldState.EMPTY);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public FieldState getState() {
        return state.get();
    }

    public ObjectProperty<FieldState> stateProperty() {
        return state;
    }

    public void setState(FieldState state) {
        this.state.set(state);
    }
}

/************************** States of BoardGame fields *********************************/

/**
 * holds all valid states a BoardGame field can have.
 */
enum FieldState {
    EMPTY, TAKEN_BY_PLAYER01, TAKEN_BY_PLAYER02
}
