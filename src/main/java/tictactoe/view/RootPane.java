package tictactoe.view;

import javafx.scene.layout.BorderPane;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.simplecontrols.PlayerOverview;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class RootPane extends BorderPane implements ViewMixin{

    private final RootPM pm;

    private Header menuBar;
    private GameBoard gameBoard;
    private PlayersPanel playersLeft;
    private PlayersPanel playersRight;
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

        playersLeft = new PlayersPanel(pm, PlayersPanel.PlayersPanelAlignment.LEFT);
        playersRight = new PlayersPanel(pm, PlayersPanel.PlayersPanelAlignment.RIGHT);

        score = new ScoreBoardWrapper(pm);
    }

    @Override
    public void layoutParts() {
        setTop(menuBar);
        setCenter(gameBoard);
        setLeft(playersLeft);
        setRight(playersRight);
        setBottom(score);
    }
}
