package tictactoe.presentationmodel.states;

/**
 * - represents the state of the currently played round: Won, draw or continuing?
 * - offers query methods to check the current state ( isWon(), isDraw(), doesGameContinue() ).
 *
 * Created by Degonas on 23.07.2017.
 */
public class GameStatePM {

    private boolean isWon;
    private boolean isDraw;
    /* Note: if both equal false, then game continues */

    public GameStatePM(){
        setWon(false);
        setDraw(false);
    }

    /**
     * resets the GameStatePM to its initiale state.
     *
     * Use Cases:
     * - New Game: a new round of TicTacToe has been started.
     */
    public void reset(){
        setWon(false);
        setDraw(false);
    }


    /********************* Query Methods *************************/

    public boolean doesGameContinue(){
        //return isWon == false && isDraw == false;
        return !isWon() && !isDraw();
    }

    public boolean isWon() {
        return isWon;
    }

    public boolean isDraw() {
        return isDraw;
    }


    /********************* setters *************************/

    public void setWon(boolean won) {
        isWon = won;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
}
