package tictactoe.view;

import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class Header extends ToolBar implements ViewMixin{

    private final RootPM pm;

    private Button newGame;

    private Label       amountOfFieldsLabel;
    private TextField   amountOfFieldsTF;

    private Label       amountOfPlayersLabel;
    private TextField   amountOfPlayersTF;

    private CheckBox    is3D;

    public Header(RootPM pm){
        this.pm = pm;
        init();
    }

    @Override
    public void initializeParts() {
        newGame = new Button("New Game");

        amountOfFieldsLabel = new Label("Amount of Fields:");
        amountOfFieldsTF = new TextField();

        amountOfPlayersLabel = new Label("Amount of Players");
        amountOfPlayersTF = new TextField();

        is3D = new CheckBox("3D");
        is3D.setSelected(false);        //Todo: remove when 3D is available
        is3D.setDisable(true);
    }

    @Override
    public void layoutParts() {
        this.getItems().addAll(newGame,
                amountOfFieldsLabel,
                amountOfFieldsTF,
                amountOfPlayersLabel,
                amountOfPlayersTF,
                is3D);
    }

    @Override
    public void addEventHandlers() {
        newGame.setOnAction(event -> {
            int fieldCount = Integer.parseInt( amountOfFieldsTF.getText() );
            int playerCount = Integer.parseInt( amountOfPlayersTF.getText() );
            pm.newGame(fieldCount, playerCount);
        });
    }

    @Override
    public void setupBindings() {
        amountOfFieldsTF.textProperty().bindBidirectional(pm.newAmountOfFieldsProperty(), new NumberStringConverter());
        amountOfPlayersTF.textProperty().bindBidirectional(pm.newAmountOfPlayersProperty(), new NumberStringConverter());
    }
}
