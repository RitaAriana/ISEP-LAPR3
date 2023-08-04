package lapr.project.model.store;
import lapr.project.model.Capital;
import lapr.project.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * Capital Store, which saves capitals and creates them
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class CapitalStore {

    /**
     * List containing all Capitals in the company
     */
    private final List<Capital> capitalLst;

    /**
     * Instantiates a new Country Store
     */
    public CapitalStore(){
        capitalLst=new ArrayList<>();
    }

    /**
     * Returns a list of capitals
     *
     * @return a list of capitals
     */
    public List<Capital> getCapitalLst(){
        return capitalLst;
    }

    /**
     * Creates a new Capital
     * @param name of the Capital
     * @param country of the Capital
     * @param latitude of the Capital
     * @param longitude of the Capital
     * @return The created capital
     */
    public Capital createCapital(String name, Country country, String latitude, String longitude){
        return new Capital(name, country, latitude, longitude);
    }

    /**
     * Saves the parameter Capital in the Instance List (capitalLst)
     * @param capital that we intend to save.
     */
    public void saveCapital(Capital capital) {
        capitalLst.add(capital);
    }

    /**
     * Gets country through name.
     * @param name the name associated with the country that we want to get
     * @return the country associated with the name
     */
    public Capital getCapitalByCountryName(String name){
        for (Capital capital : capitalLst) {
            if (capital.getCountryName().equals(name)) {
                return capital;
            }
        }
        return null;
    }

    /**
     * Gets country through name.
     * @param name the name associated with the country that we want to get
     * @return the country associated with the name
     */
    public Capital getCapitalByName(String name){
        for (Capital capital : capitalLst) {
            if (capital.getName().equals(name)) {
                return capital;
            }
        }
        return null;
    }
}
