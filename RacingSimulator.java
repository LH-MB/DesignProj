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
    double blueTime;
    double redTime;
    double whiteTime;
    double purpleTime;
    
    Checkpoint[] checkpoints;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

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
        String relativePath = "/RacingSimulator/images/";

        ImageView blueCar = setUpCar(relativePath + "blue_car.png", checkpointA);
        ImageView redCar = setUpCar(relativePath + "red_car.png", checkpointA);
        ImageView whiteCar = setUpCar(relativePath + "white_car.png", checkpointA);
        ImageView purpleCar = setUpCar(relativePath + "purple_car.png", checkpointA);

        RaceCar carBlue = new RaceCar();
        RaceCar carRed = new RaceCar();
        RaceCar carWhite = new RaceCar();
        RaceCar carPurple = new RaceCar();
        
        carBlue.randomizeValues();
        carRed.randomizeValues();
        carWhite.randomizeValues();
        carPurple.randomizeValues();
        
        //RACETRACK
        Image raceTrackImg = new Image(relativePath + "raceTrack.png");
        ImageView raceTrackView = new ImageView();
        raceTrackView.setImage(raceTrackImg);

        blueTime = (10 / (carBlue.getCalculatedSpeed()));
        redTime = (10 / (carRed.getCalculatedSpeed()));
        whiteTime = (10 / (carWhite.getCalculatedSpeed()));
        purpleTime = (10 / (carPurple.getCalculatedSpeed()));
                
        simulator(blueCar, 1, blueTime);
        simulator(redCar, 1, redTime);
        simulator(whiteCar, 1, whiteTime);
        simulator(purpleCar, 1, purpleTime);

        Pane trackPanel = new Pane();
        trackPanel.getChildren().addAll(raceTrackView, blueCar, redCar, whiteCar, purpleCar);

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

    private ImageView setUpCar(String carFilePath, Checkpoint checkpointA) {
        Image carImg = new Image(carFilePath);
        ImageView carView = new ImageView();
        carView.setImage(carImg);
        carView.setFitWidth(100);
        carView.setPreserveRatio(true);
        carView.setTranslateX(checkpointA.getXPos());
        carView.setTranslateY(checkpointA.getYPos());
        return carView;
    }

    public void simulator(ImageView car, int index, double time) {
        TranslateTransition transition = new TranslateTransition();
        transition.setToX(checkpoints[index].getXPos());
        transition.setToY(checkpoints[index].getYPos());
        transition.setDuration(Duration.seconds(time));
        transition.setDelay(Duration.seconds(.25));
        transition.setOnFinished(event -> {
            int i = index;
            car.setRotate(car.getRotate() + 90);
            if (i == 3) {
                i = 0;
            } else {
                i++;
            }
            simulator(car, i, time);
        });
        transition.setNode(car);
        transition.play();
    }
}
