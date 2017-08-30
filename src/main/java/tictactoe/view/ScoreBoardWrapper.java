package tictactoe.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import tictactoe.presentationmodel.PlayerPM;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.util.ViewMixin;

import java.util.List;

/**
 * Created by degonas on 17.07.2017.
 */
public class ScoreBoardWrapper extends VBox implements ViewMixin {

    private final RootPM pm;

    private Label title;
    private ScoreBoard scoreBoard;
    private Label drawLabel;
    private Label drawCount;

    /*#NewGame: is this view updated? */
    private final BooleanProperty isUpdated = new SimpleBooleanProperty(false);

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

        //#NewGame
        isUpdatedProperty().bindBidirectional(pm.getNewGameCheckList().isScoreBoardPanelRefreshedProperty());
    }

    @Override
    public void addValueChangedListeners() {
        //#NewGAme
        pm.setUpNewGameProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                refreshScoreBoardPanel();
                setIsUpdated(true);
            }
        });
    }

    //#NewGAme
    private void refreshScoreBoardPanel(){
        getChildren().clear();
        init();
    }

    /***************************** setters and getters **************************************/

    public boolean isIsUpdated() {
        return isUpdated.get();
    }

    public BooleanProperty isUpdatedProperty() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated.set(isUpdated);
    }
}


class ScoreBoard extends TableView<PlayerPM> implements ViewMixin{

    private final RootPM pm;

    public ScoreBoard(RootPM pm){
        this.pm = pm;

        init();
    }

    @Override
    public void initializeSelf() {
        this.setItems(pm.getAllPlayers());
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);    // Verteile die Spalten regelmässig in der TabelView

    }

    @Override
    public void initializeParts() {
        //TableColumn<DATENTYP DER ZELLE, ANZEIGETYP DER ZELLE>
        TableColumn<PlayerPM, Number> idCol         = new TableColumn<>("ID-Nr.");
        TableColumn<PlayerPM, String> nameCol       = new TableColumn<>("Name");
        TableColumn<PlayerPM, Number> scoreCol      = new TableColumn<>("Score");

        idCol.setCellValueFactory(cell -> cell.getValue().idProperty());
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
        scoreCol.setCellValueFactory(cell -> cell.getValue().scoreProperty());

        this.getColumns().addAll(idCol, nameCol, scoreCol);
    }

    @Override
    public void layoutParts() {

    }

}

//class ScoreBoard extends HBox implements ViewMixin{
//
//    private final RootPM pm;
//
//    private Label scorePlayer01;
//    private Label colon;
//    private Label scorePlayer02;
//
//    private HBox spacerLeft;
//    private HBox spacerRight;
//
//    public ScoreBoard(RootPM pm){
//        this.pm = pm;
//
//        init();
//    }
//
//    @Override
//    public void initializeParts() {
//        scorePlayer01 = new Label("0");
//        colon = new Label(":");
//        scorePlayer02 = new Label("0");
//
//        spacerLeft = new HBox();
//        spacerRight = new HBox();
//    }
//
//    @Override
//    public void layoutParts() {
//        getChildren().addAll(scorePlayer01, spacerLeft, colon, spacerRight, scorePlayer02);
//
//        //gaps between children
//        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
//        HBox.setHgrow(spacerRight, Priority.ALWAYS);
//    }
//
//    @Override
//    public void setupBindings() {
//        scorePlayer01.textProperty().bind(pm.findPlayerBy(1).scoreProperty().asString());
//        scorePlayer02.textProperty().bind(pm.findPlayerBy(2).scoreProperty().asString());
//    }
//}