package tictactoe.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.components.BoardField;
import tictactoe.view.util.ViewMixin;

/**
 * A game board with a dimension of 3 x 3 fields.
 *
 * Created by degonas on 17.07.2017.
 */
public class GameBoard extends GridPane implements ViewMixin {

    private final RootPM pm;


    /* holds all fields of this gameboard. */
    private ObservableList<BoardField> allFields = FXCollections.observableArrayList(); //#Frage: Warum ObjectProperty statt StringProperty (Funktionsweise enum)?

    public GameBoard(RootPM pm){
        this.pm = pm;
        init();
    }

    @Override
    public void initializeSelf() {
        setId("gameboard");
    }

    @Override
    public void initializeParts() {
        for (int i = 0; i < RootPM.AMOUNT_OF_FIELDS; ++i) {
            allFields.add(new BoardField(pm));
        }
    }

    @Override
    public void layoutParts() {
        generateGameBoard();

        // Growth vertically (Höhe)
        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(rc, rc, rc);

        // Growth horizontally (Breite)
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(cc, cc, cc);
    }

    /**
     * fill GameBoard with n x n fields. n = rowLength.
     */
    private void generateGameBoard(){
        int i = 0, rowIndex = 0, rowLenght = (int) Math.sqrt(RootPM.AMOUNT_OF_FIELDS);
        for(BoardField field : allFields){
            add(field, i % rowLenght, rowIndex);
            i++;
            if (i % rowLenght == 0){    //new line
                rowIndex++;
            }
        }
    }
}
