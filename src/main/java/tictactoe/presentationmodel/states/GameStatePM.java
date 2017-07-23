package tictactoe.presentationmodel.states;

/**
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

    public boolean doesGameContinue(){
        //return isWon == false && isDraw == false;
        return !isWon() && !isDraw();
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }
}
