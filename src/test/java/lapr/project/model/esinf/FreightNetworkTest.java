package lapr.project.model.esinf;

import lapr.project.controller.*;
import lapr.project.model.*;
import lapr.project.model.store.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FreightNetworkTest {
    PortStore portStore;

    SeadistStore seadistStore;

    CapitalStore capitalStore;

    BorderStore borderStore;

    CountryStore countryS;

    @BeforeEach
    public void setUp() {

        countryS = new CountryStore();

        seadistStore = new SeadistStore();
        Seadist seadist = new Seadist(10358, 13012, 1457f, "Aarhus", "Leixoes", "Denmark", "Portugal");
        Seadist seadist2 = new Seadist(19057, 13012, 935f, "Antwerp", "Leixoes", "Belgium", "Portugal");
        Seadist seadist3 = new Seadist(13012, 18476, 817f, "Leixoes", "Ponta Delgada", "Portugal", "Portugal");
        Seadist seadist4 = new Seadist(18476, 13390, 804f, "Ponta Delgada", "Setubal", "Portugal", "Portugal");
        seadistStore.saveSeadist(seadist);
        seadistStore.saveSeadist(seadist2);
        seadistStore.saveSeadist(seadist3);
        seadistStore.saveSeadist(seadist4);

        portStore = new PortStore();
        PlaceLocation placeLocation2 = new PlaceLocation(41.18333333,-8.7);
        Country country2 = new Country("Europe","Portugal");
        countryS.saveCountry(country2);
        Ports ports2 = new Ports(country2,13012,"Leixoes",placeLocation2);
        portStore.savePort(ports2);
        PlaceLocation placeLocation1 = new PlaceLocation(56.15,10.21666667);
        Country country1 = new Country("Europe","Denmark");
        countryS.saveCountry(country1);
        Ports ports1 = new Ports(country1,10358,"Aarhus",placeLocation1);
        portStore.savePort(ports1);
        PlaceLocation placeLocation3 = new PlaceLocation(51.23333333,4.466666667);
        Country country3 = new Country("Europe","Belgium");
        countryS.saveCountry(country3);
        Ports ports3 = new Ports(country3,10358,"Antwerp",placeLocation3);
        portStore.savePort(ports3);
        PlaceLocation placeLocation4 = new PlaceLocation(37.73333333,-25.66666667);
        Ports ports4 = new Ports(country2,18476,"Ponta Delgada",placeLocation4);
        portStore.savePort(ports4);
        PlaceLocation placeLocation5 = new PlaceLocation(38.5,-8.916666667);
        Ports ports5 = new Ports(country2,13390,"Setubal",placeLocation5);
        portStore.savePort(ports5);

        borderStore = new BorderStore();
        Border border1 = new Border("Portugal","Spain");
        Border border2 = new Border("Spain","France");
        Border border3 = new Border("France","Italy");
        Border border4 = new Border("France","Switzerland");
        borderStore.saveBorder(border1);
        borderStore.saveBorder(border2);
        borderStore.saveBorder(border3);
        borderStore.saveBorder(border4);

        capitalStore = new CapitalStore();
        Country country11 = new Country("Europe","Portugal");
        countryS.saveCountry(country11);
        Capital capital11 = new Capital("Lisbon",country11,"38.71666667","-9.133333");
        capitalStore.saveCapital(capital11);
        Country country12 = new Country("Europe","Spain");
        countryS.saveCountry(country12);
        Capital capital12 = new Capital("Madrid",country12,"40.4","-3.683333");
        capitalStore.saveCapital(capital12);
        Country country13 = new Country("Europe","France");
        countryS.saveCountry(country13);
        Capital capital13 = new Capital("Paris",country13,"48.86666667","2.333333");
        capitalStore.saveCapital(capital13);
        Country country14 = new Country("Europe","Italy");
        countryS.saveCountry(country14);
        Capital capital14 = new Capital("Rome",country14,"41.9","12.483333");
        capitalStore.saveCapital(capital14);
        Country country15 = new Country("Europe","Switzerland");
        countryS.saveCountry(country15);
        Capital capital15 = new Capital("Bern",country15,"46.91666667","7.466667");
        capitalStore.saveCapital(capital15);
    }

    @Test
    void addNewInformation() {
        FreightNetwork freightNetwork = new FreightNetwork();
        freightNetwork.addNewInformation(capitalStore,portStore,seadistStore,borderStore,1);
        assertEquals(17,freightNetwork.getAdjacencyMatrixGraph().edges().size());
    }

    @Test
    void linkBetweenCapitalsOfNeighboringCountries() {
        FreightNetwork freightNetwork = new FreightNetwork();
        freightNetwork.linkBetweenCapitalsOfNeighboringCountries(capitalStore,borderStore);
        assertEquals(8,freightNetwork.getAdjacencyMatrixGraph().edges().size());
    }

    @Test
    void connectionBetweenPortsOfTheSameCountry() {
        FreightNetwork freightNetwork = new FreightNetwork();
        freightNetwork.connectionBetweenPortsOfTheSameCountry(portStore,seadistStore);
        assertEquals(4,freightNetwork.getAdjacencyMatrixGraph().edges().size());
    }

    @Test
    void connectionBetweenTheCapitalAndTheNearestPort() {
        FreightNetwork freightNetwork = new FreightNetwork();
        freightNetwork.connectionBetweenTheCapitalAndTheNearestPort(capitalStore,portStore);
        assertEquals(2,freightNetwork.getAdjacencyMatrixGraph().edges().size());
    }

    @Test
    void connectionBetweenThePortAndTheNearestNPortsOfAnotherCountry() {
        FreightNetwork freightNetwork = new FreightNetwork();
        freightNetwork.connectionBetweenThePortAndTheNearestNPortsOfAnotherCountry(seadistStore,1,portStore);
        assertEquals(3,freightNetwork.getAdjacencyMatrixGraph().edges().size());
    }


    @Test
    void geral() throws IOException {
        CreateFreightNetworkController controller = new CreateFreightNetworkController();
        controller.createFreightNetwork(1);
        ColorFreightNetworkController colorFreightNetworkController = new ColorFreightNetworkController();
        colorFreightNetworkController.colorNetwork();
        assertEquals(5, App.getInstance().getCompany().getFreightNetwork().getCoresUtilizadas());
    }

    @Test
    void centeredCitties (){
        FreightNetwork freightNetwork = new FreightNetwork();
        freightNetwork.linkBetweenCapitalsOfNeighboringCountries(capitalStore,borderStore);
        Map<String, List<Place>> result = freightNetwork.mostCenteredCities(1, countryS);
        Assertions.assertEquals("France",result.get("Europe").get(0).getCountryName());
    }

    @Test
    void portsMoreCritical () {

        BorderStore border = new BorderStore();
        Border border1 = new Border("Portugal","Spain");
        Border border2 = new Border("Brazil","Venezuela");
        border.saveBorder(border1);
        border.saveBorder(border2);

        CapitalStore capital = new CapitalStore();
        CountryStore countryS = new CountryStore();
        Country country11 = new Country("Europe","Portugal");
        countryS.saveCountry(country11);
        Capital capital11 = new Capital("Lisbon",country11,"38.71666667","-9.133333");
        capital.saveCapital(capital11);
        Country country12 = new Country("Europe","Spain");
        countryS.saveCountry(country12);
        Capital capital12 = new Capital("Madrid",country12,"40.4","-3.683333");
        capital.saveCapital(capital12);
        Country country13 = new Country("Europe","United Kingdom");
        countryS.saveCountry(country13);
        Capital capital13 = new Capital("London",country13,"51.5","-0.083333");
        capital.saveCapital(capital13);
        Country country14 = new Country("America","Brazil");
        countryS.saveCountry(country14);
        Capital capital14 = new Capital("Brasilia",country14,"-15.78333333","-47.916667");
        capital.saveCapital(capital14);
        Country country15 = new Country("America","Venezuela");
        countryS.saveCountry(country15);
        Capital capital15 = new Capital("Caracas",country15,"10.48333333","-66.866667");
        capital.saveCapital(capital15);

        PortStore portStore = new PortStore();
        PlaceLocation placeLocation4 = new PlaceLocation(37.73333333,-25.66666667);
        Ports ports4 = new Ports(country11,18476,"Ponta Delgada",placeLocation4);
        portStore.savePort(ports4);
        PlaceLocation placeLocation5 = new PlaceLocation(38.5,-8.916666667);
        Ports ports5 = new Ports(country11,13390,"Setubal",placeLocation5);
        portStore.savePort(ports5);
        PlaceLocation placeLocation1 = new PlaceLocation(38.53333333,-28.61666667);
        Ports ports1 = new Ports(country11,18433,"Horta",placeLocation1);
        portStore.savePort(ports1);
        PlaceLocation placeLocation2 = new PlaceLocation(51.5,-0.066666667);
        Ports ports2 = new Ports(country13,29239,"London",placeLocation2);
        portStore.savePort(ports2);
        PlaceLocation placeLocation3 = new PlaceLocation(-23.93333333,-46.31666667);
        Ports ports3 = new Ports(country14,27248,"Santos",placeLocation3);
        portStore.savePort(ports3);

        SeadistStore seadistStore = new SeadistStore();
        Seadist seadist = new Seadist(27248, 18433, 4082f, "Santos", "Horta", "Brazil", "Portugal");
        Seadist seadist2 = new Seadist(18433, 29239, 1569f, "Horta", "London", "Portugal", "United Kingdom");
        Seadist seadist3 = new Seadist(18433, 18476, 155f, "Horta", "Ponta Delgada", "Portugal", "Portugal");
        Seadist seadist4 = new Seadist(18433, 13390, 939f, "Horta", "Setubal", "Portugal", "Portugal");
        seadistStore.saveSeadist(seadist);
        seadistStore.saveSeadist(seadist2);
        seadistStore.saveSeadist(seadist3);
        seadistStore.saveSeadist(seadist4);

        FreightNetwork freightNetwork = new FreightNetwork();
        freightNetwork.addNewInformation(capital,portStore,seadistStore,border,2);
        List<String> result = freightNetwork.getsPortsMoreCritical(1);
        assertEquals("Horta",result.get(0));
    }


}