package lapr.project.model;

import java.util.Objects;

public abstract class Place {
    /**
     * The port location
     */
    private final PlaceLocation coordinates;

    /**
     * The port country
     */
    private final Country country;

    /**
     * Constructs an instance of Location receiving the following parameters:
     *
     * @param latitude latitude
     * @param longitude longitude
     * @param countryName country name
     */
    public Place(double latitude, double longitude, String countryName, String continent) {
        coordinates = new PlaceLocation(latitude,longitude);
        country = new Country(continent,countryName);
    }

    public double getLatitude(){
        return coordinates.getLatitude();
    }

    public double getLongitude(){
        return coordinates.getLongitude();
    }

    public String getCountryName(){
        return country.getCountryName();
    }

    public String getContinent(){
        return country.getContinent();
    }


}
