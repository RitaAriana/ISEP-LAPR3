package lapr.project.model.store;

import lapr.project.model.Country;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CountryStoreTest {

    CountryStore countryStore = new CountryStore();

    public CountryStoreTest(){
    }

    @Test
    void saveCountry() {
        Country country1 = countryStore.createCountry("Denmark","Europe");
        countryStore.saveCountry(country1);
        assertEquals(country1,countryStore.getCountryByName("Denmark"));
    }

    @Test
    void getCountryByNameNull() {
        assertNull(countryStore.getCountryByName(null));
    }
}