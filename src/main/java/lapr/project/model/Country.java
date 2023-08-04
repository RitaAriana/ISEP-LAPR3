package lapr.project.model;

/**
 * Represents a Country.
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class Country {

    /**
     * The name of the country
     */
    private final String countryName;

    /**
     * The continent to which the country belongs
     */
    private final String continent;

    /**
     * Build an instance of {@code Country} by receiving the name and the continent
     * @param continent The continent to which the country belongs
     * @param countryName The name of the country
     */
    public Country(String continent, String countryName){
        this.continent=continent;
        this.countryName=countryName;
    }

    /**
     * Get the name of the country
     * @return The name of the country
     */
    public String getCountryName(){
        return countryName;
    }

    /**
     * Get the continent to which the country belongs
     * @return The continent to which the country belongs
     */
    public String getContinent(){
        return continent;
    }




}
