package tictactoe.presentationmodel;

import javafx.beans.property.*;
import tictactoe.presentationmodel.states.FieldState;
import tictactoe.presentationmodel.states.fieldstateimpl.EmptyState;

/**
 * Created by Degonas on 18.07.2017.
 */
public class BoardFieldPM {

    private final IntegerProperty id = new SimpleIntegerProperty();

    /* the state of this BoardFieldPM (empty, taken by player 01 or player 02 */
    private final ObjectProperty<FieldState> state = new SimpleObjectProperty<>();

    private final BooleanProperty disable = new SimpleBooleanProperty(false);


    /**
     * initializes a presentation of a GameBoard field with the default state "EMPTY".
     * @param id of this field.
     */
    public BoardFieldPM(int id){
        setId(id);
        setState(new EmptyState());
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

    public boolean isDisabled() {
        return disable.get();
    }

    public BooleanProperty disableProperty() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable.set(disable);
    }
}

