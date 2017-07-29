package tictactoe.presentationmodel.gamerules.impl;

import javafx.collections.ObservableList;
import tictactoe.presentationmodel.BoardFieldPM;
import tictactoe.presentationmodel.gamerules.GameRules;
import tictactoe.presentationmodel.states.FieldState;
import tictactoe.presentationmodel.states.GameStatePM;

/**
 * Created by Degonas on 23.07.2017.
 */
public class Rules_2D_3x3 extends GameRules {

    /*
        GameBoard indexes:
            00|01|02
            --------
            03|04|05
            --------
            06|07|08
        * */

    public Rules_2D_3x3(GameStatePM gameState) {
        super(gameState);
    }


    public void updateGameState(ObservableList<BoardFieldPM> allFields) {
        super.updateGameState(allFields);
    }

    /************************** win conditions **************************************/

    @Override
    protected boolean isWon(ObservableList<BoardFieldPM> allFields){
        if ( isNewGame(allFields) )   return false;       //prevents execution of several methods.
        if ( isWonVertically(allFields) )   return true;
        if ( isWonHorizontally(allFields) ) return true;
        if ( isWonDiagonally(allFields) )   return true;
        return false;
    }

    private boolean isNewGame(ObservableList<BoardFieldPM> allFields) {
        return allFields.stream().allMatch(boardFieldPM -> boardFieldPM.getState().getStatusCode() == FieldState.StatusCode.EMPTY);
    }

    private boolean isWonVertically(ObservableList<BoardFieldPM> allFields){
        for (int i = 0; i < 3; ++i) {
            BoardFieldPM a = allFields.get(i);
            BoardFieldPM b = allFields.get(i + 3);
            BoardFieldPM c = allFields.get(i + 6);
            if (a.getState().getStatusCode() == b.getState().getStatusCode() &&         //A == B == C and...
                    b.getState().getStatusCode() == c.getState().getStatusCode() &&
                    b.getState().getStatusCode() != FieldState.StatusCode.EMPTY) {      //aren't EMPTY
                return true;
            }
        }
        return false;
    }

    private boolean isWonHorizontally(ObservableList<BoardFieldPM> allFields) {
        for (int i = 0; i < allFields.size(); i = i + 3) {
            BoardFieldPM a = allFields.get(i);
            BoardFieldPM b = allFields.get(i + 1);
            BoardFieldPM c = allFields.get(i + 2);
            if (a.getState().getStatusCode() == b.getState().getStatusCode() &&         //A == B == C and...
                    b.getState().getStatusCode() == c.getState().getStatusCode() &&
                    b.getState().getStatusCode() != FieldState.StatusCode.EMPTY) {      //aren't EMPTY
                return true;
            }
        }
        return false;
    }

    private boolean isWonDiagonally(ObservableList<BoardFieldPM> allFields) {
        if (allFields.get(0).getState().getStatusCode() == allFields.get(4).getState().getStatusCode() &&           //A == B == C and...
                allFields.get(4).getState().getStatusCode() == allFields.get(8).getState().getStatusCode() &&
                allFields.get(4).getState().getStatusCode() != FieldState.StatusCode.EMPTY) {                       //aren't EMPTY
            return true;
        } else if (allFields.get(2).getState().getStatusCode() == allFields.get(4).getState().getStatusCode() &&    //A == B == C and...
                allFields.get(4).getState().getStatusCode() == allFields.get(6).getState().getStatusCode() &&
                allFields.get(4).getState().getStatusCode() != FieldState.StatusCode.EMPTY) {                       //aren't EMPTY
            return true;
        }
        return false;
    }


    /********************************** Draw conditions *****************************************/
    // - all fields taken: NOT StatusCode.EMPTY
    //  Note: if at least one field is EMPTY and the game hasn't been won yet, then the game continues.

    @Override
    protected boolean isDraw(ObservableList<BoardFieldPM> allFields){
        //Are all fields occupied by a player?
        return allFields.stream().noneMatch(boardFieldPM -> boardFieldPM.getState().getStatusCode() == FieldState.StatusCode.EMPTY);

        //Todo: add more draw conditions.

    }


    /*********************** getters and setters ******************************/
    @Override
    public GameStatePM getCurrentState() {
        return super.getCurrentState();
    }

}
