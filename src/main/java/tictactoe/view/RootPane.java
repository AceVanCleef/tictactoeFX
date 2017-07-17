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
    private PlayerOverview player01;
    private PlayerOverview player02;
    private ScoreBoardWrapper score;

    public RootPane(RootPM pm){
        this.pm = pm;

        init();
    }

    @Override
    public void initializeSelf() {
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        this.getStylesheets().addAll(stylesheet);
    }

    @Override
    public void initializeParts() {
        menuBar = new Header(pm);

        gameBoard = new GameBoard(pm);

        player01 = new PlayerOverview(pm, "Player 01");
        player02 = new PlayerOverview(pm, "Player 02");

        score = new ScoreBoardWrapper(pm);
    }

    @Override
    public void layoutParts() {
        setTop(menuBar);
        setCenter(gameBoard);
        setLeft(player01);
        setRight(player02);
        setBottom(score);
    }
}
