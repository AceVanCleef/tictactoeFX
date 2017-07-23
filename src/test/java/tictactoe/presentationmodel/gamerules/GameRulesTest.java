package tictactoe.presentationmodel.gamerules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.*;
import tictactoe.presentationmodel.BoardFieldPM;
import tictactoe.presentationmodel.states.FieldState;
import tictactoe.presentationmodel.states.GameStatePM;

import static org.junit.Assert.*;

/**
 * Created by Degonas on 23.07.2017.
 */
public class GameRulesTest {

    private final ObservableList<BoardFieldPM> allFields = FXCollections.observableArrayList();

    private GameStatePM gameState;
    GameRules rules;

    //FieldStates
    FieldState state1 = FieldState.getStateByPlayerId( 1 );
    FieldState state2 = FieldState.getStateByPlayerId( 2 );

    @Before
    public void setUp(){
        gameState = new GameStatePM();
    }

    //given

    //when

    //then


    @Test
    public void testRules_2D_3x3(){
        //given
        /* [When new game starts]
               _|_|_
               _|_|_
                | |
         */
        for (int i = 0; i < 9; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        rules = GameRules.getGameRulesFor(GameRules.RuleSet._2D_3X3, gameState);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());



        //given
        /* [One field taken]
               x|_|_
               _|_|_
                | |
         */
        allFields.get(0).setState(state1);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());



        //given
        /* [One field taken by Player 2]
               x|_|_
               o|_|_
                | |
         */
        allFields.get(3).setState(state2);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());



        //given
        /* [Complete row, but not matching]
               x|_|_
               o|_|_
               x| |
         */
        allFields.get(3).setState(state1);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());



        //given
        /* [Vertical win condition]
               x|x|_
               o|x|_
               x|x|
         */
        allFields.get(1).setState(state1);
        allFields.get(4).setState(state1);
        allFields.get(7).setState(state1);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
    }
}
