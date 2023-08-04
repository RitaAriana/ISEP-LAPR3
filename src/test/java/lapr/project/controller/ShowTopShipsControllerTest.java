package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ShowTopShipsControllerTest {

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    String[] auxDatas = {"28-12-2020 01:25","01-01-2021 16:15"};

    @Test
    void getTopNShips() throws ParseException{
        Company company = new Company();
        company.getBstShip().insert(new Ship("366998510","LIBERTY","IMO7717626",1,12,"WDC2845",31,29,29,"31",3.9f, new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"-45","-122",19,145,"147","B")));
        company.getBstShip().insert(new Ship("366998511","LIBERTY","IMO7717626",1,12,"WDC2845",31,29,29,"31",3.9f, new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"-45","-122",19,145,"147","B")));
        ShowTopShipsController showTopShipsController = new ShowTopShipsController(company);
        assertEquals("For the Vessel Type: 31, this is the data of the Ships:\n\n" +
                        "The ship with the 366998510 MMSI Code, " + String.format("traveled %.2f Kilometers at a Mean SOG of: %.2f", 0f, 19f) + "\n" +
                        "The ship with the 366998511 MMSI Code, " + String.format("traveled %.2f Kilometers at a Mean SOG of: %.2f", 0f, 19f) + "\n" + "\n\n\n"
                , showTopShipsController.getTopNShips(3, dateFormatter.parse("27-12-2020 01:25"), dateFormatter.parse("02-01-2021 16:15")));
    }

    @Test
    void getTopNShipsNoShips() {
        ShowTopShipsController controller = new ShowTopShipsController(new Company());
        assertEquals("There was no ship to demonstrate",controller.getTopNShips(5, new Date(), new Date()));
    }

    @Test
    void getTopNShipsNull() {
        BstShip<Ship> bstShip = new AvlShip();
        boolean nullTree = (bstShip.getTopNShips(5, new Date(), new Date()) != null);
        assertTrue(nullTree);
    }

    @Test
    void checkControllerCompany(){
        ShowTopShipsController controller = new ShowTopShipsController();
        assertEquals(App.getInstance().getCompany(),controller.getCompany());
    }
}