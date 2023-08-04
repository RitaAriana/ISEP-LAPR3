package lapr.project.presentation;

import lapr.project.controller.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


class ScenarioTestTest {

    private ImportShipController importShipController;

    private ShowPositionalMessagesController showPositionalMessagesController;

    private ShowPairsOfShipsController showPairsOfShipsController;

    private SearchDetailsController searchDetailsController;

    private MovementsSummaryController movementsSummaryController;

    private ListSomeShipDataController listSomeShipDataController;

    private ShowTopShipsController showTopShipsController;

    private ImportPortController importPortController;

    private FindClosestPortController findClosestPortController;

    public ScenarioTestTest() throws SQLException, IOException {
        importShipController=new ImportShipController();
        showPositionalMessagesController=new ShowPositionalMessagesController();
        showPairsOfShipsController=new ShowPairsOfShipsController();
        searchDetailsController = new SearchDetailsController();
        movementsSummaryController = new MovementsSummaryController();
        listSomeShipDataController = new ListSomeShipDataController();
        showPairsOfShipsController = new ShowPairsOfShipsController();
        showTopShipsController = new ShowTopShipsController();
        importPortController = new ImportPortController();
        findClosestPortController = new FindClosestPortController();
    }

    @Test
    public void presentation() throws ParseException, IOException {
        importShipController.importFile("sships.csv");
        importShipController.importShips();
        importShipController.importFile("bships.csv");
        importShipController.importShips();

        searchDetailsController.shipExistByIMO("IMO9395044");
        searchDetailsController.getShipDetails();
        searchDetailsController.writeDataSearchedByIMO();
        searchDetailsController.shipExistByCallSign("C4SQ2");
        searchDetailsController.getShipDetails();
        searchDetailsController.writeDataSearchedByCallsign();
        String[] auxDatas = {"25-12-2020 17:00","05-03-2021 17:30","31-12-2020 23:00"};
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        if(showPositionalMessagesController.shipExist("210950000")){
            showPositionalMessagesController.showPositionalMessages(dateFormatter.parse(auxDatas[0]),dateFormatter.parse(auxDatas[1]));
        }
        if(showPositionalMessagesController.shipExist("303221000")){
            showPositionalMessagesController.showPositionalMessages(dateFormatter.parse(auxDatas[2]));
        }
        movementsSummaryController.createSummaryDto(movementsSummaryController.createSummaryForShip(movementsSummaryController.getShipByMmsiCode("210950000")));
        listSomeShipDataController.organizeByDescendingOrder();
        showTopShipsController.getTopNShips(5,dateFormatter.parse(auxDatas[0]),dateFormatter.parse(auxDatas[1]));
        showPairsOfShipsController.getPairsOfShip();

    }


    @Test
    public void presentation2() throws ParseException, IOException {

        importPortController.importFile("portsTest.csv");
        importPortController.importPorts();

        importShipController.importFile("sships.csv");
        importShipController.importShips();


        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        findClosestPortController.findClosestPort("WNGW", dateFormatter.parse("31-12-2020 23:26"));


        importPortController.importFile("sports.csv");
        importPortController.importPorts();

        importPortController.importFile("bports.csv");
        importPortController.importPorts();

    }



}