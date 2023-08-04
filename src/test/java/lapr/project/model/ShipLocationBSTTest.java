package lapr.project.model;

import lapr.project.controller.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class ShipLocationBSTTest {


    List<ShipLocation> arr = new ArrayList<>();

    String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02", "12-12-2020 17:02"};

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    ShipLocationBST<ShipLocation> tree;

    ShipLocation location1;
    ShipLocation location2;
    ShipLocation location3;
    ShipLocation location4;

    Company company;

    public ShipLocationBSTTest() throws ParseException {
        location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"-45","-122",19,145,"147","B");
        location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"43","-122",11,122,"147","B");
        location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","180",23,98,"147","B");
        location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[3]),"91","181",35,200,"147","B");
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
        arr.add(location4);
        company = App.getInstance().getCompany();
    }

    @BeforeEach
    public void setUp(){
        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = new Ship("211331640",",SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
    }

    @Test
    public void isNotEmpty() throws ParseException {
        Assertions.assertFalse(tree.isEmpty());
    }

    @Test
    public void isEmpty() throws ParseException {
        ShipLocationBST bstTree = new ShipLocationAVL();
        Assertions.assertTrue(bstTree.isEmpty());
    }

    @Test
    public void find() throws ParseException {
        ShipLocationBST bstTree = new ShipLocationAVL();
        Assertions.assertNull(bstTree.find(null,null));
    }

    @Test
    public void find01() throws ParseException {
        ShipLocationBST.Node<ShipLocation> test = tree.find(tree.root,location3);
        Assertions.assertEquals(location3, test.getShipLocation());
    }

    @Test
    public void find02() throws ParseException {
        ShipLocationBST.Node<ShipLocation> test = tree.find(tree.root,location2);
        Assertions.assertEquals(location2, test.getShipLocation());
    }

    @Test
    public void find03() throws ParseException {
        ShipLocationBST.Node<ShipLocation> test = tree.find(tree.root,location1);
        Assertions.assertEquals(location1, test.getShipLocation());
    }

    @Test
    public void getPositionalMessagesNotExist01() throws ParseException {
        String[] datas = {"30-12-2020 01:25","30-12-2020 17:02"};
        List<String> expected = new ArrayList<>();
        List<String> result=tree.getPositionalMessages(dateFormatter.parse(datas[0]),dateFormatter.parse(datas[1]));
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void getPositionalMessagesNotExist02() throws ParseException {
        String[] datas = {"31-12-2020 18:25","31-12-2020 20:02"};
        List<String> expected = new ArrayList<>();
        List<String> result=tree.getPositionalMessages(dateFormatter.parse(datas[0]),dateFormatter.parse(datas[1]));
        assertEquals(expected,result);
    }

    @Test
    public void getPositionalMessagesExist01() throws ParseException {
        String[] datas = {"31-12-2020 01:25","31-12-2020 02:02"};
        List<String> expected = new ArrayList<>();
        expected.add(location1.toString());
        List<String>result=tree.getPositionalMessages(dateFormatter.parse(datas[0]),dateFormatter.parse(datas[1]));
        assertEquals(expected,result);
    }

    @Test
    public void getPositionalMessagesExist02() throws ParseException {
        String[] datas = {"31-12-2020 17:00","31-12-2020 17:02"};
        List<String> expected = new ArrayList<>();
        expected.add(location3.toString());
        List<String>result = tree.getPositionalMessages(dateFormatter.parse(datas[0]),dateFormatter.parse(datas[1]));
        assertEquals(expected,result);
    }

    @Test
    public void getPositionalMessagesExist03() throws ParseException {
        String[] datas = {"31-12-2020 16:00","31-12-2020 16:30"};
        List<String> expected = new ArrayList<>();
        expected.add(location2.toString());
        List<String>result=tree.getPositionalMessages(dateFormatter.parse(datas[0]),dateFormatter.parse(datas[1]));
        assertEquals(expected,result);
    }

    @Test
    public void getPositionalMessagesExist04() throws ParseException {
        String[] datas = {"31-12-2020 12:00","31-12-2020 18:30"};
        List<String> expected = new ArrayList<>();
        expected.add(location2.toString());
        expected.add(location3.toString());
        List<String>result=tree.getPositionalMessages(dateFormatter.parse(datas[0]),dateFormatter.parse(datas[1]));
        assertEquals(expected,result);
    }

    @Test
    public void verifyTreeSizeIsEmpty(){
        assertEquals(0, new AvlShip().size());
    }

    @Test
    public void verifyTreeSizeEqualsOne(){
        Ship ship = new Ship("211331640",",SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
        Company company = new Company();
        company.getBstShip().insert(ship);
        assertEquals(1, company.getBstShip().size());
    }

    @org.junit.jupiter.api.Test
    void getStartBase() throws ParseException {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getStartBase(), dateFormatter.parse(auxDatas[3]));
    }

    @org.junit.jupiter.api.Test
    void getEndBase() throws ParseException {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getEndBase(), dateFormatter.parse(auxDatas[2]));
    }

    @org.junit.jupiter.api.Test
    void getTotalMovements() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getTotalMovements(), 3);

    }

    @org.junit.jupiter.api.Test
    void getTotalMovementsTime() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getTotalMovementsTime(), "456:00:00");
    }


    @org.junit.jupiter.api.Test
    void getMaximumSog() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getMaximumSog(), 35, 0.01);
    }

    @org.junit.jupiter.api.Test
    void getMeanSog() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getMeanSog(), 22, 0.01);

    }

    @org.junit.jupiter.api.Test
    void getMaximumCog() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getMaximumCog(), 200, 0.01);

    }

    @org.junit.jupiter.api.Test
    void getMeanCog() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getMeanCog(), 141.25, 0.01);
    }

    @org.junit.jupiter.api.Test
    void latitudeDepartureEqualsNotAvailable() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getLatitudeDeparture(), "not available");
    }

    @org.junit.jupiter.api.Test
    void getLatitudeDeparture() throws ParseException {
        String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02", "12-12-2020 17:02"};
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ShipLocation location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"36","-122",19,145,"147","B");
        ShipLocation location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"36","-122",11,122,"147","B");
        ShipLocation location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","-122",23,98,"147","B");
        ShipLocation location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[3]),"-56","-122",35,200,"147","B");
        List<ShipLocation> arr = new ArrayList<>();
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
        arr.add(location4);
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        for(ShipLocation i :arr)
            shipLocationBST.insert(i);

        assertEquals(shipLocationBST.getLatitudeDeparture(), "-56");
    }

    @org.junit.jupiter.api.Test
    void longitudeDepartureEqualsNotAvailable() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getLongitudeDeparture(), "not available");
    }

    @org.junit.jupiter.api.Test
    void getLongitudeDeparture() throws ParseException {
        String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02", "12-12-2020 17:02"};
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ShipLocation location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"36","100",19,145,"147","B");
        ShipLocation location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"36","-100",11,122,"147","B");
        ShipLocation location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","120",23,98,"147","B");
        ShipLocation location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[3]),"-56","-170",35,200,"147","B");
        List<ShipLocation> arr = new ArrayList<>();
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
        arr.add(location4);
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        for(ShipLocation i :arr)
            shipLocationBST.insert(i);

        assertEquals(shipLocationBST.getLongitudeDeparture(), "-170");
    }

    @org.junit.jupiter.api.Test
    void getArrivalLatitude() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getArrivalLatitude(), "36");
    }

    @org.junit.jupiter.api.Test
    void getArrivalLongitude() {
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);

        assertEquals(tree.getArrivalLongitude(), "180");
    }


    @org.junit.jupiter.api.Test
    void getTravelledDistanceWithPositionsNotAvailable() throws ParseException {
        String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02", "12-12-2020 17:02"};
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ShipLocation location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"91","181",19,145,"147","B");
        ShipLocation location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"91","181",11,122,"147","B");
        ShipLocation location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"91","181",23,98,"147","B");
        ShipLocation location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[3]),"91","181",35,200,"147","B");
        List<ShipLocation> arr = new ArrayList<>();
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
        arr.add(location4);
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        for(ShipLocation i :arr)
            shipLocationBST.insert(i);

        assertEquals(shipLocationBST.getTravelledDistance(), 0, 0.0);

    }

    @org.junit.jupiter.api.Test
    void getTravelledDistanceWithOnePositionNotAvailable() throws ParseException {
        String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02", "12-12-2020 17:02"};
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ShipLocation location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"36","132",19,145,"147","B");
        ShipLocation location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"91","98",11,122,"147","B");
        ShipLocation location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","-70",23,98,"147","B");
        ShipLocation location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[3]),"-56","-122",35,200,"147","B");
        List<ShipLocation> arr = new ArrayList<>();
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
        arr.add(location4);
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        for(ShipLocation i :arr)
            shipLocationBST.insert(i);

       assertEquals(shipLocationBST.getTravelledDistance(), 14203.35, 0.01);
    }

    @org.junit.jupiter.api.Test
    void getTravelledDistanceWithAllPointsCorrect() throws ParseException {
        String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02", "12-12-2020 17:02"};
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ShipLocation location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"36","132",19,145,"147","B");
        ShipLocation location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"90","98",11,122,"147","B");
        ShipLocation location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","-70",23,98,"147","B");
        ShipLocation location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[3]),"-56","-122",35,200,"147","B");
        List<ShipLocation> arr = new ArrayList<>();
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
        arr.add(location4);
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        for(ShipLocation i :arr)
            shipLocationBST.insert(i);

        assertEquals(shipLocationBST.getTravelledDistance(), 26212.41, 0.01);
    }

    @org.junit.jupiter.api.Test
    void getDeltaDistanceOfDepartureCoordinateNotAvailable(){
        ShipLocationBST<ShipLocation> tree = new ShipLocationAVL();
        for(ShipLocation i : arr)
            tree.insert(i);

        assertEquals(tree.getDeltaDistance(), 0, 0.0);
    }

    @org.junit.jupiter.api.Test
    void getDeltaDistanceOfArrivalCoordinateNotAvailable() throws ParseException {
        String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02", "12-12-2020 17:02"};
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ShipLocation location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"36","132",19,145,"147","B");
        ShipLocation location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"90","98",11,122,"147","B");
        ShipLocation location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","181",23,98,"147","B");
        ShipLocation location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[3]),"-56","-122",35,200,"147","B");
        List<ShipLocation> arr = new ArrayList<>();
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
        arr.add(location4);
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        for(ShipLocation i :arr)
            shipLocationBST.insert(i);

        assertEquals(shipLocationBST.getDeltaDistance(), 0, 0.0);
    }

    @org.junit.jupiter.api.Test
    void getDeltaDistance() throws ParseException {
        String[] auxDatas = {"31-12-2020 01:25","31-12-2020 16:15","31-12-2020 17:02", "12-12-2020 17:02"};
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        ShipLocation location1 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[0]),"36","132",19,145,"147","B");
        ShipLocation location2 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[1]),"90","98",11,122,"147","B");
        ShipLocation location3 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[2]),"36","170",23,98,"147","B");
        ShipLocation location4 = new ShipLocation("211331640", dateFormatter.parse(auxDatas[3]),"-56","-122",35,200,"147","B");
        List<ShipLocation> arr = new ArrayList<>();
        arr.add(location1);
        arr.add(location2);
        arr.add(location3);
        arr.add(location4);
        ShipLocationBST<ShipLocation> shipLocationBST = new ShipLocationAVL();
        for(ShipLocation i :arr)
            shipLocationBST.insert(i);

        assertEquals(shipLocationBST.getDeltaDistance(), 12068.15, 0.2);
    }

    @Test
    public void getSpecificDatePeriod() throws ParseException {
        List<ShipLocation> list = new ArrayList<>();
        tree.getSpecificDatePeriod(null,dateFormatter.parse("26-11-2021 13:00"),dateFormatter.parse("26-11-2021 14:00"),list);
        assertEquals(0,list.size());
    }

    @Test
    public void getSpecificDatePeriod01() throws ParseException {
        List<ShipLocation> list = new ArrayList<>();
        tree.getSpecificDatePeriod(tree.root, dateFormatter.parse("31-12-2020 01:15"),dateFormatter.parse("31-12-2020 01:35"),list);
        assertEquals(1,list.size());
    }

    @Test
    public void smallestElement() throws ParseException {
        ShipLocationBST bstTree = new ShipLocationAVL();
        ShipLocation result = bstTree.smallestElement();
        assertNull(result);
    }

    @Test
    public void smallestElement02() throws ParseException {
        ShipLocation bstTree = tree.smallestElement();
        assertEquals(location4,bstTree);
    }

    @Test
    void heightTest() throws ParseException {
        ShipLocation shipLocation1 = new ShipLocation();
        ShipLocation shipLocation2 = new ShipLocation();
        ShipLocation shipLocation3 = new ShipLocation();
        ShipLocation shipLocation4 = new ShipLocation();
        ShipLocation shipLocation5 = new ShipLocation();
        ShipLocation shipLocation6 = new ShipLocation();
        ShipLocation shipLocation7 = new ShipLocation();

        shipLocation1.setMessageTime(dateFormatter.parse("21-12-2020 10:00"));
        shipLocation2.setMessageTime(dateFormatter.parse("22-12-2020 10:00"));
        shipLocation3.setMessageTime(dateFormatter.parse("23-12-2020 10:00"));
        shipLocation4.setMessageTime(dateFormatter.parse("24-12-2020 10:00"));
        shipLocation5.setMessageTime(dateFormatter.parse("25-12-2020 10:00"));
        shipLocation6.setMessageTime(dateFormatter.parse("26-12-2020 10:00"));
        shipLocation7.setMessageTime(dateFormatter.parse("27-12-2020 10:00"));

        ShipLocationBST<ShipLocation> shipLocationAVL = new ShipLocationAVL();

        shipLocationAVL.insert(shipLocation1);
        shipLocationAVL.insert(shipLocation2);
        shipLocationAVL.insert(shipLocation3);
        shipLocationAVL.insert(shipLocation4);
        shipLocationAVL.insert(shipLocation5);
        shipLocationAVL.insert(shipLocation6);
        shipLocationAVL.insert(shipLocation7);


        assertEquals(2, shipLocationAVL.height());
    }

    @Test
    void inOrderTest() throws ParseException {
        ShipLocation shipLocation1 = new ShipLocation();
        ShipLocation shipLocation2 = new ShipLocation();
        ShipLocation shipLocation3 = new ShipLocation();
        ShipLocation shipLocation4 = new ShipLocation();
        ShipLocation shipLocation5 = new ShipLocation();
        ShipLocation shipLocation6 = new ShipLocation();
        ShipLocation shipLocation7 = new ShipLocation();

        shipLocation1.setMessageTime(dateFormatter.parse("21-12-2020 10:00"));
        shipLocation2.setMessageTime(dateFormatter.parse("22-12-2020 10:00"));
        shipLocation3.setMessageTime(dateFormatter.parse("23-12-2020 10:00"));
        shipLocation4.setMessageTime(dateFormatter.parse("24-12-2020 10:00"));
        shipLocation5.setMessageTime(dateFormatter.parse("25-12-2020 10:00"));
        shipLocation6.setMessageTime(dateFormatter.parse("26-12-2020 10:00"));
        shipLocation7.setMessageTime(dateFormatter.parse("27-12-2020 10:00"));

        ShipLocationBST<ShipLocation> shipLocationAVL = new ShipLocationAVL();

        shipLocationAVL.insert(shipLocation1);
        shipLocationAVL.insert(shipLocation2);
        shipLocationAVL.insert(shipLocation3);
        shipLocationAVL.insert(shipLocation4);
        shipLocationAVL.insert(shipLocation5);
        shipLocationAVL.insert(shipLocation6);
        shipLocationAVL.insert(shipLocation7);

        Iterator iterator = shipLocationAVL.inOrder().iterator();

        boolean correctSequence = true;

        Date shipLocationDate1 = ((ShipLocation) iterator.next()).getMessageTime();
        while(iterator.hasNext() && correctSequence) {
            Date shipLocationDate2 = ((ShipLocation) iterator.next()).getMessageTime();
            if(shipLocationDate1.compareTo(shipLocationDate2) > 0)
                correctSequence = false;
            shipLocationDate1 = shipLocationDate2;
        }

        assertTrue(correctSequence);
    }

}