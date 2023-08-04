package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetShortestPathsControllerTest {


    @Test
    void getsPortsMoreCritical() throws IOException {
        CreateFreightNetworkController createFreightNetworkController = new CreateFreightNetworkController();
        createFreightNetworkController.createFreightNetwork(1);
        GetShortestPathsController getShortestPathsController = new GetShortestPathsController();
        List<String> passagens = new ArrayList<>();
        passagens.add("Amsterdam");
        getShortestPathsController.getShortestPaths("Lisbon","Rome",passagens);
    }



}