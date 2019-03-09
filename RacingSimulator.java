package RacingSimulator;

/**
 * Louis Hwang, Nathan Moore, and Svet Draganitchki
 * CS 225 - Project 3
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
        title.getStyleClass().add("title");

        //CHECKPOINTS
        Checkpoint checkpointA = new Checkpoint(15, 15);

        //CARS
        Image blueCarImg = new Image("/RacingSimulator/blue_car.png");
        ImageView blueCarView = new ImageView();
        blueCarView.setImage(blueCarImg);
        blueCarView.setFitWidth(100);
        blueCarView.setPreserveRatio(true);
        blueCarView.setTranslateX(checkpointA.getXPos());
        blueCarView.setTranslateY(checkpointA.getYPos());

        //RACETRACK
        Image raceTrackImg = new Image("/RacingSimulator/raceTrack.png");
        ImageView raceTrackView = new ImageView();
        raceTrackView.setImage(raceTrackImg);

        Pane trackPanel = new Pane();
        trackPanel.getChildren().addAll(raceTrackView, blueCarView);

        //LEFT SIDE
        VBox leftSide = new VBox();
        leftSide.getChildren().addAll(title, trackPanel);
        leftSide.setAlignment(Pos.CENTER);

        //MAIN LAYOUT
        HBox mainLayout = new HBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(leftSide);

        //SCENE AND STAGE
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Racing Simulator");
        primaryStage.show();
    }
}
