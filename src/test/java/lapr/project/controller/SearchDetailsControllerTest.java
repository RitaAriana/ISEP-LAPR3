package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.WriteForAFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchDetailsControllerTest {

    SearchDetailsController controller;

    WriteForAFile writeForAFile;

    @BeforeEach
    public void setUp(){
        Company company = App.getInstance().getCompany();
        ImportShipController impShipCTR = new ImportShipController();
        impShipCTR.importFile("sships.csv");
        controller = new SearchDetailsController();
        writeForAFile = new WriteForAFile();

    }



   @Test
    public void shipExistByMMSI() {
       Company comp=new Company();

       SearchDetailsController ctr = new SearchDetailsController(comp);

       ShipLocationBST<ShipLocation> tree;
       List<ShipLocation> arr = new ArrayList<>();

       tree = new ShipLocationAVL();
       for(ShipLocation i :arr)
           tree.insert(i);
       Ship ship = new Ship("211331640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
       comp.getBstShip().insert(ship);
        boolean result = ctr.shipExistByMMSI("211331640");
        System.out.println(result);
        Assertions.assertTrue(result);
    }


    @Test
    public void shipNotExistByMMSI() {
        boolean result = controller.shipExistByMMSI("210951111");
        Assertions.assertFalse(result);
    }

    @Test
    public void shipExistByIMO() {
        Company comp=new Company();

        SearchDetailsController ctr = new SearchDetailsController(comp);

        ShipLocationBST<ShipLocation> tree;
        List<ShipLocation> arr = new ArrayList<>();

        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = new Ship("211331640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
        comp.getBstShip().insert(ship);

        Ship result = ctr.shipExistByIMO("IMO2113432");
        boolean flag;
        if (result == null) flag = true;
        else flag = false;
        Assertions.assertFalse(flag);
    }

    @Test
    public void shipNotExistByIMO() {
        Ship result = controller.shipExistByIMO("IMO9999999");
        boolean flag;
        if (result != null) flag = false;
        else flag = true;
        Assertions.assertTrue(flag);
    }

    @Test
    public void shipExistByCallSign() {
        Company comp=new Company();

        SearchDetailsController ctr = new SearchDetailsController(comp);

        ShipLocationBST<ShipLocation> tree;
        List<ShipLocation> arr = new ArrayList<>();

        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = new Ship("211331640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
        comp.getBstShip().insert(ship);

        Ship result = ctr.shipExistByCallSign("DHBN");
        boolean flag;
        if (result == null) flag = true;
        else flag = false;
        Assertions.assertFalse(flag);
    }

    @Test
    public void shipNotExistByCallSign() {
        Ship result = controller.shipExistByCallSign("W3WQ2");
        boolean flag;
        if (result != null) flag = false;
        else flag = true;
        Assertions.assertTrue(flag);
    }


    @Test
    public void getShipDetails() {
        Company comp=new Company();

        SearchDetailsController ctr = new SearchDetailsController(comp);

        ShipLocationBST<ShipLocation> tree;
        List<ShipLocation> arr = new ArrayList<>();

        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = new Ship("211331640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
        comp.getBstShip().insert(ship);
        String expected = "MMSI: 211331640 \n" +
                 "Name: SEOUL EXPRESS \n" +
                 "IMO: IMO2113432 \n" +
                 "Call Sign: DHBN \n" +
                 "Vessel Type: 70 \n" +
                 "Length: 294.0 \n" +
                 "Width: 32.0 \n" +
                 "Draft: 13.0";
        ctr.shipExistByIMO("IMO2113432");
        Assertions.assertEquals(expected, ctr.getShipDetails());
    }

    @Test
    public void getShipDetailsByMMSI() throws IOException {

        Company comp=new Company();

        SearchDetailsController ctr = new SearchDetailsController(comp);

        ShipLocationBST<ShipLocation> tree;
        List<ShipLocation> arr = new ArrayList<>();

        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = new Ship("211331640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
        comp.getBstShip().insert(ship);
        String expected = "MMSI: 211331640 \n" +
                "Name: SEOUL EXPRESS \n" +
                "IMO: IMO2113432 \n" +
                "Call Sign: DHBN \n" +
                "Vessel Type: 70 \n" +
                "Length: 294.0 \n" +
                "Width: 32.0 \n" +
                "Draft: 13.0";

        ctr.shipExistByMMSI("211331640");
        File file = new File(".\\outputs\\ShipDetails");
        writeForAFile.writeForAFile(ctr.getShipDetails(),"211331640",file, false);

        Assertions.assertEquals(expected, ctr.getShipDetails());

    }

    @Test
    public void getShipDetailsByIMO() throws IOException {

        Company comp=new Company();

        SearchDetailsController ctr = new SearchDetailsController(comp);

        ShipLocationBST<ShipLocation> tree;
        List<ShipLocation> arr = new ArrayList<>();

        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = new Ship("211331640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
        comp.getBstShip().insert(ship);
        String expected = "MMSI: 211331640 \n" +
                "Name: SEOUL EXPRESS \n" +
                "IMO: IMO2113432 \n" +
                "Call Sign: DHBN \n" +
                "Vessel Type: 70 \n" +
                "Length: 294.0 \n" +
                "Width: 32.0 \n" +
                "Draft: 13.0";

        ctr.shipExistByIMO("IMO2113432");
        File file = new File(".\\outputs\\ShipDetails");
        writeForAFile.writeForAFile(ctr.getShipDetails(),"IMO2113432",file, false);

        //ctr.shipExistByIMO("IMO2113432");
        Assertions.assertEquals(expected, ctr.getShipDetails());
    }

    @Test
    public void getShipDetailsByCallsign() throws IOException {
        Company comp=new Company();

        SearchDetailsController ctr = new SearchDetailsController(comp);

        ShipLocationBST<ShipLocation> tree;
        List<ShipLocation> arr = new ArrayList<>();

        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = new Ship("211331640","SEOUL EXPRESS","IMO2113432",1,280,"DHBN",70,294,32,"79",13,tree);
        comp.getBstShip().insert(ship);
        String expected = "MMSI: 211331640 \n" +
                "Name: SEOUL EXPRESS \n" +
                "IMO: IMO2113432 \n" +
                "Call Sign: DHBN \n" +
                "Vessel Type: 70 \n" +
                "Length: 294.0 \n" +
                "Width: 32.0 \n" +
                "Draft: 13.0";

        ctr.shipExistByCallSign("DHBN");
        File file = new File(".\\outputs\\ShipDetails");
        writeForAFile.writeForAFile(ctr.getShipDetails(),"DHBN",file, false);
        Assertions.assertEquals(expected, ctr.getShipDetails());
    }

}