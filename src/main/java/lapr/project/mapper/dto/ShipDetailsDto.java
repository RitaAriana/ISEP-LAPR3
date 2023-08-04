package lapr.project.mapper.dto;

import lapr.project.model.Ship;


/**
 * Represents a data transfer object of Ship
 * @author Pedro Rocha <1201382@isep.ipp.pt>
 */
public class ShipDetailsDto {

    /**
     * The ship's MMSI
     */
    private final String MMSI;

    /**
     * The ship's name
     */
    private final String name;

    /**
     * The ship's ID (IMO code)
     */
    private final String shipID;

    /**
     * The ship's Call sign
     */
    private final String callSign;

    /**
     * The ship's Vessel Type
     */
    private final int vesselType;

    /**
     * The ship's length
     */
    private final float length;

    /**
     * The ship's width
     */
    private final float width;

    /**
     * The ship's draft
     */
    private final float draft;

    /**
     * Ship's Details Dto constructor
     *
     * @param mmsi The ship's MMSI
     * @param name The ship's name
     * @param shipID The ship's ID (IMO code)
     * @param callSign The ship's Call sign
     * @param vesselType The ship's Vessel Type
     * @param length The ship's length
     * @param width The ship's width
     * @param draft The ship's draft
     */
    public ShipDetailsDto(String mmsi, String name, String shipID, String callSign, int vesselType, float length, float width, float draft){
        this.MMSI = mmsi;
        this.name = name;
        this.shipID = shipID;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = length;
        this.width = width;
        this.draft = draft;
    }

    /**
     * Ship's Details Dto Constructor
     *
     * @param ship to convert to data transfer object
     */
    public ShipDetailsDto(Ship ship) {
        this.MMSI = ship.getMMSI();
        this.name = ship.getName();
        this.shipID = ship.getShipID();
        this.callSign = ship.getCallSign();
        this.vesselType = ship.getVesselType();
        this.length = ship.getLength();
        this.width = ship.getWidth();
        this.draft = ship.getDraft();
    }

    /**
     * @return ship's MMSI
     */
    public String getMMSI(){
        return MMSI;
    }

    /**
     * @return ship's name
     */
    public String getName(){
        return name;
    }

    /**
     * @return ship's ID
     */
    public String getShipID(){
        return shipID;
    }


    /**
     * @return the ship's call sign
     */
    public String getCallSign(){
        return callSign;
    }

    /**
     * @return the ship's vessel type
     */
    public int getVesselType(){
        return vesselType;
    }

    /**
     * @return the ship's length
     */
    public float getLength(){
        return length;
    }

    /**
     * @return the ship's width
     */
    public float getWidth(){
        return width;
    }

    /**
     * @return the ship's draft
     */
    public float getDraft(){
        return draft;
    }

    /**
     * @return information about a certain ship
     */
    @Override
    public String toString() {
        return  "MMSI: " + MMSI +
                " \nName: " + name +
                " \nIMO: " + shipID +
                " \nCall Sign: " + callSign +
                " \nVessel Type: " + vesselType +
                " \nLength: " + length +
                " \nWidth: " + width +
                " \nDraft: " + draft;
    }
}
