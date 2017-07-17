package tictactoe.view;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class Header extends ToolBar implements ViewMixin{

    private final RootPM pm;

    private Button newGame;

    public Header(RootPM pm){
        this.pm = pm;
        init();
    }

    @Override
    public void initializeParts() {
        newGame = new Button("New Game");
    }

    @Override
    public void layoutParts() {
        this.getItems().addAll(newGame, new Button("test"));
    }
}
