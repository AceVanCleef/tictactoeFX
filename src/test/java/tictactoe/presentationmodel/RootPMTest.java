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
        pm = new RootPM();
    }

    @Test
    public void testSetUp(){
        //given
        //pm = new RootPM(); //see setUp()

        //when

        //then
        assertEquals(pm.AMOUNT_OF_FIELDS, pm.getAllFields().size());
        assertEquals(pm.AMOUNT_OF_PLAYERS, pm.getAllPlayers().size());

        //given

        //when
        /* the initial player's ID */
        int currentPlayerId = pm.getCurrentPlayerId();

        //then
        assertEquals(pm.getAllPlayers().get(0).getId(), currentPlayerId);
    }

    @Test
    public void testNextPlayer(){
        /* from player01 to player02 */
        //given
        //pm = new RootPM(); //see setUp()
        int previousPlayerId = pm.getCurrentPlayerId();

        //when
        pm.nextPlayer();

        //then
        int newCurrentPlayerId = pm.getCurrentPlayerId();
        assertNotEquals(newCurrentPlayerId, previousPlayerId);
        assertEquals(2, newCurrentPlayerId);
        assertEquals(previousPlayerId + 1, newCurrentPlayerId);

        /* from player02 to player01 */
        //given
        previousPlayerId = pm.getCurrentPlayerId();

        //when
        pm.nextPlayer();

        //then
        newCurrentPlayerId = pm.getCurrentPlayerId();
        assertNotEquals(newCurrentPlayerId, previousPlayerId);
        assertEquals(1, newCurrentPlayerId);
        assertEquals(previousPlayerId - 1, newCurrentPlayerId);

    }
}
