package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetTheMostEfficientCircuitControllerTest {



    @Test
    void getTheMostEfficientCircuit() throws IOException {
        CreateFreightNetworkController createFreightNetworkController = new CreateFreightNetworkController();
        createFreightNetworkController.createFreightNetwork(4);
        GetTheMostEfficientCircuitController circuitController = new GetTheMostEfficientCircuitController();
        circuitController.getTheMostEfficientCircuit("Lisbon");
    }





}