package tictactoe.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
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

    /* the maximum amount of fields this gameboard has. */
    private static int AMOUNT_OF_FIELDS = 9;

    /* holds all fields of this gameboard. */
    private ObservableList<BoardField> allFields = FXCollections.observableArrayList();

    public GameBoard(RootPM pm){
        this.pm = pm;
        init();
    }

    @Override
    public void initializeParts() {
        for (int i = 0; i < AMOUNT_OF_FIELDS; ++i) {
            allFields.add(new BoardField(pm));
        }
    }

    @Override
    public void layoutParts() {

        //fill GameBoard with n x n fields. n = rowLength.
        int i = 0, rowIndex = 0, rowLenght = (int) Math.sqrt(AMOUNT_OF_FIELDS);
        for(BoardField field : allFields){
            add(field, i % rowLenght, rowIndex);
            i++;
            if (i % rowLenght == 0){    //new line
                rowIndex++;
            }
        }
    }
}
