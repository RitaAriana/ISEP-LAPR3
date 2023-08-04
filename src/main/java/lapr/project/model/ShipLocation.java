package lapr.project.model;

import java.util.Date;

/**
 * ShipLocation Class, which allows the user to know where the ship is.
 *
 * @author 1201239 Francisco Redol
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class ShipLocation implements Comparable<ShipLocation>{

    /**
     * Ship's MMSI. It allows the ShipLocation object to be tied with a Ship Object
     */
    private String MMSI;

    /**
     * Message Date and Time
     */
    private Date messageTime;

    /**
     * Location latitude
     */
    private String latitude;

    /**
     * Location Longitude
     */
    private String longitude;

    /**
     * Location SOG
     */
    private float SOG;

    /**
     * Location COG
     */
    private float COG;

    /**
     * Location Heading
     */
    private String heading;

    /**
     * Location Position
     */
    private String position;

    /**
     * Location transceiver Class
     */
    private String transceiverClass;

    /**
     * ShipLocation Constructor
     */
    public ShipLocation(){}

    /**
     * ShipLocation Constructor
     *
     * @param MMSI Ship's MMSI. It allows the ShipLocation object to be tied with a Ship Object
     * @param messageTime Message Date and Time
     * @param latitude Location latitude
     * @param longitude Location Longitude
     * @param SOG Location SOG
     * @param COG Location COG
     * @param heading Location Heading
     * @param position Location Position
     * @param transceiverClass Location transceiver Class
     */
    public ShipLocation(String MMSI, Date messageTime, String latitude, String longitude, float SOG, float COG, String heading, String position, String transceiverClass){
        setMMSI(MMSI);
        setMessageTime(messageTime);
        setLatitude(latitude);
        setLongitude(longitude);
        setSOG(SOG);
        setCOG(COG);
        setHeading(heading);
        setPosition(position);
        setTransceiverClass(transceiverClass);
    }

    /**
     * ShipLocation Constructor
     *
     * @param MMSI Ship's MMSI. It allows the ShipLocation object to be tied with a Ship Object
     * @param messageTime Message Date and Time
     * @param latitude Location latitude
     * @param longitude Location Longitude
     * @param SOG Location SOG
     * @param COG Location COG
     * @param heading Location Heading
     * @param transceiverClass Location transceiver Class
     */
    public ShipLocation(String MMSI, Date messageTime, String latitude, String longitude, float SOG, float COG, String heading, String transceiverClass){
        setMMSI(MMSI);
        setMessageTime(messageTime);
        setLatitude(latitude);
        setLongitude(longitude);
        setSOG(SOG);
        setCOG(COG);
        setHeading(heading);
        this.position = "not defined";
        setTransceiverClass(transceiverClass);
    }

    /**
     * Sets the Ship's MMSI according to the defined rules
     *
     * @param MMSI The ship's MMSI
     */
    public void setMMSI(String MMSI){
        if(MMSI == null || MMSI.length() != 9)
            throw new IllegalArgumentException("The ship MMSI code must be 9-digit long.");
        else
            this.MMSI = MMSI;
    }

    /**
     * Sets the Ship's date.
     *
     * @param date that is going to be inserted.
     */
    public void setMessageTime(Date date){
        this.messageTime = date;
    }

    /**
     * Sets the Latitude according to the defined rules
     *
     * @param latitude of the Location
     */
    public void setLatitude(String latitude){
        if(latitude == null || latitude.isEmpty())
            throw new IllegalArgumentException("Invalid Latitude.");

        if(latitude.equals("91") || latitude.equals("not available"))
            this.latitude = "not available";
        else if(Float.parseFloat(latitude) < -90 || Float.parseFloat(latitude)  > 90)
            throw new IllegalArgumentException("Invalid Latitude.");
        else
            this.latitude = latitude;
    }

    /**
     * Sets the Longitude according to the defined rules
     *
     * @param longitude of the Location
     */
    public void setLongitude(String longitude){
        if(longitude == null || longitude.isEmpty())
            throw new IllegalArgumentException("Invalid Longitude.");

        if(longitude.equals("181") || longitude.equals("not available"))
            this.longitude = "not available";
        else if(Float.parseFloat(longitude) < -180 || Float.parseFloat(longitude)  > 180)
            throw new IllegalArgumentException("Invalid Longitude.");
        else
            this.longitude = longitude;
    }

    /**
     * Sets the SOG according to the defined rules
     *
     * @param SOG of the Location
     */
    public void setSOG(float SOG){
        if(SOG < 0)
            throw new IllegalArgumentException("Invalid SOG.");
        else
            this.SOG = SOG;
    }

    /**
     * Sets the COG according to the defined rules
     *
     * @param COG of the Location
     */
    public void setCOG(float COG){
        if(COG < 0)
            this.COG = 360 + COG;
        else if (COG > 360)
            this.COG = COG - 360;
        else
            this.COG = COG;
    }

    /**
     * Sets the Heading according to the defined rules
     *
     * @param heading of the Location
     */
    public void setHeading(String heading){
        if(heading == null || heading.isEmpty())
            throw new IllegalArgumentException("Invalid Heading.");

        if(heading.equals("511") || heading.equals("not available"))
            this.heading = "not available";
        else if(Integer.parseInt(heading) < 0 || Integer.parseInt(heading) > 359)
            throw new IllegalArgumentException("Invalid Heading.");
        else
            this.heading = heading;
    }


    /**
     * The Ship Location's Position
     *
     * @param position of the Ship Location
     */
    public void setPosition(String position){
        this.position = position;
    }

    /**
     * The Ship Location's Transceiver Class
     *
     * @param transceiverClass of the Ship Location
     */
    public void setTransceiverClass(String transceiverClass){
        this.transceiverClass = transceiverClass;
    }

    /**
     * @return ship's MMSI
     */
    public String getMMSI(){
        return MMSI;
    }

    /**
     * @return the Date of the Location sent
     */
    public Date getMessageTime() {return messageTime;}

    /**
     * @return the Latitude of the Location
     */
    public String getLatitude(){
        return latitude;
    }

    /**
     * @return the Longitude of the Location
     */
    public String getLongitude(){
        return longitude;
    }

    /**
     * @return the SOG of the Location
     */
    public float getSOG(){
        return SOG;
    }

    /**
     * @return the COG of the Location
     */
    public float getCOG(){
        return COG;
    }

    /**
     * @return the Heading of the Location
     */
    public String getHeading(){
        return heading;
    }

    /**
     * @return the Position of the Location
     */
    public String getPosition(){
        return position;
    }

    /**
     * @return the Transceiver Class
     */
    public String getTransceiverClass(){
        return transceiverClass;
    }

    /**
     * Method that allows the system to compare two ShipLocation Objects
     *
     * @param o The object to be compared
     * @return true if the objects are equal and false if not
     */
    @Override
    public int compareTo(ShipLocation o) {
        return this.messageTime.compareTo(o.getMessageTime());
    }

    /**
     * Textual description of the ship's location
     * @return a string representation of the ship location
     */
    @Override
    public String toString(){
        return String.format("%nDate: %s%nLatitude: %s%nLongitude: %s%n%nSOG: %f%nCOG: %f%nHeading: %s%n", messageTime,latitude,longitude,SOG,COG,heading);
    }

    /**
     * Compare the parameter category with the other object provided.
     * @param o Object we want to compare with the parameter category.
     * @return true if the received object represents another parameter category equivalent to the parameter category. Otherwise, it returns false.
     */
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }

        if(o == null || this.getClass() != o.getClass()){
            return false;
        }

        ShipLocation otherParameterCategory = (ShipLocation) o;

        return this.getMMSI().equals(otherParameterCategory.getMMSI()) || this.getMessageTime().equals(otherParameterCategory.getMessageTime()) || this.getLatitude().equals(otherParameterCategory.getLatitude()) || this.getLongitude().equals(otherParameterCategory.getLongitude()) || this.getSOG() == otherParameterCategory.getSOG() || this.getCOG() == otherParameterCategory.getCOG() || this.getHeading().equals(otherParameterCategory.getHeading()) || this.getTransceiverClass().equals(otherParameterCategory.getTransceiverClass());
    }
}
