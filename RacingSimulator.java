package RacingSimulator;

/**
 * Louis Hwang, Nathan Moore, and Svet Draganitchki
 * CS 225 - Project 3
 */

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("Duplicates")

public class RacingSimulator extends Application {

    //private varibles used throughout RacingSimulator
    private Checkpoint[] checkpoints;
    private Label[] results;
    private int counter;
    
    private ImageView carView;
    private boolean winner;
    
    private RaceCar blueCar;
    private RaceCar redCar;
    private RaceCar whiteCar;
    private RaceCar purpleCar;
    
    private Pane trackPanel;
    
    private String relativePath = "/RacingSimulator/images/";
    
    private Button start;
    private Button reset;
    
    private VBox leftSide;
//    private Text results;
    private VBox rightSide;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       
        results = new Label[4];
        counter = 0;
        
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

        //CARS 
        blueCar = new RaceCar(0, 3, setUpCar(relativePath + "blue_car.png", checkpointA));
        redCar = new RaceCar(1, 0, setUpCar(relativePath + "red_car.png", checkpointB));
        whiteCar = new RaceCar(2, 1, setUpCar(relativePath + "white_car.png", checkpointC));
        purpleCar = new RaceCar(3, 2, setUpCar(relativePath + "purple_car.png", checkpointD));

        //RACETRACK
        Image raceTrackImg = new Image(relativePath + "raceTrack.png");
        ImageView raceTrackView = new ImageView();
        raceTrackView.setImage(raceTrackImg);
        
        trackPanel = new Pane();
        trackPanel.getChildren().addAll(raceTrackView, blueCar.getImage(), redCar.getImage(), whiteCar.getImage(), purpleCar.getImage());

        //START
        Image startImg = new Image(relativePath + "start.png");
        start = new Button("Start",new ImageView(startImg));
        start.setMinSize(50,50);
        start.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {   
                simulator(blueCar, 1);
                simulator(redCar, 2);
                simulator(whiteCar, 3);
                simulator(purpleCar, 0);
                start.setVisible(false);   
            }
        
    });
        
        //RESET
        Image resetImg = new Image(relativePath + "reset.png");
        reset = new Button("Restart",new ImageView(resetImg));
        reset.setMinSize(100,20);
        reset.setVisible(false);
        
        reset.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            
            public void handle(ActionEvent event) {
                trackPanel.getChildren().removeAll(blueCar.getImage(), redCar.getImage(), whiteCar.getImage(), purpleCar.getImage());
                
                blueCar.setStartPosition(0);
                redCar.setStartPosition(1);
                whiteCar.setStartPosition(2);
                purpleCar.setStartPosition(3);
                
                blueCar.setEndPosition(3);
                redCar.setEndPosition(0);
                whiteCar.setEndPosition(1);
                purpleCar.setEndPosition(2);
                
                blueCar.setImage(setUpCar(relativePath + "blue_car.png",checkpointA));
                redCar.setImage(setUpCar(relativePath + "red_car.png", checkpointB));
                whiteCar.setImage(setUpCar(relativePath + "white_car.png", checkpointC));
                purpleCar.setImage(setUpCar(relativePath + "purple_car.png", checkpointD));

                trackPanel.getChildren().addAll(blueCar.getImage(), redCar.getImage(), whiteCar.getImage(), purpleCar.getImage());
                counter = 0;
                winner = false;
                rightSide.getChildren().clear();
                rightSide.setPadding(new Insets(0));
//                try{
//                Thread.sleep(10000);
//                }
//                catch(java.lang.InterruptedException e){
//                    System.out.println(e);}  // Just leaving this here just incase I want to use it late
                start.setVisible(true);
                reset.setVisible(false);
            }
                
        
    });
        
        //LEFT SIDE
        leftSide = new VBox();
        leftSide.getChildren().addAll(title, start, trackPanel, reset);
        leftSide.setAlignment(Pos.CENTER);

        //RIGHT SIDE
        rightSide = new VBox(25);
        rightSide.setAlignment(Pos.CENTER_RIGHT);
//        rightSide.getStyleClass().add("title");
        rightSide.getStyleClass().add("results");

        
        
        //MAIN LAYOUT
        HBox mainLayout = new HBox();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.getChildren().addAll(leftSide,rightSide);
        mainLayout.setSpacing(150);

        //SCENE AND STAGE
        Scene scene = new Scene(mainLayout, 900, 700);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Racing Simulator");
        primaryStage.show();
    }

    private ImageView setUpCar(String carFilePath, Checkpoint checkpoint) {
        Image carImg = new Image(carFilePath);
        carView = new ImageView();
        carView.setImage(carImg);
        carView.setFitWidth(100);
        carView.setPreserveRatio(true);
        carView.setTranslateX(checkpoint.getXPos());
        carView.setTranslateY(checkpoint.getYPos());
        carView.setRotate(checkpoint.getAngle());
        return carView;
    }
    
    private ImageView resetCar(Checkpoint checkpoint){
        carView.setTranslateX(checkpoint.getXPos());
        carView.setTranslateY(checkpoint.getYPos());
        carView.setRotate(checkpoint.getAngle());
        return carView;
    }

    private void simulator(RaceCar car, int index) {
        
        TranslateTransition transition = new TranslateTransition();
        transition.setToX(checkpoints[index].getXPos());
        transition.setToY(checkpoints[index].getYPos());
        transition.setDuration(Duration.seconds(car.getCalculatedSpeed()/2.5));
        transition.setDelay(Duration.seconds(.25));
        
        ImageView carImage = car.getImage();
        transition.setNode(carImage);
        double angle = checkpoints[index].getAngle();
       
        if (index != car.getEndPosition()) {
            transition.setOnFinished(event -> {
                int i = index;
                carImage.setRotate(angle);
                if (i == 3) {
                    i = 0;
                } else {
                    i++;
                }
                car.randomizeValues();
                simulator(car, i);
            });
        }
        else  if (index == car.getEndPosition() && winner != true){
            winner = true;
            transition.setOnFinished(event -> {
                results [counter] = new Label("WINNER: " + car.toString());
                results[counter].setWrapText(true);
                results[counter].setFont(new Font("Arial",16));
                results[counter].setTextFill(Color.web("#ffcc00"));
                rightSide.setPadding(new Insets(25));
                rightSide.getChildren().addAll(results[counter]);
                counter ++;
            });
        }
        else if (index == car.getEndPosition()){
            transition.setOnFinished(event -> {
            results [counter] = new Label(car.toString());
            results[counter].setWrapText(true);
            results[counter].setFont(new Font("Arial",12));
            results[counter].setTextFill(Color.web("#ffffff"));
            rightSide.setPadding(new Insets(25));
            rightSide.getChildren().addAll(results[counter]);  
            counter ++;
            if (counter == 4){
                reset.setVisible(true);
            }
            });
        }

        transition.setNode(carImage);
        transition.play();
    }
  
}
