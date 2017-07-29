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
        gameState.reset();


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
        gameState.reset();


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
        gameState.reset();


        //given
        /* [Complete row, but not matching]
               x|_|_
               o|_|_
               x| |
         */
        allFields.get(6).setState(state1);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());
        gameState.reset();


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
        gameState.reset();



        //given
        /* [Horizontal win condition]
               x|x|_
               o|o|o
               x|x|
         */
        allFields.get(3).setState(state2);
        allFields.get(4).setState(state2);
        allFields.get(5).setState(state2);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();




        /* [Diagonal win condition version 01]
               o|_|_
               _|o|_
                | |o
         */
        allFields.clear();
        for (int i = 0; i < 9; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(0).setState(state2);
        allFields.get(4).setState(state2);
        allFields.get(8).setState(state2);


        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();



        /* [Diagonal win condition version 02]
               _|_|x
               _|x|_
               x| |
         */
        allFields.clear();
        for (int i = 0; i < 9; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(2).setState(state1);
        allFields.get(4).setState(state1);
        allFields.get(6).setState(state1);


        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();


        /* [Draw condition version 01: all fields are taken]
               x|o|x
               o|o|x
               x|x|o
         */
        allFields.clear();
        for (int i = 0; i < 9; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(0).setState(state1);
        allFields.get(1).setState(state2);
        allFields.get(2).setState(state1);

        allFields.get(3).setState(state2);
        allFields.get(4).setState(state2);
        allFields.get(5).setState(state1);

        allFields.get(6).setState(state1);
        allFields.get(7).setState(state1);
        allFields.get(8).setState(state2);



        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(true, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();
    }

    @Test
    public void testRules_2D_4x4(){
        //given
        /* [When new game starts]
               _|_|_|_
               _|_|_|_
               _|_|_|_
                | | |
         */
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        rules = GameRules.getGameRulesFor(GameRules.RuleSet._2D_4X4, gameState);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());
        gameState.reset();


        //given
        /* [One field taken]
               x|_|_|_
               _|_|_|_
               _|_|_|_
                | | |
         */
        allFields.get(0).setState(state1);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());
        gameState.reset();


        //given
        /* [One field taken by Player 2]
               x|_|_|_
               o|_|_|_
               _|_|_|_
                | | |
         */
        allFields.get(4).setState(state2);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());
        gameState.reset();


        //given
        /* [Complete row, but not matching]
               x|_|_|_
               o|_|_|_
               x|_|_|_
               o| | |
         */
        allFields.get(8).setState(state1);
        allFields.get(12).setState(state2);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());
        gameState.reset();


        //given
        /* [Vertical win condition]
               x|_|_|_
               x|_|_|_
               x|_|_|_
               x| | |
         */
        allFields.get(0).setState(state1);
        allFields.get(4).setState(state1);
        allFields.get(8).setState(state1);
        allFields.get(12).setState(state1);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();



        //given
        /* [Horizontal win condition]
               _|_|_|_
               o|o|o|o
               _|_|_|_
                | | |
         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(4).setState(state2);
        allFields.get(5).setState(state2);
        allFields.get(6).setState(state2);
        allFields.get(7).setState(state2);

        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();




        /* [Diagonal win condition version 01]
               x|_|_|_
               _|x|_|_
               _|_|x|_
                | | |x
         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(0).setState(state1);
        allFields.get(5).setState(state1);
        allFields.get(10).setState(state1);
        allFields.get(15).setState(state1);


        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();



        /* [Diagonal win condition version 02]
               _|_|_|o
               _|_|o|_
               _|o|_|_
               o| | |
         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(3).setState(state2);
        allFields.get(6).setState(state2);
        allFields.get(9).setState(state2);
        allFields.get(12).setState(state2);


        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();


        /* [Diamond win condition version 01]
               _|x|_|_
               x|_|x|_
               _|x|_|_
                | | |
         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(1).setState(state1);
        allFields.get(4).setState(state1);
        allFields.get(6).setState(state1);
        allFields.get(9).setState(state1);


        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();


        /* [Diamond win condition version 02]
               _|_|x|_
               _|x|_|x
               _|_|x|_
                | | |
         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(2).setState(state1);
        allFields.get(5).setState(state1);
        allFields.get(7).setState(state1);
        allFields.get(10).setState(state1);


        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();

        /* [Diamond win condition version 03]
               _|_|_|_
               _|_|x|_
               _|x|_|x
                | |x|

         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(6).setState(state1);
        allFields.get(9).setState(state1);
        allFields.get(11).setState(state1);
        allFields.get(14).setState(state1);

        /* [Diamond win condition version 03]
               _|_|_|_
               _|o|_|_
               o|_|o|_
                |o| |

         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(5).setState(state2);
        allFields.get(8).setState(state2);
        allFields.get(10).setState(state2);
        allFields.get(13).setState(state2);


        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(true, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();

        /* [Diamond win condition NOT fulfilled]
               _|_|_|_
               _|o|_|_
               o|_|x|_
                |o| |

         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(5).setState(state2);
        allFields.get(8).setState(state2);
        allFields.get(10).setState(state1);
        allFields.get(13).setState(state2);


        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(false, gameState.isDraw());
        assertEquals(true, gameState.doesGameContinue());
        gameState.reset();


        /* [Draw condition version 01: all fields are taken]
               x|o|x|x
               o|o|x|x
               x|o|x|x
               x|x|o|o
         */
        allFields.clear();
        for (int i = 0; i < 16; ++i) {
            allFields.add(new BoardFieldPM(i));
        }
        allFields.get(0).setState(state1);
        allFields.get(1).setState(state2);
        allFields.get(2).setState(state1);
        allFields.get(3).setState(state1);

        allFields.get(4).setState(state2);
        allFields.get(5).setState(state2);
        allFields.get(6).setState(state1);
        allFields.get(7).setState(state1);

        allFields.get(8).setState(state1);
        allFields.get(9).setState(state2);
        allFields.get(10).setState(state2);
        allFields.get(11).setState(state2);

        allFields.get(12).setState(state1);
        allFields.get(13).setState(state1);
        allFields.get(14).setState(state2);
        allFields.get(15).setState(state2);



        //when
        rules.updateGameState(allFields);

        //then
        assertEquals(false, gameState.isWon());
        assertEquals(true, gameState.isDraw());
        assertEquals(false, gameState.doesGameContinue());
        gameState.reset();
    }
}
