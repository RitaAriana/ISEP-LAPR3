package lapr.project.mapper;

import lapr.project.mapper.dto.ShipDetailsDto;
import lapr.project.model.Ship;
import lapr.project.model.ShipLocation;
import lapr.project.model.ShipLocationBST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipDetailsMapperTest {

    Ship ship;

    ShipDetailsMapper mapper;

    ShipLocationBST<ShipLocation> tree;



    @BeforeAll
    public void setUp() throws ParseException {
        //ship = new Ship("211331640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,79,13,tree);

    }

    /*@Test
    public void toModel() {
        mapper = new ShipDetailsMapper();
        ShipDetailsDto ship1 = new ShipDetailsDto("211331640","SEOUL EXPRESS","IMO2113432","DHBN",70,294,32,13);
        ShipDetailsDto ship2 = mapper.toDto(ship);
        assertEquals(ship1, ship2);
    }

    @Test
    public void toDto() {
        mapper = new ShipDetailsMapper();
        ShipDetailsDto ship1 = new ShipDetailsDto("211331640","SEOUL EXPRESS","IMO2113432","DHBN",70,294,32,13);
        ShipDetailsDto ship2 = mapper.toDto(ship);

        assertEquals(ship1.getMMSI(),ship2.getMMSI());
        assertEquals(ship1.getName(),ship2.getName());
        assertEquals(ship1.getShipID(),ship2.getShipID());
        assertEquals(ship1.getCallSign(),ship2.getCallSign());
        assertEquals(ship1.getVesselType(), ship2.getVesselType(),0.0);
        assertEquals(ship1.getLength(), ship2.getLength(),0.0);
        assertEquals(ship1.getWidth(),ship2.getWidth(),0.0);
        assertEquals(ship.getDraft(), ship2.getDraft(),0.0);

    }*/
}