package RacingSimulator;

/**
 * Louis Hwang, Nathan Moore, and Svet Draganitchki
 * CS 225 - Project 3
 */

public class RaceCar{

    private double engine;
    private double tires;
    private boolean nitrous;
    private String color;
    private char currentPosition;
    private char endPosition;

    //Default constructor
    public RaceCar(){
        engine = 0.0;
        tires = 0.0;
        nitrous = false;
        color = null;
        currentPosition = '\0';
        endPosition = '\0';
    }

    public RaceCar(int engine, int tires, boolean nitrous, String color, char currentPosition, char endPosition){
        this.engine = engine;
        this.tires = tires;
        this.nitrous = nitrous;
        this.color = color;
        this.currentPosition = currentPosition;
        this.endPosition = endPosition;
    }

    //Getters
    public double getEngine(){
        return engine;
    }

    public double getTires(){
        return tires;
    }

    public boolean getNitrous(){
        return nitrous;
    }

    public String getColor(){
        return color;
    }

    public char getCurrentPosition(){
        return currentPosition;
    }

    public char getEndPosition(){
        return endPosition;
    }

    //Setters
    public void setEngine(int a){
        engine = a;
    }

    public void setTires(int a){
        tires = a;
    }

    public void setNitrous(boolean a){
        nitrous = a;
    }

    public void setCurrentPosition(char a){
        currentPosition = a;
    }

    public void setEndPosition(char a){
        endPosition = a;
    }

    public void randomizeValues(){

    }

    public String toString(){
        return "Engine: " + engine + " Tires: " + tires + " Nitrous: " + nitrous + " Color: " + color + " Current Position: " + currentPosition
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
                    && this.color.equals(a.color)
                    && this.currentPosition == a.currentPosition
                    && this.endPosition == a.endPosition;
        }
        else
            return false;
    }






}