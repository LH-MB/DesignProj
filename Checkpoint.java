package RacingSimulator;

/**
 * Louis Hwang, Nathan Moore
 * CS 225 - Project 3
 *
 * Louis:   created xPos, yPos, toString, and equals attributes/methods
 * Nathan:  created angle attributes/methods
 *
 * Checkpoint class represents a checkpoint. Attributes are the X-coordinate, Y-coordinate, and angle (in degrees)
 */

public class Checkpoint {

    private double xPos;
    private double yPos;
    private double angle;


    //Default constructor
    public Checkpoint(){
        xPos = 0.0;
        yPos = 0.0;
        angle = 0;
    }

    public Checkpoint(double xPos, double yPos, double angle){
        this.xPos = xPos;
        this.yPos = yPos;
        this.angle = angle;
    }

    //Getters
    public double getXPos(){
        return xPos;
    }

    public double getYPos(){
        return yPos;
    }

    public double getAngle(){
        return angle;
    }
    //Setters
    public void setXPos(double a){
        xPos = a;
    }

    public void setYPos(double a){
        yPos = a;
    }

    public void setAngle(double a){
        angle = a;
    }

    public String toString(){
        return "X Position: " + xPos + " Y Position: " + yPos + " Angle: " + angle;

    }

    public boolean equals(Object obj){
        if (obj == this) return true;

        if (obj == null) return false;

        if (this.getClass() == obj.getClass()){
            Checkpoint a = (Checkpoint) obj;

            return this.xPos == a.xPos
                    && this.yPos == a.yPos
                    && this.angle == a.angle;
        }
        else
            return false;
    }

}
