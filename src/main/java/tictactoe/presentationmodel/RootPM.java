package tictactoe.presentationmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by degonas on 17.07.2017.
 */
public class RootPM {


    /* the maximum amount of fields this gameboard has. */
    public final static int AMOUNT_OF_FIELDS = 9;

    private ObservableList<BoardFieldPM> allFields = FXCollections.observableArrayList();

    public RootPM(){

    }

}
