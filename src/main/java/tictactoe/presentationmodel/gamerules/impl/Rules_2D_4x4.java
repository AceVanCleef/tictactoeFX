package tictactoe.presentationmodel.gamerules.impl;

import javafx.collections.ObservableList;
import tictactoe.presentationmodel.BoardFieldPM;
import tictactoe.presentationmodel.gamerules.GameRules;
import tictactoe.presentationmodel.states.FieldState;
import tictactoe.presentationmodel.states.GameStatePM;

/**
 * Created by Degonas on 29.07.2017.
 */
public class Rules_2D_4x4 extends GameRules {

    /*
        GameBoard indexes:
            00|01|02|03
            -----------
            04|05|06|07
            -----------
            08|09|10|11
            -----------
            12|13|14|15
        * */

    public Rules_2D_4x4(GameStatePM gameState) {
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
        if ( isWonByDiamond(allFields) )   return true;
        return false;
    }

    private boolean isNewGame(ObservableList<BoardFieldPM> allFields) {
        return allFields.stream().allMatch(boardFieldPM -> boardFieldPM.getState().getStatusCode() == FieldState.StatusCode.EMPTY);
    }

    private boolean isWonVertically(ObservableList<BoardFieldPM> allFields){
        /* Indexes:
            00|__|__|__
            04|__|__|__
            08|__|__|__
            12|  |  |
            Move to right: +1
         */
        for (int i = 0; i < 4; ++i) {
            if (compareFields(allFields, i, i + 4, i + 8, i + 12)){
                return true;
            }
        }

        return false;
    }

    private boolean isWonHorizontally(ObservableList<BoardFieldPM> allFields) {
        /* Indexes:
            00|01|02|03
            __|__|__|__
            __|__|__|__
              |  |  |
            Move one row down: +4
         */
        for (int i = 0; i < allFields.size(); i = i + 4) {
            if ( compareFields(allFields, i, i + 1, i + 2, i + 3) ){
                return true;
            }
        }

        return false;
    }

    private boolean isWonDiagonally(ObservableList<BoardFieldPM> allFields) {
        /*
        GameBoard (sketch):
            00|01|02|03
            -----------
            04|05|06|07
            -----------
            08|09|10|11
            -----------
            12|13|14|15
        * */
        if ( compareFields(allFields, 0, 5, 10, 15) ){
            return true;
        } else if ( compareFields(allFields, 3, 6, 9, 12) ){
            return true;
        }

        return false;
    }

    private boolean isWonByDiamond(ObservableList<BoardFieldPM> allFields){
        /* Four diamonds possible:          A Diamond looks like:
            1) 01, 04, 09, 06               _|x|_|_
            2) 02, 05, 10, 07               x|_|x|_
            3) 06, 09, 14, 11               _|x|_|_
            4) 05, 08, 13, 10               _|_|_|_
        **/
        if ( compareFields(allFields, 1, 4, 9,6)) {
            return true;
        } else if ( compareFields(allFields, 2, 5, 10,7)) {
            return true;
        } else if ( compareFields(allFields, 6, 9, 14,11)) {
            return true;
        } else if ( compareFields(allFields, 5, 8, 13,10)) {
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
     * @param m index of 4th BoardFieldPM
     * @return boolean - have same FieldState.StatusCode or not.
     */
    private boolean compareFields(ObservableList<BoardFieldPM> allFields, int i, int j, int k, int m){
        return allFields.get(i).getState().getStatusCode() == allFields.get(j).getState().getStatusCode() &&           //A == B == C == D and...
                allFields.get(j).getState().getStatusCode() == allFields.get(k).getState().getStatusCode() &&
                allFields.get(k).getState().getStatusCode() == allFields.get(m).getState().getStatusCode() &&
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
