package RacingSimulator;

import java.text.DecimalFormat;
import javafx.scene.image.ImageView;

/**
 * Louis Hwang, Svet Draganitchki, Nathan Moore
 * CS 225 - Project 3
 *
 * Louis:   Created engine, tires, nitrous, startPosition, endPosition, toString, and equals attributes/methods
 * Nathan:  Changed data types of some attributes. Created colorCar, calculatedSpeed, ImageView attributes/methods
 * Svet: added String variable to RaceCar. 
 * RaceCar class represents a RaceCar. Attributes are engine, tires, nitrous, calculated speed, start position, end position, and image;
 */

public class RaceCar{
    
    private String colorCar;
    private double engine;
    private double tires;
    private double nitrous;
    private double calculatedSpeed;
    private int startPosition;
    private int endPosition;
    private ImageView image;

    //CONSTRUCTOR
    public RaceCar(){
        colorCar = "Not Set";
        engine = 0.0;
        tires = 0.0;
        nitrous = 0.0;
        calculatedSpeed = 0.0;
        startPosition = 0;
        endPosition = 0;
        image = null;
    }

    public RaceCar(String colorCar,int startPosition, int endPosition, ImageView image) {
        engine = (Math.random() * 3) + 2;
        tires = (Math.random() * 3) + 2;
        if ((int)(Math.random() * 3 + 1) == 1) {
            nitrous = 1;
        } else {
            nitrous = 0;
        }
        this.colorCar = colorCar;
        this.calculatedSpeed = engine + tires + nitrous;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.image = image;
    }

    //GETTERS
    public String getColor(){
        return colorCar;
    }
    
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

    //SETTERS
    public void setColor(String a){
        colorCar = a;
    }
    
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
    
    //Randomzie engine, tires, and nitrous for calculatedSpeed
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
    
	@Override
    public String toString(){
        return colorCar + " Engine: " + engine + " Tires: " + tires + " Nitrous: " + nitrous + " Current Position: " + startPosition
                + "End Position " + endPosition + " Image: " + image;

    }
	
	@Override
    public boolean equals(Object obj){
        if (obj == this) return true;

        if (obj == null) return false;

        if (this.getClass() == obj.getClass()){
            RaceCar a = (RaceCar) obj;

            return this.colorCar.equals(a.colorCar)
                    && this.engine == a.engine
                    && this.tires == a.tires
                    && this.nitrous == a.nitrous
                    && this.startPosition == a.startPosition
                    && this.endPosition == a.endPosition
                    && this.image.equals(a.image);
        }
        else
            return false;
    }

}
