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
    double start;
    
    Checkpoint[] checkpoints;
    Checkpoint[][] allCheckpoints;
    int[] checkpointsEncountered;

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
        Checkpoint checkpointA = new Checkpoint(-10, 15, 0);
        checkpoints[0] = checkpointA;
        Checkpoint checkpointB = new Checkpoint(410, 15, 90);
        checkpoints[1] = checkpointB;
        Checkpoint checkpointC = new Checkpoint(410, 435, 180);
        checkpoints[2] = checkpointC;
        Checkpoint checkpointD = new Checkpoint(-10, 435, 270);
        checkpoints[3] = checkpointD;

        allCheckpoints = new Checkpoint[4][4];
        
        //blue car path
        allCheckpoints[0][0] = checkpointA;
        allCheckpoints[0][1] = checkpointB;
        allCheckpoints[0][2] = checkpointC;
        allCheckpoints[0][3] = checkpointD;
        
        //red car path
        allCheckpoints[1][0] = checkpointA;
        allCheckpoints[1][1] = checkpointD;
        allCheckpoints[1][2] = checkpointC;
        allCheckpoints[1][3] = checkpointB;
        
        //white car path
        allCheckpoints[2][0] = checkpointA;
        allCheckpoints[2][1] = checkpointD;
        allCheckpoints[2][2] = checkpointC;
        allCheckpoints[2][3] = checkpointB;
        
        //purple car path
        allCheckpoints[3][0] = checkpointA;
        allCheckpoints[3][1] = checkpointB;
        allCheckpoints[3][2] = checkpointC;
        allCheckpoints[3][3] = checkpointD;
        
        //CHECKPOINTS ENCOUNTERED FOR EACH CAR
        checkpointsEncountered = new int[4];
        checkpointsEncountered[0] = 0;
        checkpointsEncountered[1] = 0;
        checkpointsEncountered[2] = 0;     
        checkpointsEncountered[3] = 0;
        
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
        
        carRed.setAngle(90);
        carWhite.setAngle(90);
        
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
                
        simulator(blueCar, 0, 1, blueTime, carBlue);
        simulator(redCar, 1, 1, redTime, carRed);
        simulator(whiteCar, 2, 1, whiteTime, carWhite);
        simulator(purpleCar, 3, 1, purpleTime, carPurple);
        
        start = System.currentTimeMillis();
        
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

    public void simulator(ImageView car, int carIndex, int index, double time, RaceCar raceCar) {
        TranslateTransition transition = new TranslateTransition();
        transition.setToX(allCheckpoints[carIndex][index].getXPos());
        transition.setToY(allCheckpoints[carIndex][index].getYPos());
        transition.setDuration(Duration.seconds(time));
        transition.setDelay(Duration.seconds(.25));
        if (!raceCar.getIsFinished()){
            transition.setOnFinished(event -> {
                int c = carIndex;
                int i = index;
                car.setRotate(raceCar.getAngle() + allCheckpoints[carIndex][index].getAngle());
                if (i == 3){
                    i = 0;
                    checkpointsEncountered[carIndex]++;;
                    if (checkpointsEncountered[carIndex] == 3){
                       raceCar.setIsFinished(true);
                    }
                } else {
                    i++;
                    checkpointsEncountered[carIndex]++;
                }

                simulator(car, c, i, time, raceCar);
            });
        }
        transition.setNode(car);
        transition.play();
    
    }
    
    
}
