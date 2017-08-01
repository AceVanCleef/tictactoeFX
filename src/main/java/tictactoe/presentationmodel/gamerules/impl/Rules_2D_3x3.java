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

    @Override
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
//        for (int i = 0; i < 3; ++i) {
//            BoardFieldPM a = allFields.get(i);
//            BoardFieldPM b = allFields.get(i + 3);
//            BoardFieldPM c = allFields.get(i + 6);
//            if (a.getState().getStatusCode() == b.getState().getStatusCode() &&         //A == B == C and...
//                    b.getState().getStatusCode() == c.getState().getStatusCode() &&
//                    b.getState().getStatusCode() != FieldState.StatusCode.EMPTY) {      //aren't EMPTY
//                return true;
//            }
//        }

        for (int i = 0; i < 3; ++i) {
            if ( compareFields(allFields, i,i + 3, i + 6) ){
                return true;
            }
        }
        return false;
    }

    private boolean isWonHorizontally(ObservableList<BoardFieldPM> allFields) {
//        for (int i = 0; i < allFields.size(); i = i + 3) {
//            BoardFieldPM a = allFields.get(i);
//            BoardFieldPM b = allFields.get(i + 1);
//            BoardFieldPM c = allFields.get(i + 2);
//            if (a.getState().getStatusCode() == b.getState().getStatusCode() &&         //A == B == C and...
//                    b.getState().getStatusCode() == c.getState().getStatusCode() &&
//                    b.getState().getStatusCode() != FieldState.StatusCode.EMPTY) {      //aren't EMPTY
//                return true;
//            }
//        }

        for (int i = 0; i < allFields.size(); i = i + 3) {
            if ( compareFields(allFields, i, i + 1, i + 2) ){
                return true;
            }
        }

            return false;
    }

    private boolean isWonDiagonally(ObservableList<BoardFieldPM> allFields) {
//        if (allFields.get(0).getState().getStatusCode() == allFields.get(4).getState().getStatusCode() &&           //A == B == C and...
//                allFields.get(4).getState().getStatusCode() == allFields.get(8).getState().getStatusCode() &&
//                allFields.get(4).getState().getStatusCode() != FieldState.StatusCode.EMPTY) {                       //aren't EMPTY
//            return true;
//        } else if (allFields.get(2).getState().getStatusCode() == allFields.get(4).getState().getStatusCode() &&    //A == B == C and...
//                allFields.get(4).getState().getStatusCode() == allFields.get(6).getState().getStatusCode() &&
//                allFields.get(4).getState().getStatusCode() != FieldState.StatusCode.EMPTY) {                       //aren't EMPTY
//            return true;
//        }

        if ( compareFields(allFields,0,4,8) ){
            return true;
        } else if ( compareFields(allFields, 2,4,6) ){
            return true;
        }

        return false;
    }

    /********************************** Helper Methods *****************************************/


    /**
     * checks whether all four fields (indexes i, j, k and m) are taken by the same player.
     * @param allFields represents the whole GameBoard (or rather all Fields of GameBoard)
     * @param i index of 1st BoardFieldPM
     * @param j index of 2nd BoardFieldPM
     * @param k index of 3rd BoardFieldPM
     * @return boolean - have same FieldState.StatusCode or not.
     */
    private boolean compareFields(ObservableList<BoardFieldPM> allFields, int i, int j, int k){
        return allFields.get(i).getState().getStatusCode() == allFields.get(j).getState().getStatusCode() &&           //A == B == C and...
                allFields.get(j).getState().getStatusCode() == allFields.get(k).getState().getStatusCode() &&
                allFields.get(j).getState().getStatusCode() != FieldState.StatusCode.EMPTY;                            //aren't EMPTY
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
