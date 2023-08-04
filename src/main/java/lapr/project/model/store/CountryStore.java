package lapr.project.model.store;

import lapr.project.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * The different countries existing in a company.
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class CountryStore {

    /**
     * List containing all Countries in the company
     */
    private List<Country> countryLst;

    /**
     * Instantiates a new Country Store
     */
    public CountryStore(){
        countryLst=new ArrayList();
    }

    public List<Country> getCountryLst(){
        return countryLst;
    }
    /**
     * New country.
     * @param countryName The name of the country
     * @param continent The continent to which the country belongs
     * @return The country.
     */
    public Country createCountry(String countryName, String continent) {
        return new Country(continent,countryName);
    }


    /**
     * Save the country case it is in a valid state.
     * @param c The country we intend to save.
     */
    public void saveCountry(Country c) {
        countryLst.add(c);
    }

    /**
     * Gets country through name.
     * @param name the name associated with the country that we want to get
     * @return the country associated with the name
     */
    public Country getCountryByName(String name){
        for (Country c : countryLst) {
            if (c.getCountryName().equals(name)) {
                return c;
            }
        }
        return null;
    }
}
