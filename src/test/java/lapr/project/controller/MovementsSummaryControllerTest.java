package lapr.project.controller;

import lapr.project.mapper.dto.SummaryDto;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovementsSummaryControllerTest {

    MovementsSummaryController movementsSummaryController;

    List<ShipLocation> arr = new ArrayList<>();

    String[] auxDates = {"31-12-2020 23:31","31-12-2020 23:28"};

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    ShipLocationBST<ShipLocation> tree;

    ShipLocation location1;
    ShipLocation location2;


    ShowPositionalMessagesController controller;

    public MovementsSummaryControllerTest() throws ParseException {
        location1 = new ShipLocation("229961000", dateFormatter.parse(auxDates[0]), "54.23188", "-130.33667", 0.1f, 82.8f, "0", "A");
        location2 = new ShipLocation("229961000", dateFormatter.parse(auxDates[1]), "54.23184", "-130.33702", 0.1f, 34.6f, "0", "A");
        arr.add(location1);
        arr.add(location2);
    }

    @BeforeEach
    public void setUp(){
        ImportShipController impShipCTR = new ImportShipController();
        impShipCTR.importFile("sships.csv");
        impShipCTR.importShips();
        movementsSummaryController = new MovementsSummaryController();
    }

    @Test
    void getShipByMmsiCode() {
        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = new Ship("229961000","ARABELLA","IMO9700122",0,0,"9HA3752",70,199,32,"NA",13.3f, tree);
        assertEquals(ship.toString(), movementsSummaryController.getShipByMmsiCode("229961000").toString());
    }

    @Test
    void createSummaryForShip() {
        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = movementsSummaryController.getShipByMmsiCode("229961000");
        Summary summary = new Summary(ship);

        assertEquals(summary, movementsSummaryController.createSummaryForShip(ship));
    }

    @Test
    void createSummaryDto() throws IOException {
        tree = new ShipLocationAVL();
        for(ShipLocation i :arr)
            tree.insert(i);
        Ship ship = movementsSummaryController.getShipByMmsiCode("229961000");
        Summary summary = new Summary(ship);
        SummaryDto summaryDto = new SummaryDto(summary);

        assertEquals(summaryDto.toString(), movementsSummaryController.createSummaryDto(summary).toString());
    }

    @Test
    void shipExist() {
        assertTrue(movementsSummaryController.shipExist("229961000"));
    }


    @Test
    void shipNotExist(){
        assertFalse(movementsSummaryController.shipExist("111111111"));
    }

    @Test
    void shipNotExistPassingANullReferenceByParameter(){
        assertFalse(movementsSummaryController.shipExist(null));
    }
}