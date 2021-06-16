package obj;

import java.io.Serializable;

/**
 * This method is used to create coordinate object
 * @author bruno
 *
 */

public class Coordinates implements  Serializable{
    private Double x; 
    private Float y; 
    
    public Coordinates(Double x, float y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * 
     * @return y coordinate
     */
    public float getY() {
        return y;
    }
    
    /**
     * 
     * @return x coordinate
     */
    public Double getX() {
        return x;
    }
    
    /**
     * coordinate object information
     * @return information
     */
    public String getShow() {
    	return " Coordinates (" +getX()+ " " +getY()+")";
    }
}
