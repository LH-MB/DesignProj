package RacingSimulator;

/**
 * Louis Hwang, Nathan Moore, and Svet Draganitchki
 * CS 225 - Project 3
 */

import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

public class RacingSimulator extends Application {

    Checkpoint[] checkpoints;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        String relativePath = "/RacingSimulator/images/";

        //TITLE
        Text title = new Text("Racing Simulator");
        title.getStyleClass().add("title");

        //CHECKPOINTS
        checkpoints = new Checkpoint[4];
        Checkpoint checkpointA = new Checkpoint(-10, 15);
        checkpoints[0] = checkpointA;
        Checkpoint checkpointB = new Checkpoint(410, 15);
        checkpoints[1] = checkpointB;
        Checkpoint checkpointC = new Checkpoint(410, 435);
        checkpoints[2] = checkpointC;
        Checkpoint checkpointD = new Checkpoint(-10, 435);
        checkpoints[3] = checkpointD;

        //CARS
        Image blueCarImg = new Image(relativePath + "blue_car.png");
        ImageView blueCarView = new ImageView();
        blueCarView.setImage(blueCarImg);
        blueCarView.setFitWidth(100);
        blueCarView.setPreserveRatio(true);
        blueCarView.setTranslateX(checkpointA.getXPos());
        blueCarView.setTranslateY(checkpointA.getYPos());

        //RACETRACK
        Image raceTrackImg = new Image(relativePath + "raceTrack.png");
        ImageView raceTrackView = new ImageView();
        raceTrackView.setImage(raceTrackImg);

        TranslateTransition blueTransition = new TranslateTransition();
        simulator(blueCarView, 1);

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

    public void simulator(ImageView car, int index) {
        TranslateTransition transition = new TranslateTransition();
        transition.setToX(checkpoints[index].getXPos());
        transition.setToY(checkpoints[index].getYPos());
        transition.setDuration(Duration.seconds(3));
        transition.setOnFinished(event -> {
            int i = index;
            car.setRotate(car.getRotate() + 90);
            if (i == 3) {
                i = 0;
            } else {
                i++;
            }
            simulator(car, i);
        });
        transition.setNode(car);
        transition.play();
    }
}
