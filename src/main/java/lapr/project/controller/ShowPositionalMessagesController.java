package lapr.project.controller;


import lapr.project.model.BstShip;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipLocationBST;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Class responsible for making the connection between the UI and the system when viewing a ship's positional messages
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class ShowPositionalMessagesController {

    /**
     * Represents an instance of app.
     */
    private final App app;

    /**
     * Represents an instance of Company
     */
    private final Company company;

    /**
     * Represents an instance of BstShip
     */
    private final BstShip bstShip;

    /**
     * Represents an instance of ship
     */
    private Ship ship;

    /**
     * Represents an instance of WriteForFile
     */
    private final WriteForAFile writeForAFile;


    /**
     * Initialize the controller
     */
    public ShowPositionalMessagesController(){
        this.app=App.getInstance();
        this.company=app.getCompany();
        this.bstShip=company.getBstShip();
        this.writeForAFile = new WriteForAFile();
    }

    /**
     * Initialize the controller receiving a company
     */
    public ShowPositionalMessagesController(Company company){
        this.app=App.getInstance();
        this.company=company;
        this.bstShip=company.getBstShip();
        this.writeForAFile = new WriteForAFile();
    }

    /**
     * Get the company associated with the controller
     * @return company associated with the controller
     */
    public Company getCompany(){
        return company;
    }

    /**
     * Verification if a ship exists in the system through the MMSI code
     * @param mmsiCode Code of the ship that we want to know if it exists in the system
     * @return true if the ship exists, otherwise return false
     */
    public boolean shipExist(String mmsiCode){
        this.ship=bstShip.getShipByMmsiCode(mmsiCode);
        return ship != null;
    }

    /**
     * Obtain the positional messages of the intended ship within the indicated period of time
     * @param initialDate initial date of the intended period
     * @param finalDate final date of the intended period
     * @return List with requested positional messages
     */
    public List<String> showPositionalMessages(Date initialDate, Date finalDate) throws IOException {
        ShipLocationBST shipLocationBst = ship.getShipPosition();
        File file = new File(".\\outputs\\Positional Messages");
        List<String> positionalMessages = shipLocationBst.getPositionalMessages(initialDate,finalDate);
        for(String lista : positionalMessages){
            writeForAFile.writeForAFile(lista, ship.getMMSI(), file, false);
        }
        return positionalMessages;

    }

    /**
     * Obtain the positional messages of the intended ship within the indicated period of time
     * @param date initial date of the intended period
     * @return List with requested positional messages
     */
    public String showPositionalMessages(Date date) throws IOException {
        ShipLocationBST shipLocationBst = ship.getShipPosition();
        File file = new File(".\\outputs\\Positional Messages");
        String positionalMessage = shipLocationBst.getPositionalMessages(date);
        writeForAFile.writeForAFile(positionalMessage, ship.getMMSI(), file, false);

        return positionalMessage;
    }
}
