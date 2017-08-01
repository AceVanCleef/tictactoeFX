package tictactoe.view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import tictactoe.presentationmodel.PlayerPM;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.simplecontrols.PlayerOverview;
import tictactoe.view.util.ViewMixin;

import java.util.List;

/**
 * can differentiate itself whether it is situated left of BorderPane and therefore should
 * only represent players with odd IDs or is situated right and should only represent
 * players with even IDs.
 *
 * Created by Degonas on 30.07.2017.
 */
public class PlayersPanel extends VBox implements ViewMixin{

    /* used to differentiate whether this PlayersPanel is part of BorderPane.setLeft() or .setRight() */
    public enum PlayersPanelAlignment {
        LEFT, RIGHT
    }

    private RootPM pm;

    /* used to determine whether playerPMs with even or odd IDs will be represented in this PlayersPanel */
    private PlayersPanelAlignment alignment;

    /* holds a variable amount of PlayerOverviews */
    private ObservableList<PlayerOverview> myPlayers = FXCollections.observableArrayList();

    /*#NewGame: is this view updated? */
    private final BooleanProperty isUpdated = new SimpleBooleanProperty(false);




    public PlayersPanel(RootPM pm, PlayersPanelAlignment panelAlignment){
        this.pm = pm;
        alignment = panelAlignment;

        init();
    }


    @Override
    public void initializeParts() {
        //get the correct playerPMs. Either the ones with even or odd IDs.
        List<PlayerPM> playersToRepresent;
        if (alignment == PlayersPanelAlignment.LEFT) {
            playersToRepresent = pm.getPlayerPMsWithOddID();
        } else {
            playersToRepresent = pm.getPlayerPMsWithEvenID();
        }
        //fill myPlayers with PlayerOverviews
        for (PlayerPM playerPM : playersToRepresent){
            myPlayers.add( new PlayerOverview(pm, playerPM) );
        }
    }

    @Override
    public void layoutParts() {
        for (PlayerOverview playerOverview : myPlayers){
            this.getChildren().add(playerOverview);
        }
    }

    @Override
    public void addValueChangedListeners() {
        //#NewGAme
        pm.setUpNewGameProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                refreshPlayersPanel();
                //set refresh flag back to false:
                //pm.setSetUpNewGame(false);
                setIsUpdated(true);
            }
        });
    }

    @Override
    public void setupBindings() {
        //#NewGame: connection to NewGameCheckList
        if (alignment == PlayersPanelAlignment.LEFT){
            isUpdatedProperty().bindBidirectional(pm.getNewGameCheckList().isPlayersPanelLeftRefreshedProperty());
        } else {
            isUpdatedProperty().bindBidirectional(pm.getNewGameCheckList().isPlayersPanelRightRefreshedProperty());
        }
    }

    //#NewGAme
    private void refreshPlayersPanel(){
        getChildren().clear();
        myPlayers.clear();
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
