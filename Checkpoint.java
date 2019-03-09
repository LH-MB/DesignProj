package RacingSimulator;

/**
 * Louis Hwang, Nathan Moore, and Svet Draganitchki
 * CS 225 - Project 3
 */

public class Checkpoint{

    private double xPos;
    private double yPos;


    //Default constructor
    public Checkpoint(){
        xPos = 0.0;
        yPos = 0.0;
    }

    public Checkpoint(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    //Getters
    public double getXPos(){
        return xPos;
    }

    public double getYPos(){
        return yPos;
    }

    //Setters
    public void setXPos(double a){
        xPos = a;
    }

    public void setYPos(double a){
        yPos = a;
    }

    public String toString(){
        return "X Position: " + xPos + " Y Position: " + yPos;

    }

    public boolean equals(Object obj){
        if (obj == this) return true;

        if (obj == null) return false;

        if (this.getClass() == obj.getClass()){
            Checkpoint a = (Checkpoint) obj;

            return this.xPos == a.xPos
                    && this.yPos == a.yPos;
        }
        else
            return false;
    }






}