package tictactoe.presentationmodel.gamerules;

import javafx.collections.ObservableList;
import tictactoe.presentationmodel.BoardFieldPM;
import tictactoe.presentationmodel.gamerules.impl.Rules_2D_3x3;
import tictactoe.presentationmodel.states.GameStatePM;

/**
 * Created by Degonas on 23.07.2017.
 */
public abstract class GameRules {



    public enum RuleSet{
        _2D_3X3, _2D_4X4, _2d_5x5
    }

    private GameStatePM currentState;

    //initializes fields of this abstract class for its subclasses.
    protected GameRules(GameStatePM gameState) {
        currentState = gameState;   //needed for "super(gamestate)" in subclass' constructor.
    }

    public static GameRules getGameRulesFor(RuleSet gametype, GameStatePM gameState){
        if(gametype == RuleSet._2D_3X3) {
            return new Rules_2D_3x3(gameState);
        }

        return null;
    }

    public abstract void updateGameState(ObservableList<BoardFieldPM> allFields);

    /*********************** getters and setters ******************************/
    public GameStatePM getCurrentState() {
        return currentState;
    }

}
