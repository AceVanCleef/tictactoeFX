package tictactoe.presentationmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.gamerules.GameRules;
import tictactoe.presentationmodel.states.GameStatePM;
import tictactoe.presentationmodel.states.FieldState;

/**
 * Created by degonas on 17.07.2017.
 */
public class RootPM {


    /* the maximum amount of fields this gameboard has. */
    public final static int AMOUNT_OF_FIELDS = 9;
    //Todo: turn AMOUNT_OF_FIELDS into a part of the instance.
    //Todo 2: when expanding to 2D and 3D models, refractor into Factory Pattern.

    private final ObservableList<BoardFieldPM> allFields = FXCollections.observableArrayList();


    /* the maximum amount of players this game has. */
    public final static int AMOUNT_OF_PLAYERS = 2;

    private final ObservableList<PlayerPM> allPlayers = FXCollections.observableArrayList();
    private final IntegerProperty currentPlayerId = new SimpleIntegerProperty();

    /* Game rules */
    private GameRules rules;
    /* represents the state of the currently running game round. Can be queried by using isWon(), isDraw() and isContinuing(). */
    private GameStatePM gameState;

    /* counts how many draws have happened */
    public final IntegerProperty drawCount = new SimpleIntegerProperty(0);

    public RootPM(){
        //prepare the GameBoard
        for (int i = 0; i < AMOUNT_OF_FIELDS; ++i) {
            allFields.add(new BoardFieldPM(i));
        }

        //prepare playerPMs
        for (int i = 0; i < AMOUNT_OF_PLAYERS; ++i) {
            allPlayers.add(new PlayerPM(i + 1));    //no mather how many players, their IDs must be in numerical sequence.
        }

        //this player begins the match
        setCurrentPlayerId(allPlayers.get(0).getId());

        //GameStatePM
        gameState = new GameStatePM();

        //set the game rules
        rules = defineGameRules();
    }

    /**
     *
     * @param fieldId of currently selected / clicked field.
     * @return SVGPath of current player's icon / field marker.
     */
    public SVGPath updateGameBy(int fieldId){
        //1) claim Field for current player
        //2) change state of current field
        SVGPath fieldMarker = claimFieldForCurrentPlayer(fieldId);

        //3) update current game state
        rules.updateGameState(allFields);   //check win conditions

        //4) check if game cdntinues
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

        //cycle through allFields
        if (getCurrentPlayerId() < AMOUNT_OF_PLAYERS) {
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
}
