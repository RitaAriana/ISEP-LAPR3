package lapr.project.model.store;

import lapr.project.model.Capital;
import lapr.project.model.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalStoreTest {

    @Test
    void getCapitalLst() {
        CapitalStore capitalStore = new CapitalStore();
        boolean capitalStrEmpty = capitalStore.getCapitalLst().isEmpty();
        assertTrue(capitalStrEmpty);
    }

    @Test
    void createCapital() {
        Capital capital = new Capital("Lisboa", new Country("Europa", "Portugal"), "33.4", "38.3");
        CapitalStore capitalStore = new CapitalStore();
        Capital capital2 = capitalStore.createCapital("Lisboa", new Country("Europa", "Portugal"), "33.4", "38.3");
        boolean equalCapitals = true;
        try{
            if(!capital.getName().equalsIgnoreCase(capital2.getName()))
                throw new IllegalArgumentException();
            if(!capital.getCountryName().equalsIgnoreCase(capital2.getCountryName()))
                throw new IllegalArgumentException();
            if(!capital.getContinent().equalsIgnoreCase(capital2.getContinent()))
                throw new IllegalArgumentException();
            if(capital.getLatitude() != capital2.getLatitude())
                throw new IllegalArgumentException();
            if(capital.getLongitude() != capital2.getLongitude())
                throw new IllegalArgumentException();
        } catch (Exception e){
            equalCapitals = false;
        }
        assertTrue(equalCapitals);
    }

    @Test
    void saveCapital() {
        CapitalStore capitalStr = new CapitalStore();
        Capital capital = new Capital("Lisboa", new Country("Europa", "Portugal"), "33.4", "38.3");
        capitalStr.saveCapital(capital);
        assertFalse(capitalStr.getCapitalLst().isEmpty());
    }

    @Test
    void getCapitalByNameExists() {
        CapitalStore capitalStr = new CapitalStore();
        Capital capital = capitalStr.createCapital("Lisboa", new Country("Europa", "Portugal"), "33.4", "38.3");
        Capital capital2 = capitalStr.createCapital("Paris", new Country("Europa", "França"), "33.4", "38.3");
        capitalStr.saveCapital(capital2);
        capitalStr.saveCapital(capital);

        Capital toBeFound = capitalStr.getCapitalByCountryName("Portugal");

        boolean exists = toBeFound != null;

        assertTrue(exists);
    }

    @Test
    void getCapitalByNameNotExist() {
        CapitalStore capitalStr = new CapitalStore();
        Capital capital = new Capital("Lisboa", new Country("Europa", "Portugal"), "33.4", "38.3");
        capitalStr.saveCapital(capital);

        Capital toBeFound = capitalStr.getCapitalByCountryName("Luxemburgo");

        boolean exists = toBeFound != null;

        assertFalse(exists);
    }
}