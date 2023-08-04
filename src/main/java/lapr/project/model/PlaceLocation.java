package lapr.project.model;

/**
 * Represents a PlaceLocation.
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class PlaceLocation {

    /**
     * The latitude of the place
     */
    private double latitude;

    /**
     * The longitude of the place
     */
    private double longitude;

    /**
     * Build an instance of {@code PlaceLocation} by receiving the latitude and the longitude
     * @param latitude The latitude of the place
     * @param longitude The longitude of the place
     */
    public PlaceLocation(double latitude, double longitude){
        setLatitude(latitude);
        setLongitude(longitude);
    }

    /**
     * Sets the Latitude according to the defined rules
     *
     * @param latitude of the Location
     */
    public void setLatitude(double latitude){
        if(latitude < -90 || latitude > 90)
            throw new IllegalArgumentException("Invalid Latitude.");
        else
            this.latitude = latitude;
    }

    /**
     * Sets the Longitude according to the defined rules
     *
     * @param longitude of the Location
     */
    public void setLongitude(double longitude){
        if(longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("Invalid Longitude.");
        else
            this.longitude = longitude;
    }

    /**
     * Get the latitude of the place
     * @return The latitude of the place
     */
    public double getLatitude(){
        return latitude;
    }

    /**
     * Get the longitude of the place
     * @return The longitude of the place
     */
    public double getLongitude(){
        return longitude;
    }

}
