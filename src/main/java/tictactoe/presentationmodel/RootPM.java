package tictactoe.presentationmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by degonas on 17.07.2017.
 */
public class RootPM {


    /* the maximum amount of fields this gameboard has. */
    public final static int AMOUNT_OF_FIELDS = 9;

    private final ObservableList<BoardFieldPM> allFields = FXCollections.observableArrayList();


    /* the maximum amount of players this game has. */
    public final static int AMOUNT_OF_PLAYERS = 2;

    private final ObservableList<PlayerPM> allPlayers = FXCollections.observableArrayList();
    private final IntegerProperty currentPlayerId = new SimpleIntegerProperty();

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
