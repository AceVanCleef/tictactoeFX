package tictactoe.presentationmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.SVGPath;
import tictactoe.controller.NewGameCheckList;
import tictactoe.presentationmodel.gamerules.GameRules;
import tictactoe.presentationmodel.states.GameStatePM;
import tictactoe.presentationmodel.states.FieldState;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by degonas on 17.07.2017.
 */
public class RootPM {


    /* the maximum amount of fields this gameboard has. */
    private int AMOUNT_OF_FIELDS;
    //Todo 2: when expanding to 2D and 3D models, refractor into Factory Pattern.
    /* #NewGame: @GUI: amountOfFieldsTF */
    private final IntegerProperty newAmountOfFields = new SimpleIntegerProperty();


    private final ObservableList<BoardFieldPM> allFields = FXCollections.observableArrayList();


    /* the maximum amount of players this game has. */
    private int AMOUNT_OF_PLAYERS;
    /* #NewGame: @GUI: amountOfPlayersTF */
    private final IntegerProperty newAmountOfPlayers = new SimpleIntegerProperty();

    private final ObservableList<PlayerPM> allPlayers = FXCollections.observableArrayList();
    private final IntegerProperty currentPlayerId = new SimpleIntegerProperty();

    /* Game rules */
    private GameRules rules;
    /* represents the state of the currently running game round. Can be queried by using isWon(), isDraw() and isContinuing(). */
    private GameStatePM gameState;

    /* #Score: counts how many draws have happened */
    private final IntegerProperty drawCount = new SimpleIntegerProperty(0);


    /*#NewGame: triggers the generation of a new GameBoard view and other GUI components */
    private final BooleanProperty setUpNewGame = new SimpleBooleanProperty(false);
    /*#NewGame: only resets setUpNewGame if all GUI components were refreshed/updated */
    private NewGameCheckList newGameCheckList;


    /************************************ Constructor(s) ******************************************/


    public RootPM(int amountOfFields, int amountOfPlayers) {
        setAmountOfFields(amountOfFields);
        setAmountOfPlayers(amountOfPlayers);

        //prepare the GameBoard
        for (int i = 0; i < AMOUNT_OF_FIELDS; ++i) {
            allFields.add(new BoardFieldPM(i));
        }

        //prepare playerPMs
        for (int i = 0; i < getAmountOfPlayers(); ++i) {
            allPlayers.add(new PlayerPM(i + 1));    //no mather how many players, their IDs must be in numerical sequence.
        }

        //this player begins the match
        setCurrentPlayerId(allPlayers.get(0).getId());

        //GameStatePM
        gameState = new GameStatePM();

        //set the game rules
        rules = defineGameRules();

        //instantiate NewGameCheckList
        newGameCheckList = new NewGameCheckList(this);
    }

    /**
     * updates the current BoardFieldPM, the GameStatePM and the Score and returns
     * the icon / symbol of the currently selected player (to the view.BoardField which called this method).
     *
     * Note: called in view.BoardField's EventHandler
     * @param fieldId of currently selected / clicked field.
     * @return SVGPath of current player's icon / field marker.
     */
    public SVGPath updateGameBy(int fieldId){
        //1) claim Field for current player
        //2) change state of current field
        SVGPath fieldMarker = claimFieldForCurrentPlayer(fieldId);

        //3) update current game state
        rules.updateGameState(allFields);   //check win conditions

        //4) check if game continues
        if ( gameState.doesGameContinue() ) {
            //5a) switch to next player and mark the currentField with its FieldMarker
            nextPlayer();

            System.out.println("Game continues.");   //Todo: remove

            return fieldMarker;
        } else {
            //5b) game has been won or it's a draw:
            updateScore();
            disableAllBoardFieldPMs();
        }
        return fieldMarker;
    }

    /**
     * disables the BoardFieldPM selected by the current player and marks it
     * as taken by the current player.
     * Note: selection triggered by EventHandler in BoardField view.
     * @param fieldId of currentField
     * @return SVGPath fieldMarker for the currentPlayer
     */
    private SVGPath claimFieldForCurrentPlayer(int fieldId) {
        //disable currentField
        BoardFieldPM currentField = findFieldBy(fieldId);
        currentField.setDisable(true);

        //set the state of currentField...
        FieldState state = FieldState.getStateByPlayerId( getCurrentPlayerId() );
        currentField.setState(state);
        //...return its SVGPath representation to the GUI.
        return state.getStateSymbol();
    }

