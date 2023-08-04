package lapr.project.controller;

import lapr.project.data.SendToDatabase;
import lapr.project.model.Company;

/**
 * Send To Database Controller
 *
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class SendToDatabaseController {

    /**
     * SendToDta
     */
    private final SendToDatabase sendToDatabase;

    /**
     * The SendToDatabaseController
     */
    public SendToDatabaseController(){
        sendToDatabase = new SendToDatabase();
    }

    /**
     * The SendToDatabaseController
     *
     * @param company used for testing purposes
     */
    public SendToDatabaseController(Company company){
        sendToDatabase = new SendToDatabase(company);
    }

    /**
     * Send Ships and Locations to database
     */
    public void sendShipsAndLocations(){
        sendToDatabase.sendShipsAndLocationsToDatabase();
    }

    /**
     * Send Ports to the database
     */
    public void sendPorts(){
        sendToDatabase.sendPortsToDatabase();
    }
}
