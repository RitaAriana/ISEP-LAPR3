package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class BstShipTest {

    List<ShipLocation> arr = new ArrayList<>();

    String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02"};

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    ShipLocationBST<ShipLocation> tree;

    BstShip<Ship> ships;

    Ship ship;

    ShipLocation location1;
    ShipLocation location2;
    ShipLocation location3;
    ShipLocation location4;
    ShipLocation location5;
    ShipLocation location6;

    public BstShipTest() throws ParseException {
        location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"36","-122",19,145,"147","B");
        location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"36","-122",19,145,"147","B");
        location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","-122",19,145,"147","B");
        location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"35","-122",19,145,"147","B");
        location5 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"37","-122",19,145,"147","B");
        location6 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"38","-122",19,145,"147","B");
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
    }

    @BeforeEach
    public void setUp(){
        tree = new ShipLocationAVL();
        ships = new AvlShip();
        for(ShipLocation i :arr)
            tree.insert(i);
        ship = new Ship("211331640",",SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
        ships.insert(ship);
    }

    @Test
    public void getShipByMmsiCodeExist() {
        assertEquals(ship,ships.getShipByMmsiCode("211331640"));
    }

    @Test
    public void getShipByMmsiCodeNotExist() {
        assertNull(ships.getShipByMmsiCode("211331643"));
    }

    @Test
    public void insertLocations(){
        Ship ship = new Ship("212951640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13, location4);
        assertEquals(1, ship.getShipPosition().size());
    }

    @Test
    public void insertLocations2(){
        Ship ship = new Ship("211331641","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13, location3);
        ship.getShipPosition().insert(location4);
        ship.getShipPosition().insert(location5);
        assertEquals(3, ship.getShipPosition().size());
    }

    @Test
    public void isNotEmpty() throws ParseException {
        Assertions.assertFalse(ships.isEmpty());
    }

    @Test
    public void isEmpty() throws ParseException {
        BstShip bstTree = new AvlShip();
        Assertions.assertTrue(bstTree.isEmpty());
    }

    @Test
    public void smallestElement() throws ParseException {
        BstShip bstTree = new AvlShip();
        Ship result = bstTree.smallestElement();
        assertNull(result);
    }

    @Test
    public void smallestElement02() throws ParseException {
        Ship result = ships.smallestElement();
        assertEquals(ship,result);
    }

    @Test
    public void  getIntendedPairsOfShips() throws ParseException {
        ShipLocationBST<ShipLocation> positions1 = new ShipLocationAVL();
        ShipLocationBST<ShipLocation> positions2 = new ShipLocationAVL();

        ShipLocation location1 = new ShipLocation("366998510", dateFormatter.parse("31-12-2020 00:00"),"52.97875","-122.41981",8f,-58.4f,"340", "B");

        ShipLocation location3 = new ShipLocation("367122220", dateFormatter.parse("31-12-2020 00:00"),"52.97874","-122.42079",3.6f,3.7f,"357", "B");
        ShipLocation location4 = new ShipLocation("367122220", dateFormatter.parse("31-12-2020 22:59"),"37.82189","-122.31172",0,179.1f,"288","B");

        positions1.insert(location1);
        positions2.insert(location3);
        positions2.insert(location4);

        Ship ship1 = new Ship("366998510","LIBERTY","IMO7717626",1,12,"WDC2845",31,29,29,"31",3.9f,positions1);
        Ship ship2 = new Ship("367122220","REVOLUTION","IMO7717627",1,12,"WDC2846",31,29,29,"31",3.9f,positions2);

        Company companyTest = new Company();
        companyTest.getBstShip().insert(ship1);
        companyTest.getBstShip().insert(ship2);

        List<TreeMap<Double,String>> result = companyTest.getBstShip().getIntendedPairsOfShips();
        List<TreeMap<Double,String>> esperado = new ArrayList<>();
        assertEquals(esperado,result);
    }

    @Test
    public void  getIntendedPairsOfShips02() throws ParseException {
        ShipLocationBST<ShipLocation> positions1 = new ShipLocationAVL();
        ShipLocationBST<ShipLocation> positions2 = new ShipLocationAVL();

        ShipLocation location1 = new ShipLocation("366998510", dateFormatter.parse("31-12-2020 00:00"),"52.97875","-122.41981",8f,-58.4f,"340", "B");
        ShipLocation location2 = new ShipLocation("366998510", dateFormatter.parse("31-12-2020 23:10"),"26.52603","-122.31122",0,-115.4f,"104","B");

        ShipLocation location3 = new ShipLocation("367122220", dateFormatter.parse("31-12-2020 00:00"),"52.97874","-122.42079",3.6f,3.7f,"357", "B");

        positions1.insert(location1);
        positions1.insert(location2);
        positions2.insert(location3);

        Ship ship1 = new Ship("366998510","LIBERTY","IMO7717626",1,12,"WDC2845",31,29,29,"31",3.9f,positions1);
        Ship ship2 = new Ship("367122220","REVOLUTION","IMO7717627",1,12,"WDC2846",31,29,29,"31",3.9f,positions2);

        Company companyTest = new Company();
        companyTest.getBstShip().insert(ship1);
        companyTest.getBstShip().insert(ship2);

        List<TreeMap<Double,String>> result = companyTest.getBstShip().getIntendedPairsOfShips();
        List<TreeMap<Double,String>> esperado = new ArrayList<>();
        TreeMap<Double,String> infoPair = new TreeMap<>(Collections.reverseOrder());
        esperado.add(infoPair);
        assertEquals(esperado,result);
    }

    @Test
    public void find() throws ParseException {
        BstShip bstTree = new AvlShip();
        Assertions.assertNull(bstTree.find(null,null));
    }

    @Test
    void heightTest(){
        Ship ship1 = new Ship();
        Ship ship2 = new Ship();
        Ship ship3 = new Ship();
        Ship ship4 = new Ship();
        Ship ship5 = new Ship();
        Ship ship6 = new Ship();
        Ship ship7 = new Ship();

        ship1.setMMSI("123456781");
        ship2.setMMSI("123456782");
        ship3.setMMSI("123456783");
        ship4.setMMSI("123456784");
        ship5.setMMSI("123456785");
        ship6.setMMSI("123456786");
        ship7.setMMSI("123456787");

        BstShip<Ship> bstShip = new AvlShip();
        bstShip.insert(ship1);
        bstShip.insert(ship2);
        bstShip.insert(ship3);
        bstShip.insert(ship4);
        bstShip.insert(ship5);
        bstShip.insert(ship6);
        bstShip.insert(ship7);

        assertEquals(2, bstShip.height());
    }

    @Test
    void inOrderTest(){
        Ship ship1 = new Ship();
        Ship ship2 = new Ship();
        Ship ship3 = new Ship();
        Ship ship4 = new Ship();
        Ship ship5 = new Ship();
        Ship ship6 = new Ship();
        Ship ship7 = new Ship();

        ship1.setMMSI("123456781");
        ship2.setMMSI("123456782");
        ship3.setMMSI("123456783");
        ship4.setMMSI("123456784");
        ship5.setMMSI("123456785");
        ship6.setMMSI("123456786");
        ship7.setMMSI("123456787");

        BstShip<Ship> bstShip = new AvlShip();
        bstShip.insert(ship1);
        bstShip.insert(ship2);
        bstShip.insert(ship3);
        bstShip.insert(ship4);
        bstShip.insert(ship5);
        bstShip.insert(ship6);
        bstShip.insert(ship7);

        Iterator iterator = bstShip.inOrder().iterator();

        boolean correctSequence = true;

        String shipMMSI1 = ((Ship) iterator.next()).getMMSI();
        while(iterator.hasNext() && correctSequence) {
            String shipMMSI2 = ((Ship) iterator.next()).getMMSI();
            if(shipMMSI1.compareTo(shipMMSI2) > 0)
                correctSequence = false;
            shipMMSI1 = shipMMSI2;
        }

        assertTrue(correctSequence);
    }
}