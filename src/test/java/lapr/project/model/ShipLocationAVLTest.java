package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipLocationAVLTest {

    List<ShipLocation> lst = new ArrayList<>();

    String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02"};

    ShipLocation location1;
    ShipLocation location2;
    ShipLocation location3;
    ShipLocation location4;
    ShipLocation location5;
    ShipLocation location6;

    ShipLocationBST<ShipLocation> tree;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public ShipLocationAVLTest() throws ParseException {
        location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"36","-122",19,145,"147","B");
        location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"36","-122",19,145,"147","B");
        location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","-122",19,145,"147","B");
        location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"35","-122",19,145,"147","B");
        location5 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"37","-122",19,145,"147","B");
        location6 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"38","-122",19,145,"147","B");

    }

    @BeforeEach
    public void setUp(){
        tree = new ShipLocationAVL();
        for(ShipLocation i :lst)
            tree.insert(i);
    }

    @Test
    void insertNull() {
        int treeSize = tree.size();
        tree.insert(null);

        assertEquals(treeSize, tree.size());
    }

    @Test
    void insertNullRoot() {
        ShipLocationBST<ShipLocation> shipLocationAvl = new ShipLocationAVL();
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setMMSI("123456789");
        shipLocationAvl.insert(shipLocation);

        assertEquals(1, shipLocationAvl.size());
    }

    @Test
    void insertEqualElements() throws ParseException {
        ShipLocationBST shipLocationBST = new ShipLocationAVL();
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setMessageTime(dateFormatter.parse("23-12-2020 15:15"));
        ShipLocation shipLocation2 = shipLocation;

        shipLocationBST.insert(shipLocation);
        shipLocationBST.insert(shipLocation2);

        assertEquals(1, shipLocationBST.size());
    }

    @Test
    void insertBiggerElement() throws ParseException {
        ShipLocationBST shipLocationBST = new ShipLocationAVL();
        ShipLocation shipLocation = new ShipLocation();
        ShipLocation shipLocation2 = new ShipLocation();
        Date data1 = dateFormatter.parse("23-12-2020 15:15");
        Date data2 = dateFormatter.parse("29-12-2020 15:15");
        shipLocation.setMessageTime(data1);
        shipLocation2.setMessageTime(data2);
        shipLocationBST.insert(shipLocation);
        shipLocationBST.insert(shipLocation2);

        ShipLocationBST.Node nodeFound =  shipLocationBST.find(shipLocation);
        boolean result = nodeFound.getRight().getShipLocation().equals(shipLocation2);

        assertTrue(result);

    }

    @Test
    void insertSmallerElement() throws ParseException {
        ShipLocationBST shipLocationBST = new ShipLocationAVL();
        ShipLocation shipLocation = new ShipLocation();
        ShipLocation shipLocation2 = new ShipLocation();
        Date data1 = dateFormatter.parse("26-12-2020 15:15");
        Date data2 = dateFormatter.parse("24-12-2020 15:15");
        shipLocation.setMessageTime(data1);
        shipLocation2.setMessageTime(data2);
        shipLocationBST.insert(shipLocation);
        shipLocationBST.insert(shipLocation2);

        ShipLocationBST.Node nodeFound =  shipLocationBST.find(shipLocation);
        boolean result = nodeFound.getLeft().getShipLocation().equals(shipLocation2);

        assertTrue(result);
    }

    @Test
    void testEqualsObject() throws ParseException {
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        ShipLocationBST<ShipLocation> shipLocationBST2;
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setMessageTime(dateFormatter.parse("28-12-2020 15:15"));


        shipLocationBST.insert(shipLocation);
        shipLocationBST2 = shipLocationBST;

        assertTrue(shipLocationBST.equals(shipLocationBST2));
    }

    @Test
    void testEqualsNull() throws ParseException {
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();

        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setMessageTime(dateFormatter.parse("28-12-2020 15:15"));

        shipLocationBST.insert(shipLocation);

        assertFalse(shipLocation.equals(null));
    }

    @Test
    void testEqualsClass() throws ParseException {
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();

        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setMessageTime(dateFormatter.parse("28-12-2020 15:15"));

        shipLocationBST.insert(shipLocation);

        assertFalse(shipLocationBST.equals("Arvore"));
    }

    @Test
    void testEqualsFinal() throws ParseException {
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        ShipLocationBST<ShipLocation> shipLocationBST2 = new ShipLocationAVL();
        ShipLocation shipLocation = new ShipLocation();
        shipLocation.setMessageTime(dateFormatter.parse("28-12-2020 15:15"));

        shipLocationBST.insert(shipLocation);
        shipLocationBST2.insert(shipLocation);

        assertTrue(shipLocationBST.equals(shipLocationBST2));

    }
}