package RacingSimulator;

/**
 * Louis Hwang, Nathan Moore, and Svet Draganitchki
 * CS 225 - Project 3
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RacingSimulator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //TITLE
        Text title = new Text("Racing Simulator");

        //LEFT SIDE
        VBox leftSide = new VBox();
        leftSide.getChildren().addAll(title);

        //MAIN LAYOUT
        HBox mainLayout = new HBox();
        mainLayout.getChildren().addAll(leftSide);

        //SCENE AND STAGE
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Racing Simulator");
        primaryStage.show();
    }
}
