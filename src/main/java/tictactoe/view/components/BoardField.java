package tictactoe.view.components;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import tictactoe.presentationmodel.FieldState;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.util.ViewMixin;

/**
 * Created by degonas on 17.07.2017.
 */
public class BoardField extends StackPane implements ViewMixin {

    private final RootPM pm;

    private SVGPath cross;
    private SVGPath circle;

    //Todo: wie GUI BoardField mit BoardFieldPM verknüpfen? StateBdinging vs ID auch im GUI-Feld?
    private final ObjectProperty<FieldState> state = new SimpleObjectProperty<>();


    public BoardField(RootPM pm){
        this.pm = pm;
        init();
    }

    @Override
    public void initializeSelf() {
        setPrefSize(100, 100);

        getStyleClass().addAll("board-field");
    }

    @Override
    public void initializeParts() {
        cross = new SVGPath();
        cross.setContent("M22.245,4.015c0.313,0.313,0.313,0.826,0,1.139l-6.276,6.27c-0.313,0.312-0.313,0.826,0,1.14l6.273,6.272  c0.313,0.313,0.313,0.826,0,1.14l-2.285,2.277c-0.314,0.312-0.828,0.312-1.142,0l-6.271-6.271c-0.313-0.313-0.828-0.313-1.141,0  l-6.276,6.267c-0.313,0.313-0.828,0.313-1.141,0l-2.282-2.28c-0.313-0.313-0.313-0.826,0-1.14l6.278-6.269  c0.313-0.312,0.313-0.826,0-1.14L1.709,5.147c-0.314-0.313-0.314-0.827,0-1.14l2.284-2.278C4.308,1.417,4.821,1.417,5.135,1.73  L11.405,8c0.314,0.314,0.828,0.314,1.141,0.001l6.276-6.267c0.312-0.312,0.826-0.312,1.141,0L22.245,4.015z");

        circle = new SVGPath();
        circle.setContent("M10,0 C4.5,0 0,4.5 0,10 C0,15.5 4.5,20 10,20 C15.5,20 20,15.5 20,10 C20,4.5 15.5,0 10,0 L10,0 Z M10,18 C5.6,18 2,14.4 2,10 C2,5.6 5.6,2 10,2 C14.4,2 18,5.6 18,10 C18,14.4 14.4,18 10,18 L10,18 Z");
    }

    @Override
    public void layoutParts() {
        this.getChildren().addAll(cross, circle);
    }

    @Override
    public void addEventHandlers() {
        this.setOnMousePressed(event -> {
            pm.nextPlayer();
            System.out.println(pm.getCurrentPlayerId());
        });
    }

    @Override
    public void setupBindings() {
        //resizing of SVGPaths
        cross.scaleXProperty().bind(this.widthProperty().multiply(0.025));
        cross.scaleYProperty().bind(this.heightProperty().multiply(0.025));
        circle.scaleXProperty().bind(this.widthProperty().multiply(0.025));
        circle.scaleYProperty().bind(this.heightProperty().multiply(0.025));
    }
}
