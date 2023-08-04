package lapr.project.controller;

import lapr.project.mapper.PortsMapper;
import lapr.project.mapper.dto.PortsDto;
import lapr.project.model.*;
import lapr.project.model.store.PortStore;
import lapr.project.utils.WriteForAFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Class responsible for making the connection between the UI and the system when find the closest port
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class FindClosestPortController {

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
     * Represents an instance of PortStore
     */
    private final PortStore portStore;

    /**
     * Represents an instance of the ports mapper.
     */
    private final PortsMapper portsMapper;

    /**
     * Represents an instance of WriteForFile
     */
    private final WriteForAFile writeForAFile;

    /**
     * Initialize the controller
     */
    public FindClosestPortController(){
        this.app=App.getInstance();
        this.company=app.getCompany();
        this.bstShip=company.getBstShip();
        this.portStore=company.getPortStr();
        this.portsMapper= new PortsMapper();
        this.writeForAFile = new WriteForAFile();
    }

    /**
     * Initialize the controller receiving a company
     */
    public FindClosestPortController(Company company){
        this.app=App.getInstance();
        this.company=company;
        this.bstShip=company.getBstShip();
        this.portStore=company.getPortStr();
        this.portsMapper= new PortsMapper();
        this.writeForAFile = new WriteForAFile();
    }

    /**
     * Get the closest port of a particular ship on a certain date
     * @param callSign The ship's Call sign
     * @param date the date for search
     */
    public PortsDto findClosestPort(String callSign, Date date) throws IOException {
        Ship ship= bstShip.getShipByCallSign(callSign);
        if(ship!=null){
            ShipLocationBST shipLocationBST=ship.getShipPosition();
            ShipLocation shipLocation=shipLocationBST.getShipLocationByDate(date);
            if(shipLocation!=null) {
                Ports port = portStore.getPorts2DTree().findNearestNeighbour(Double.parseDouble(shipLocation.getLatitude()), Double.parseDouble(shipLocation.getLongitude()));
                File file = new File(".\\outputs\\US202");
                PortsDto portsDto = portsMapper.toDto(port);
                writeForAFile.writeForAFile(portsDto.toString(), callSign, file, false);
                return portsDto;
            }
        }
        return null;
    }


}
