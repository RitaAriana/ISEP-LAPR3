package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShowPairsOfShipsControllerTest {

    Company company;

    ShowPairsOfShipsController controller;

    Ship ship1;
    Ship ship2;
    ShipLocationBST<ShipLocation> positions1 = new ShipLocationAVL();
    ShipLocationBST<ShipLocation> positions2 = new ShipLocationAVL();

    ShipLocation location1;
    ShipLocation location2;

    ShipLocation location3;
    ShipLocation location4;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public ShowPairsOfShipsControllerTest() throws ParseException {
        location1 = new ShipLocation("366998510", dateFormatter.parse("31-12-2020 00:00"),"37.8946","-122.41981",8f,-58.4f,"340", "B");
        location2 = new ShipLocation("366998510", dateFormatter.parse("31-12-2020 23:10"),"37.82185","-122.31122",0,-115.4f,"104","B");

        location3 = new ShipLocation("367122220", dateFormatter.parse("31-12-2020 00:00"),"37.9094","-122.42079",3.6f,3.7f,"357", "B");
        location4 = new ShipLocation("367122220", dateFormatter.parse("31-12-2020 22:59"),"37.82189","-122.31172",0,179.1f,"288","B");

        positions1.insert(location1);
        positions1.insert(location2);
        positions2.insert(location3);
        positions2.insert(location4);

        ship1 = new Ship("366998510","LIBERTY","IMO7717626",1,12,"WDC2845",31,29,29,"31",3.9f,positions1);
        ship2 = new Ship("367122220","REVOLUTION","IMO7717627",1,12,"WDC2846",31,29,29,"31",3.9f,positions2);

        company = new Company();
        company.getBstShip().insert(ship1);
        company.getBstShip().insert(ship2);
    }


    @BeforeEach
    public void setUp(){
        controller = new ShowPairsOfShipsController(company);
    }

    @Test
    public void ctrlr() {
        ShowPairsOfShipsController ctrlr = new ShowPairsOfShipsController();
        assertEquals(App.getInstance().getCompany(),ctrlr.getCompany());
    }

    @Test
    public void getPairsOfShips01() throws IOException {
        List<TreeMap<Double,String>> result = controller.getPairsOfShip();
        List<TreeMap<Double,String>> esperado = new ArrayList<>();
        TreeMap<Double,String> infoPair = new TreeMap<>(Collections.reverseOrder());
        String stringWithAllInfo = String.format(" %s  %s", ship1.getMMSI(), ship2.getMMSI());
        Double travelDistanceDifference = Math.abs(positions1.getTravelledDistance() - positions2.getTravelledDistance());
        infoPair.put(travelDistanceDifference, stringWithAllInfo);
        esperado.add(infoPair);
        assertEquals(esperado,result);
    }

    @Test
    public void getPairsOfShips02() throws IOException {
        Company company = new Company();
        ShowPairsOfShipsController ctrlrAux = new ShowPairsOfShipsController(company);
        List<TreeMap<Double,String>> result = ctrlrAux.getPairsOfShip();
        List<TreeMap<Double,String>> esperado = new ArrayList<>();
        assertEquals(esperado,result);
    }


}