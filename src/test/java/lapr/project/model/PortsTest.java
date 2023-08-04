package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortsTest {

    Ports port;

    public PortsTest(){
        Country country1=new Country("Europe","Denmark");
        PlaceLocation placeLocation1= new PlaceLocation(56.15,10.21666667);
        port = new Ports(country1,10358,"Aarhus",placeLocation1);
    }

    @Test
    void getPortName() {
        String expected = "Aarhus";
        assertEquals(expected,port.getPortName());
    }

    @Test
    void getCode() {
        int expected = 10358;
        assertEquals(expected,port.getCode());
    }

    @Test
    void getLatitude() {
        double expected = 56.15;
        assertEquals(expected,port.getLatitude());
    }

    @Test
    void getLongitude() {
        double expected = 10.21666667;
        assertEquals(expected,port.getLongitude());
    }

    @Test
    void getCountryName() {
        String expected = "Denmark";
        assertEquals(expected,port.getCountryName());
    }

    @Test
    void getContinent() {
        String expected = "Europe";
        assertEquals(expected,port.getContinent());
    }

    @Test
    void equals01(){
        assertEquals(port,port);
    }

    @Test
    void equalsNotEquals01(){
        Country country2 =new Country("Europe","Denmark");
        PlaceLocation placeLocation2 = new PlaceLocation(56.10,10.21666667);
        Ports port2 = new Ports(country2,10358,"Aarhus",placeLocation2);
        assertNotEquals(port,port2);
    }

    @Test
    void equalsNotEquals02(){
        ShipLocation shipLocation = new ShipLocation();
        assertNotEquals(port,shipLocation);
    }

    @Test
    void equalsNotEquals03(){
        Country country3 =new Country("Europe","Denmark");
        PlaceLocation placeLocation3 = new PlaceLocation(56.15,10.21666667);
        Ports port3 = new Ports(country3,10355,"Aarhus",placeLocation3);
        assertNotEquals(port,port3);
    }

    @Test
    void toStringTest(){
        Ports ports = new Ports(new Country("Europe", "Portugal"),12345, "Leixoes", new PlaceLocation(53, 53));
        String result = ports.toString();

        assertEquals("Port Leixoes with code: 12345", result);

    }
}