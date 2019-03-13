package RacingSimulator;

import javafx.scene.image.ImageView;

/**
 * Louis Hwang, Nathan Moore, and Svet Draganitchki
 * CS 225 - Project 3
 */

@SuppressWarnings("Duplicates")

public class RaceCar{

    private double engine;
    private double tires;
    private double nitrous;
    private double calculatedSpeed;
    private int startPosition;
    private int endPosition;
    private ImageView image;

    //Default constructor
    public RaceCar(){
        engine = 0.0;
        tires = 0.0;
        nitrous = 0.0;
        calculatedSpeed = 0.0;
        startPosition = 0;
        endPosition = 0;
        image = null;
    }

    public RaceCar(int startPosition, int endPosition, ImageView image) {
        engine = (Math.random() * 3) + 2;
        tires = (Math.random() * 3) + 2;
        if ((int)(Math.random() * 3 + 1) == 1) {
            nitrous = 1;
        } else {
            nitrous = 0;
        }
        this.calculatedSpeed = engine + tires + nitrous;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.image = image;
    }

    //Getters
    public double getEngine(){
        return engine;
    }

    public double getTires(){
        return tires;
    }

    public double getNitrous(){
        return nitrous;
    }

    public double getCalculatedSpeed() {
        return calculatedSpeed;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public ImageView getImage() {
        return image;
    }

    //Setters
    public void setEngine(int a){
        engine = a;
    }

    public void setTires(int a){
        tires = a;
    }

    public void setNitrous(double a){
        nitrous = a;
    }

    public void setCalculatedSpeed(double calculatedSpeed) {
        this.calculatedSpeed = calculatedSpeed;
    }

    public void setStartPosition(int a){
        startPosition = a;
    }

    public void setEndPosition(int a){
        endPosition = a;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void randomizeValues(){
        engine = (Math.random() * 3) + 2;
        tires = (Math.random() * 3) + 2;
        if ((int)(Math.random() * 3 + 1) == 1) {
            nitrous = 1;
        } else {
            nitrous = 0;
        }
        calculatedSpeed = engine + tires + nitrous;
    }
    
    public String toString(){
        return "Engine: " + engine + " Tires: " + tires + " Nitrous: " + nitrous + " Current Position: " + startPosition
                + "End Position " + endPosition;

    }

    public boolean equals(Object obj){
        if (obj == this) return true;

        if (obj == null) return false;

        if (this.getClass() == obj.getClass()){
            RaceCar a = (RaceCar) obj;

            return this.engine == a.engine
                    && this.tires == a.tires
                    && this.nitrous == a.nitrous
                    && this.startPosition == a.startPosition
                    && this.endPosition == a.endPosition;
        }
        else
            return false;
    }

}