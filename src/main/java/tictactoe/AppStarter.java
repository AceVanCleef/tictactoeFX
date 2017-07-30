package tictactoe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.presentationmodel.RootPM;
import tictactoe.view.RootPane;

/**
 * Created by Degonas on 28.06.2017.
 */
public class AppStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        RootPM pm = new RootPM(9, 4);       //RootPM

        Parent rootPanel = new RootPane(pm);

        Scene scene = new Scene(rootPanel);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);

        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
