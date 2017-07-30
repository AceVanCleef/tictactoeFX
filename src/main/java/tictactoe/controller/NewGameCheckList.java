package tictactoe.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import tictactoe.presentationmodel.RootPM;

/** #NewGame: knows when all views have been updated when RootPM's newGame() has been invoked and if so, informs RootPM (setSetUpNewGame(false).
 *
 * Usage:
 * - create instance of NewGameCheckList within RootPM.
 * - In view components, use 'pm.getNewGameCheckList()" and register them by setting up bindings with the individual
 *      boolean properties of this class.
 * Created by Degonas on 30.07.2017.
 */
public class NewGameCheckList {

    public NewGameCheckList(RootPM pm){
        this.pm = pm;

        //setUpBindingsWithPresentationModel();
        addValueChangeListeners();
    }

    /***************************** only related to tictactoe.presentationmodel **************************************/

    private RootPM pm;

// Potential idea for when more GUI components would be added
//    /* signals that the new game has been set up */
//    private final BooleanProperty setUpProcessFinished = new SimpleBooleanProperty(false);
//
//    private void setUpBindingsWithPresentationModel(){
//        pm.setUpNewGameProperty().bind(setUpProcessFinishedProperty());
//    }

    /***************************** only related to tictactoe.view **************************************/


    private final BooleanProperty isGameBoardRefreshed = new SimpleBooleanProperty(false);
    private final BooleanProperty isPlayersPanelLeftRefreshed = new SimpleBooleanProperty(false);
    private final BooleanProperty isPlayersPanelRightRefreshed = new SimpleBooleanProperty(false);

    //Note: bindings have to be done in the GUI components which should be updated/refreshed when a new game (round) should be started.

    /***************************** [CONTROLLER FUNCTIONALITY (Bridge)] functionalities related to both: view and presentation model**************************************/

    //when all GUI components' flags are set, then RootPM's flag will be reset.
    private void addValueChangeListeners(){
        isGameBoardRefreshedProperty().addListener((observable, oldValue, newValue) -> {
            if (areAllGuiComponentsRefreshed()){
                pm.setSetUpNewGame(false);
                resetAllGuiFlags();
            }
        });
        isPlayersPanelLeftRefreshedProperty().addListener((observable, oldValue, newValue) -> {
            if (areAllGuiComponentsRefreshed()){
                pm.setSetUpNewGame(false);
                resetAllGuiFlags();
            }
        });
        isPlayersPanelRightRefreshedProperty().addListener((observable, oldValue, newValue) -> {
            if (areAllGuiComponentsRefreshed()){
                pm.setSetUpNewGame(false);
                resetAllGuiFlags();
            }
        });
    }

    private boolean areAllGuiComponentsRefreshed(){
        return isIsGameBoardRefreshed() && isIsPlayersPanelLeftRefreshed() && isIsPlayersPanelRightRefreshed();
    }

    private void resetAllGuiFlags(){
        setIsGameBoardRefreshed(false);
        setIsPlayersPanelLeftRefreshed(false);
        setIsPlayersPanelRightRefreshed(false);
    }

    /***************************** setters and getters **************************************/

//    public boolean isSetUpProcessFinished() {
//        return setUpProcessFinished.get();
//    }
//
//    public BooleanProperty setUpProcessFinishedProperty() {
//        return setUpProcessFinished;
//    }
//
//    public void setSetUpProcessFinished(boolean setUpProcessFinished) {
//        this.setUpProcessFinished.set(setUpProcessFinished);
//    }

    public boolean isIsGameBoardRefreshed() {
        return isGameBoardRefreshed.get();
    }

    public BooleanProperty isGameBoardRefreshedProperty() {
        return isGameBoardRefreshed;
    }

    public void setIsGameBoardRefreshed(boolean isGameBoardRefreshed) {
        this.isGameBoardRefreshed.set(isGameBoardRefreshed);
    }

    public boolean isIsPlayersPanelLeftRefreshed() {
        return isPlayersPanelLeftRefreshed.get();
    }

    public BooleanProperty isPlayersPanelLeftRefreshedProperty() {
        return isPlayersPanelLeftRefreshed;
    }

    public void setIsPlayersPanelLeftRefreshed(boolean isPlayersPanelLeftRefreshed) {
        this.isPlayersPanelLeftRefreshed.set(isPlayersPanelLeftRefreshed);
    }

    public boolean isIsPlayersPanelRightRefreshed() {
        return isPlayersPanelRightRefreshed.get();
    }

    public BooleanProperty isPlayersPanelRightRefreshedProperty() {
        return isPlayersPanelRightRefreshed;
    }

    public void setIsPlayersPanelRightRefreshed(boolean isPlayersPanelRightRefreshed) {
        this.isPlayersPanelRightRefreshed.set(isPlayersPanelRightRefreshed);
    }
}
