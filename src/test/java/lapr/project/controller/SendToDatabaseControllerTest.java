package lapr.project.controller;

import lapr.project.model.Company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SendToDatabaseControllerTest {

    SendToDatabaseController sendToDatabaseController;

    Company company;

    @BeforeEach
    void setUp(){
        company = new Company();
        sendToDatabaseController = new SendToDatabaseController(company);
    }

    @Test
    void sendShipsAndLocations() {
        sendToDatabaseController.sendShipsAndLocations();
    }

    @Test
    void sendPorts() {
        sendToDatabaseController.sendPorts();
    }
}