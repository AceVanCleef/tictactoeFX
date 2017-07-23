package tictactoe.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class ScoreBoardWrapper extends VBox implements ViewMixin {

    private final RootPM pm;

    private Label title;
    private ScoreBoard scoreBoard;
    private Label drawLabel;
    private Label drawCount;

    public ScoreBoardWrapper(RootPM pm){
        this.pm = pm;

        init();
    }

    @Override
    public void initializeSelf() {
        setId("score-board-wrapper");
    }

    @Override
    public void initializeParts() {
        title = new Label("Score");
        scoreBoard = new ScoreBoard(pm);
        drawLabel = new Label("Draws: ");
        drawCount = new Label();
;    }

    @Override
    public void layoutParts() {
        getChildren().addAll(title, scoreBoard, drawLabel, drawCount);
    }

    @Override
    public void setupBindings() {
        drawCount.textProperty().bind(pm.drawCountProperty().asString());
    }
}


class ScoreBoard extends HBox implements ViewMixin{

    private final RootPM pm;

    private Label scorePlayer01;
    private Label colon;
    private Label scorePlayer02;

    private HBox spacerLeft;
    private HBox spacerRight;

    public ScoreBoard(RootPM pm){
        this.pm = pm;

        init();
    }

    @Override
    public void initializeParts() {
        scorePlayer01 = new Label("0");
        colon = new Label(":");
        scorePlayer02 = new Label("0");

        spacerLeft = new HBox();
        spacerRight = new HBox();
    }

    @Override
    public void layoutParts() {
        getChildren().addAll(scorePlayer01, spacerLeft, colon, spacerRight, scorePlayer02);

        //gaps between children
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);
    }

    @Override
    public void setupBindings() {
        scorePlayer01.textProperty().bind(pm.findPlayerBy(1).scoreProperty().asString());
        scorePlayer02.textProperty().bind(pm.findPlayerBy(2).scoreProperty().asString());
    }
}