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

    public SVGPath claimFieldForCurrentPlayer(int fieldId) {
        //disable currentField
        BoardFieldPM currentField = findFieldBy(fieldId);
        currentField.setDisable(true);

        //set the state of currentField and...
        FieldState state = FieldState.getStateByPlayerId( getCurrentPlayerId() );
        currentField.setState(state);
        //...return its SVGPath representation to the GUI.
        return state.getStateSymbol();
    }


    public void nextPlayer(){
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

}
