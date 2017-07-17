package tictactoe.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class PlayerOverview extends VBox implements ViewMixin {

    private final RootPM pm;
    private final String initialName;

    private Label playerName;
    private Button surrender;

    public PlayerOverview(RootPM pm, String playerName){
        this.pm = pm;
        initialName = playerName;

        init();
    }

    @Override
    public void initializeParts() {
        playerName = new Label(initialName);
        surrender = new Button("Surrender"); //Aufgeben
    }

    @Override
    public void layoutParts() {
        this.getChildren().addAll(playerName, surrender);
    }

}