    /** increments either the score of the winning player or the drawCount according to
     *  the current state of the game.
     *
     *  #Score
     */
    private void updateScore(){
        //check if current player has won
        if ( gameState.isWon() ) {
            PlayerPM currentPlayer = allPlayers.get(getCurrentPlayerId() - 1);
            currentPlayer.setScore( currentPlayer.getScore() + 1 );

            System.out.println("Game has been won by "+ currentPlayer.getName() +".");   //Todo: remove
        } else if ( gameState.isDraw() ) {
            setDrawCount( getDrawCount() + 1);

            System.out.println("It's a draw."); //Todo: remove
        }
    }

    //#GameEnded (by draw or victory)
    private void disableAllBoardFieldPMs(){
        for (BoardFieldPM fieldPM : allFields) {
            fieldPM.setDisable(true);
        }
    }

    /** selects the next players.
     */
    private void nextPlayer(){
        //siple switch approach using magical numbers
//        if ( getCurrentPlayerId() == 1){
//            setCurrentPlayerId(2);
//        } else if ( getCurrentPlayerId() == 2){
//            setCurrentPlayerId(1);
//        }

        //cycle through allPlayers
        if (getCurrentPlayerId() < getAmountOfPlayers()) {
            setCurrentPlayerId( getCurrentPlayerId() + 1 ); //iterate through allPlayers from 1 to n.
        } else {
            setCurrentPlayerId(allPlayers.get(0).getId());  // from last element back to first one.
        }
    }


    public BoardFieldPM findFieldBy(int fieldId) {
        return allFields.stream()
                .filter(fieldPM -> fieldPM.getId() == fieldId)
                .findFirst()
                .get();
    }

    public PlayerPM findPlayerBy(int playerId) {
        return allPlayers.stream()
                .filter(playerPM -> playerPM.getId() == playerId)
                .findFirst()
                .get();
    }

    /************************************ Game Rules and State ******************************************/

    private GameRules defineGameRules(){
        if (Math.sqrt(AMOUNT_OF_FIELDS) == 3){
            return GameRules.getGameRulesFor(GameRules.RuleSet._2D_3X3, gameState);
        } else if (Math.sqrt(AMOUNT_OF_FIELDS) == 4){
            return GameRules.getGameRulesFor(GameRules.RuleSet._2D_4X4, gameState);
        } else if (Math.sqrt(AMOUNT_OF_FIELDS) == 5){
            return GameRules.getGameRulesFor(GameRules.RuleSet._2d_5x5, gameState);
        }
        return null;    //Todo: does make this sense?
    }

    /******************************* creating new game round *********************************/

    /**
     * #NewGame
     * @param amountOfFields
     * @param amountOfPlayers
     */
    public void newGame(int amountOfFields, int amountOfPlayers) {
        setAmountOfFields(amountOfFields);
        if (amountOfPlayers != allPlayers.size()) {
            setAmountOfPlayers(amountOfPlayers);
        }

        //reset collections
        allFields.clear();
        if (amountOfPlayers != allPlayers.size()) {
            allPlayers.clear();
        }

        //prepare the GameBoard
        for (int i = 0; i < AMOUNT_OF_FIELDS; ++i) {
            allFields.add(new BoardFieldPM(i));
        }

        //prepare playerPMs
        if (amountOfPlayers != allPlayers.size()) {
            for (int i = 0; i < getAmountOfPlayers(); ++i) {
                allPlayers.add(new PlayerPM(i + 1));    //no mather how many players, their IDs must be in numerical sequence.
            }
        }

        //reset drawCount
        if (amountOfPlayers != allPlayers.size()) {
            setDrawCount(0);
        }

        //this player begins the match
        setCurrentPlayerId(allPlayers.get(0).getId());

        //GameStatePM
        gameState = new GameStatePM();

        //set the game rules
        rules = defineGameRules();

        //trigger GUI refresh (Note: GameBoard.java will also reset this flag)
        setSetUpNewGame(true);
    }

