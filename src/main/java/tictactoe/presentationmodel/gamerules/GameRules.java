package tictactoe.presentationmodel.gamerules;

import javafx.collections.ObservableList;
import tictactoe.presentationmodel.BoardFieldPM;
import tictactoe.presentationmodel.gamerules.impl.Rules_2D_3x3;
import tictactoe.presentationmodel.states.GameStatePM;

/**
 * offers GameRules for each type of RuleSet. Each RuleSet holds suitable win and draw conditions.
 *
 * Created by Degonas on 23.07.2017.
 */
public abstract class GameRules {


    /* the type of game which players are currently playing */
    public enum RuleSet{
        _2D_3X3, _2D_4X4, _2d_5x5
    }

    private GameStatePM currentState;

    //initializes fields of this abstract class for its subclasses.
    protected GameRules(GameStatePM gameState) {
        currentState = gameState;   //needed for "super(gamestate)" in subclass' constructor.
    }

    //factory method
    public static GameRules getGameRulesFor(RuleSet gametype, GameStatePM gameState){
        if(gametype == RuleSet._2D_3X3) {
            return new Rules_2D_3x3(gameState);
        }

        return null;
    }

    /**
     * checks allFields against all possible win and draw conditions and updates GameStatePM currentState.
     * @param allFields
     */
    public void updateGameState(ObservableList<BoardFieldPM> allFields){
        GameStatePM currentState = getCurrentState();

        // Respect the order: 1st win conditions, 2nd draw conditions
        if( isWon(allFields) ){
            currentState.setWon(true);
        } else if( isDraw(allFields) ) {
            currentState.setDraw(true);
        }
    }

    protected abstract boolean isWon(ObservableList<BoardFieldPM> allFields);

    protected abstract boolean isDraw(ObservableList<BoardFieldPM> allFields);

    /*********************** getters and setters ******************************/
    public GameStatePM getCurrentState() {
        return currentState;
    }

}
