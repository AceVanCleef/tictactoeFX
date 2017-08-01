package tictactoe.presentationmodel;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by degonas on 18.07.2017.
 */
public class RootPMTest {

    private RootPM pm;

    @Before
    public void setUp(){
        pm = new RootPM(9, 2);
    }

    @Test
    public void testSetUp(){
        //given
        //pm = new RootPM(9, 2);; //see setUp()

        //when

        //then
        assertEquals(pm.getAmountOfFields(), pm.getAllFields().size());
        assertEquals(pm.getAmountOfPlayers(), pm.getAllPlayers().size());

        //given

        //when
        /* the initial player's ID */
        int currentPlayerId = pm.getCurrentPlayerId();

        //then
        assertEquals(pm.getAllPlayers().get(0).getId(), currentPlayerId);
    }

    @Test
    public void testNewGame(){
        /* original Amount of fields = 9 and original amount of players = 2 */
        //given
        //pm = new RootPM(9, 2);; //original game board [see setUp()]

        //when
        pm.newGame(16, 3);

        //then
        assertEquals(pm.getAmountOfFields(), pm.getAllFields().size());
        assertEquals(pm.getAmountOfPlayers(), pm.getAllPlayers().size());

        //given

        //when
        /* the initial player's ID */
        int currentPlayerId = pm.getCurrentPlayerId();

        //then
        assertEquals(pm.getAllPlayers().get(0).getId(), currentPlayerId);



        /* testing score behavior: when newGame() and amountOfPlayers == allPlayers.size(), then the score shouldn't change */
        //given
        int player01OriginalScore = pm.getAllPlayers().get(0).getScore();
        int originalAmountOfDraws = pm.getDrawCount();
        pm.newGame(25, 4);

        //when
        pm.getAllPlayers().get(0).setScore(3);  //three times won
        int player01NewScore = pm.getAllPlayers().get(0).getScore();
        pm.setDrawCount(2);
        int newlAmountOfDraws = pm.getDrawCount();
        pm.newGame(9, 4); //amountOfPlayers stays the same

        //then
        assertEquals(4, pm.getAllPlayers().size());
        assertEquals(0, player01OriginalScore);
        assertEquals(3, player01NewScore);
        assertEquals(player01NewScore, pm.getAllPlayers().get(0).getScore());
        assertNotEquals(player01OriginalScore, pm.getAllPlayers().get(0).getScore());
        //testing draw count's value
        assertNotEquals(originalAmountOfDraws, pm.getDrawCount());
        assertEquals(newlAmountOfDraws, pm.getDrawCount());

    }

    @Test
    public void testUpdateGameBy(){
        //Todo: test .update() using mockito (stub testing)
        fail();
    }

//    @Test
//    public void testNextPlayer(){
//        /* from player01 to player02 */
//        //given
//        //pm = new RootPM(); //see setUp()
//        int previousPlayerId = pm.getCurrentPlayerId();
//
//        //when
//        pm.nextPlayer();
//
//        //then
//        int newCurrentPlayerId = pm.getCurrentPlayerId();
//        assertNotEquals(newCurrentPlayerId, previousPlayerId);
//        assertEquals(2, newCurrentPlayerId);
//        assertEquals(previousPlayerId + 1, newCurrentPlayerId);
//
//        /* from player02 to player01 */
//        //given
//        previousPlayerId = pm.getCurrentPlayerId();
//
//        //when
//        pm.nextPlayer();
//
//        //then
//        newCurrentPlayerId = pm.getCurrentPlayerId();
//        assertNotEquals(newCurrentPlayerId, previousPlayerId);
//        assertEquals(1, newCurrentPlayerId);
//        assertEquals(previousPlayerId - 1, newCurrentPlayerId);
//
//    }
}
