package lapr.project.model;

/**
 * Border Class, which identifies the border between two countries
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class Border {

    /**
     * The name of the first country of the border
     */
    private final String countryName;

    /**
     * The name of the second country of the border
     */
    private final String countryName2;

    /**
     * @param countryName the name of the first country of the border
     * @param countryName2 the name of the second country of the border
     */
    public Border(String countryName, String countryName2){
        this.countryName = countryName;
        this.countryName2 = countryName2;
    }

    /**
     * @return the first country of the border
     */
    public String getCountryName(){
        return this.countryName;
    }

    /**
     * @return the second country of the border
     */
    public String getCountryName2(){
        return this.countryName2;
    }
}
