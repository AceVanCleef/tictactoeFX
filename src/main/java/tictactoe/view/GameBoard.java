package tictactoe.view;

import javafx.scene.layout.GridPane;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.components.BoardField;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class GameBoard extends GridPane implements ViewMixin {

    private final RootPM pm;

    private BoardField field;

    public GameBoard(RootPM pm){
        this.pm = pm;
        init();
    }

    @Override
    public void initializeParts() {
        field = new BoardField(pm);
    }

    @Override
    public void layoutParts() {
        add(field, 0, 0);
    }
}
