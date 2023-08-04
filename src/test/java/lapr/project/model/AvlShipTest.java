package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.hierarchical.Node;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvlShipTest {

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

    public AvlShipTest() throws ParseException {
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
    void insertNull() {
        int treeSize = ships.size();
        ships.insert(null);

        assertEquals(treeSize, ships.size());
    }

    @Test
    void insertNullRoot() {
        BstShip<Ship> bstShip = new AvlShip();
        Ship ship = new Ship();
        ship.setMMSI("123456789");
        bstShip.insert(ship);

        assertEquals(1, bstShip.size());
    }

    @Test
    void insertEqualElements() {
        BstShip<Ship> bstShip = new AvlShip();
        Ship ship = new Ship();
        Ship ship2 = ship;
        ship.setMMSI("123456789");
        bstShip.insert(ship);
        bstShip.insert(ship2);

        assertEquals(1, bstShip.size());
    }

    @Test
    void insertBiggerElement() {
        BstShip<Ship> bstShip = new AvlShip();
        Ship ship = new Ship();
        Ship ship2 = new Ship();
        ship.setMMSI("123456789");
        ship2.setMMSI("123456790");
        bstShip.insert(ship);
        bstShip.insert(ship2);

        BstShip.Node nodeFound =  bstShip.find(ship);
        boolean result = nodeFound.getRight().getShip().equals(ship2);

        assertTrue(result);

    }

    @Test
    void insertSmallerElement() {
        BstShip<Ship> bstShip = new AvlShip();
        Ship ship = new Ship();
        Ship ship2 = new Ship();
        ship.setMMSI("123456789");
        ship2.setMMSI("123456788");
        bstShip.insert(ship);
        bstShip.insert(ship2);

        BstShip.Node nodeFound =  bstShip.find(bstShip.root(), ship);
        boolean result = nodeFound.getLeft().getShip().equals(ship2);

        assertTrue(result);
    }

    @Test
    void testEqualsObject() {
        BstShip<Ship> bstShip = new AvlShip();
        BstShip<Ship> bstShip2;
        Ship ship = new Ship();
        ship.setMMSI("123456789");

        bstShip.insert(ship);
        bstShip2 = bstShip;

        assertTrue(bstShip.equals(bstShip2));
    }

    @Test
    void testEqualsNull() {
        BstShip<Ship> bstShip = new AvlShip();

        Ship ship = new Ship();
        ship.setMMSI("123456789");

        bstShip.insert(ship);

        assertFalse(bstShip.equals(null));
    }

    @Test
    void testEqualsClass() {
        BstShip<Ship> bstShip = new AvlShip();

        Ship ship = new Ship();
        ship.setMMSI("123456789");

        bstShip.insert(ship);

        assertFalse(bstShip.equals("Arvore"));
    }

    @Test
    void testEqualsFinal() {
        BstShip<Ship> bstShip = new AvlShip();
        BstShip<Ship> bstShip2 = new AvlShip();
        Ship ship = new Ship();
        ship.setMMSI("123456789");

        bstShip.insert(ship);
        bstShip2.insert(ship);

        assertTrue(bstShip.equals(bstShip2));

    }
}