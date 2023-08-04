package lapr.project.model;

import lapr.project.controller.App;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * The ImportShip class, which allows the user to import ships and its locations from a .csv file
 *
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class ImportShip{

    /**
     * The file to be imported
     */
    File file;

    /**
     * The Scanner which reads the file
     */
    Scanner readFile;

    /**
     * Represents an instance of Company
     */
    private final Company company;

    /**
     * The class constructor
     */
    public ImportShip() {
        this.company=App.getInstance().getCompany();
    }

    public ImportShip(Company company){
        this.company=company;
    }

    /**
     * @param fileName The file name
     *
     * Allows the class to fetch the file desired by the user
     *
     * @return the success of the operation
     */
    public boolean getFile(String fileName) {
        file = new File(fileName);
        if (file.exists()) {
            try {
                readFile = new Scanner(file);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Manages the lines which are sent to getLineArray and createShip.
     *
     * @return The number of ships which were not imported
     */
    public int convertShips() {
        int shipsNotConverted = 0;
        readFile.nextLine();
        while (readFile.hasNext()) {
            String line = readFile.nextLine();
            String[] shipArray = getLineArray(line);
            if (shipArray.length == 16)
                shipsNotConverted += createShip(shipArray);
            else
                shipsNotConverted += 1;
        }
        readFile.close();
        return shipsNotConverted;
    }

    /**
     * @param shipLine Each line of the .csv file
     * @return the line split as an array
     */
    public String[] getLineArray(String shipLine) {
        return shipLine.split(",");
    }

    /**
     * @param shipArray containing all the line data
     *
     * Creates and adds ships to the BstShip. If the ship already exists, its location is added
     *
     * @return 1 if the ship was not added or 0 if it was
     */
    public int createShip(String[] shipArray) {
        try {
            //#################### Ship Location Conversion and Creation ####################
            String MMSI = shipArray[0];
            Date messageTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(shipArray[1]);
            String latitude = shipArray[2];
            String longitude = shipArray[3];
            float SOG = Float.parseFloat(shipArray[4]);
            float COG = Float.parseFloat(shipArray[5]);
            String heading = shipArray[6];
            String transceiverClass = shipArray[15];
            ShipLocation shipLocation = new ShipLocation(MMSI, messageTime, latitude, longitude, SOG, COG, heading, transceiverClass);

            //#################### Ship Conversion and Creation ####################

            BstShip shipBST = company.getBstShip();
            Ship newShip = shipBST.getShipByMmsiCode(MMSI);

            if (newShip == null) {
                String name = shipArray[7];
                String shipID = shipArray[8];
                String callSign = shipArray[9];
                String cargo = shipArray[14];

                int vesselType = Integer.parseInt(shipArray[10]);
                float length = Float.parseFloat(shipArray[11]);
                float width = Float.parseFloat(shipArray[12]);
                float draft = Float.parseFloat(shipArray[13]);

                newShip = new Ship(MMSI, name, shipID, 0, 0, callSign, vesselType, length, width, cargo, draft, shipLocation);
                shipBST.insert(newShip);
            } else
                newShip.getShipPosition().insert(shipLocation);

        } catch (Exception e) {
            return 1;
        }
        return 0;
    }
}