    /********** to divide players into two groups for GUI (BorderPane.setLeft() vs. BorderPane.setRight() ) ****************/
    //used in view.PlayersPanel
    public List<PlayerPM> getPlayerPMsWithEvenID(){
        return allPlayers.stream()
                .filter(playerPM -> playerPM.getId() % 2 == 0)
                .collect(Collectors.toList());
    }

    //used in view.PlayersPanel
    public List<PlayerPM> getPlayerPMsWithOddID(){
        return allPlayers.stream()
                .filter(playerPM -> playerPM.getId() % 2 == 1)
                .collect(Collectors.toList());
    }

    /******************************* getters and setters *********************************/

    public int getCurrentPlayerId() {
        return currentPlayerId.get();
    }

    public IntegerProperty currentPlayerIdProperty() {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(int currentPlayerId) {
        this.currentPlayerId.set(currentPlayerId);
    }

    public ObservableList<BoardFieldPM> getAllFields() {
        return allFields;
    }

    public ObservableList<PlayerPM> getAllPlayers() {
        return allPlayers;
    }


    public int getDrawCount() {
        return drawCount.get();
    }

    public IntegerProperty drawCountProperty() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount.set(drawCount);
    }

    public boolean getSetUpNewGame() {
        return setUpNewGame.get();
    }

    public BooleanProperty setUpNewGameProperty() {
        return setUpNewGame;
    }

    public void setSetUpNewGame(boolean setUpNewGame) {
        this.setUpNewGame.set(setUpNewGame);
    }

    /**
     * guarantees...
     * - a GameBoard with minimal size of 3x3.
     * - a square GameBoard by transforming the square root of amountOfFields into
     * the next lower natural Number n and stores n^2 as the value of AMOUNT_OF_FIELDS.
     * @param amountOfFields
     */
    private void setAmountOfFields(int amountOfFields) {
        if (amountOfFields < 9) {
            this.AMOUNT_OF_FIELDS = 9;     //the smallest game board size is 3x3.
            return; //shortcut.
        }
        //1) calculate potential length: E.g. 15^(1/2) = 3.87
        double boardLength = Math.sqrt(amountOfFields);
        //2) Transform length into natural number (e.g. 3.87 -> 3)
        int n = (int) boardLength;
        //3) ..and calculate the desired amount of fields: e.g. 3^2 = 9.
        int desiredFieldCount = (int) Math.pow(n, 2);

        //set values
        setNewAmountOfFields( desiredFieldCount );
        this.AMOUNT_OF_FIELDS = desiredFieldCount;
    }

    public int getAmountOfFields(){
        return AMOUNT_OF_FIELDS;
    }

    /**
     * guarantees...
     * - the minimum amount of players allowed is 2.
     * - the maximum amount of players allowed is 4.
     * @param amountOfPlayers
     */
    private void setAmountOfPlayers(int amountOfPlayers) {
        //minimal amount of players
        if (amountOfPlayers < 2) {
            AMOUNT_OF_PLAYERS = 2;
            setNewAmountOfPlayers(2);
            return;
        }
        //maximal amount of players
        if (amountOfPlayers > 4) {
            AMOUNT_OF_PLAYERS = 4;
            setNewAmountOfPlayers(4);
            return;
        }
        setNewAmountOfPlayers(amountOfPlayers);
        AMOUNT_OF_PLAYERS = amountOfPlayers;
    }

    public int getAmountOfPlayers(){
        return AMOUNT_OF_PLAYERS;
    }

    public int getNewAmountOfFields() {
        return newAmountOfFields.get();
    }

    public IntegerProperty newAmountOfFieldsProperty() {
        return newAmountOfFields;
    }

    public void setNewAmountOfFields(int newAmountOfFields) {
        this.newAmountOfFields.set(newAmountOfFields);
    }

    public int getNewAmountOfPlayers() {
        return newAmountOfPlayers.get();
    }

    public IntegerProperty newAmountOfPlayersProperty() {
        return newAmountOfPlayers;
    }

    public void setNewAmountOfPlayers(int newAmountOfPlayers) {
        this.newAmountOfPlayers.set(newAmountOfPlayers);
    }

    /**
     * Note: In view components, use 'pm.getNewGameCheckList()" and register them by setting up bindings with the individual
     *      boolean properties of newGameCheckList class.
     * @return
     */
    public NewGameCheckList getNewGameCheckList() {
        return newGameCheckList;
    }
}
