package tictactoe.view;

import javafx.scene.layout.BorderPane;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class RootPane extends BorderPane implements ViewMixin{

    private final RootPM pm;

    private Header menuBar;
    private GameBoard gameBoard;

    public RootPane(RootPM pm){
        this.pm = pm;

        init();
    }

    @Override
    public void initializeParts() {
        menuBar = new Header(pm);

        gameBoard = new GameBoard(pm);
    }

    @Override
    public void layoutParts() {
        setTop(menuBar);
        setCenter(gameBoard);
    }
}
