package tictactoe.view.components;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.BoardFieldPM;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class BoardField extends StackPane implements ViewMixin {

    private final RootPM pm;

    private int id;
    private SVGPath stateIcon;



    public BoardField(RootPM pm, int id){
        this.pm = pm;
        this.id = id;
        init();
    }

    @Override
    public void initializeSelf() {
        setPrefSize(100, 100);

        getStyleClass().addAll("board-field");
    }

    @Override
    public void initializeParts() {
        stateIcon = new SVGPath();
    }

    @Override
    public void layoutParts() {
        this.getChildren().addAll(stateIcon);
    }

    @Override
    public void addEventHandlers() {
        this.setOnMousePressed(event -> {
            SVGPath desiredIconAsSVG = pm.updateGame(id);
            stateIcon.setContent(desiredIconAsSVG.getContent());

            System.out.println(pm.getCurrentPlayerId());    //Todo: remove
        });
    }

    @Override
    public void setupBindings() {
        //resizing of SVGPaths
        stateIcon.scaleXProperty().bind(widthProperty().multiply(0.025));
        stateIcon.scaleYProperty().bind(heightProperty().multiply(0.025));

        BoardFieldPM boardFieldPM = pm.findFieldBy(id);
        disableProperty().bind(boardFieldPM.disableProperty());
    }


    /****************************** setters and getters **********************************/

    public SVGPath getStateIcon() {
        return stateIcon;
    }

    public void setStateIcon(SVGPath stateIcon) {
        this.stateIcon = stateIcon;
    }

}
