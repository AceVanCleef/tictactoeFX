package tictactoe.presentationmodel.gamerules.impl;

import javafx.collections.ObservableList;
import tictactoe.presentationmodel.BoardFieldPM;
import tictactoe.presentationmodel.gamerules.GameRules;
import tictactoe.presentationmodel.states.GameStatePM;

/**
 * Created by Degonas on 23.07.2017.
 */
public class Rules_2D_3x3 extends GameRules {

    public Rules_2D_3x3(GameStatePM gameState) {
        super(gameState);
    }

    @Override
    public void updateGameState(ObservableList<BoardFieldPM> allFields) {
        GameStatePM currentState = getCurrentState();

        if( isWon(allFields) ){
            currentState.setWon(true);
        } else if( isDraw(allFields) ) {
            currentState.setDraw(true);
        }
    }

    /************************** win conditions **************************************/

    private boolean isWon(ObservableList<BoardFieldPM> allFields){
        if ( isWonVertically(allFields) )   return true;
        if ( isWonHorizontally(allFields) ) return true;
        if ( isWonDiagonally(allFields) )   return true;
        return false;
    }

    private boolean isWonVertically(ObservableList<BoardFieldPM> allFields){
        for (int i = 0; i < 3; ++i) {
            BoardFieldPM a = allFields.get(i);
            BoardFieldPM b = allFields.get(i + 3);
            BoardFieldPM c = allFields.get(i + 6);
            if (a.getState().getStatusCode() == b.getState().getStatusCode() &&
                    b.getState().getStatusCode() == c.getState().getStatusCode() ){
                return true;
            }
        }
    }

    private boolean isWonHorizontally(ObservableList<BoardFieldPM> allFields) {
        for (int i = 0; i < allFields.size(); i = i + 3) {
            BoardFieldPM a = allFields.get(i);
            BoardFieldPM b = allFields.get(i + 1);
            BoardFieldPM c = allFields.get(i + 2);
            if (a.getState().getStatusCode() == b.getState().getStatusCode() &&
                    b.getState().getStatusCode() == c.getState().getStatusCode() ){
                return true;
            }
        }
    }

    private boolean isWonDiagonally(ObservableList<BoardFieldPM> allFields) {
        if (allFields.get(0).getState().getStatusCode() == allFields.get(4).getState().getStatusCode() &&
                allFields.get(4).getState().getStatusCode() == allFields.get(8).getState().getStatusCode()) {
            return true;
        } else if (allFields.get(2).getState().getStatusCode() == allFields.get(4).getState().getStatusCode() &&
                allFields.get(4).getState().getStatusCode() == allFields.get(6).getState().getStatusCode()) {
            return true;
        }
    }


    /********************************** Draw conditions *****************************************/

        private boolean isDraw(ObservableList<BoardFieldPM> allFields){

        }

    /*********************** getters and setters ******************************/
    @Override
    public GameStatePM getCurrentState() {
        return super.getCurrentState();
    }

}
