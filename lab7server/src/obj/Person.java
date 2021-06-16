package obj;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * This class contains all necessary variable to create a linked hash map element
 * @author bruno
 *
 */

public class Person implements  Serializable {
    private long id; //The field value must be greater than 0, The value of this field must be unique, The value of this field must be generated automatically
    private String name; //Field cannot be null, String cannot be empty
    private Coordinates coordinates; //The field cannot be null
    private ZonedDateTime creationDate; //The field cannot be null, the value of this field must be generated automatically
    private Double height; //Field can be null, Field value must be greater than 0
    private ZonedDateTime birthday; //The field cannot be null
    private String passportID; //ПThe field cannot be null
    private Color eyeColor; //The field cannot be null
    private Location location; //The field cannot be null
	private String dateTimeString;
	private String dateTimeBirthString;
	private int userId;
	
	private LocalDate Fbirthday;
	private String FdateTimeBirthString;
	
	
	
	public Person(long id, String name, Coordinates coordinates, 
			 Double height,
			 String passportID, Color eyeColor, Location location, int userID){
		this.id=id;
		this.name=name;
		this.coordinates=coordinates;
		this.creationDate = ZonedDateTime.now();
        this.dateTimeString = creationDate.toString();
		this.height=height;
		this.birthday=ZonedDateTime.now();
		this.dateTimeBirthString=birthday.toString();
		this.passportID=passportID;
		this.eyeColor=eyeColor;
		this.location=location;
		this.userId = userID;
		
		this.Fbirthday=LocalDate.now();
		this.FdateTimeBirthString=Fbirthday.toString();
	}
	
	/**
	 * This method prints out time and data
	 */
		public void printTime() {
			ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));
			ZonedDateTime creationDate = ZonedDateTime.now();
			System.out.println(creationDate);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss");
			String formattedString = zonedDateTimeNow.format(formatter);
			System.out.println(formattedString);
		}
		
		
	
	 	public String getName() {
	        return name;
	    }
	 	

	    public long getID() {
	        return id;
	    }
	    
	    public int getUserID() {
	        return userId;
	    }
	    
	    
	    
	    public ZonedDateTime getCreationDate() {
	        return creationDate;
	    }
	    
	    
	    
	    public void setCreationDate(ZonedDateTime creationDate) {
	        this.creationDate = creationDate;
	        this.dateTimeString = creationDate.toString();
	    }
	    
	    
	    
	    public Double getHeight() {
	        return height;
	    }
	    
	    
	    
	    public ZonedDateTime getBirthday() {
	        return birthday;
	    }
	    
	    
	    
	    public void setDateTimeBirthString(ZonedDateTime birthday) {
	    	this.birthday = birthday;
	    	this.dateTimeBirthString = birthday.toString();
	    }
	    
	    
	    
	    public String getPassportID() {
	    	return passportID;
	    }
	    
	    
	    
	    public Color getEyeColor() {
	        return eyeColor;
	    }
	    
	    
	    public Double getXcoordinate() {
	    	return coordinates.getX();
	    }
	    
	    
	    
	    public float getYcoordinate() {
	    	return coordinates.getY();
	    }
	    
	    
	    
	    public int getXlocation() {
	    	return location.getXloc();
	    }
	    
	    
	    
	    public long getYlocation() {
	    	return location.getYloc();
	    }
	    
	    
	    
	    public long getZlocation() {
	    	return location.getZloc();
	    }    
	    
	    
	    
	    public LocalDate FgetBirthday() { 
	        return Fbirthday;
	    }
	    
	    
	    
	    public void FsetDateTimeBirthString(LocalDate Fbirthday) {
	    	this.Fbirthday = Fbirthday;
	    	this.FdateTimeBirthString = Fbirthday.toString();
	    	//LocalDate Fbirthday = LocalDate.parse(FdateTimeBirthString, birthday);
	    }
	    
	    
	   
	    //Location loca = new Location();
	    
	    
	    
	    public String getShow() {
	    	return "ID: " +getID()+ " Name: " +getName()+ coordinates.getShow()+ " Creation data: " +getCreationDate()+ " Height: "
	                +getHeight()+ " Birthsdays: " +FgetBirthday()+ " PasspordtID: "
	                +getPassportID()+ " Eay color: " +getEyeColor()+ location.getShow();
	    	
	    			
	    }
	    
	    

}