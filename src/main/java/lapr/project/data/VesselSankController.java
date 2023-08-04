package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.WriteForAFile;
import lapr.project.model.lapr.ShipSank;

import java.io.File;
import java.io.IOException;

/**
 * Class that allows the ship capitan to know for a specific vessel, how much did the vessel sink.
 * @author Pedro Rocha <1201382@isep.ipp.pt>
 */
public class VesselSankController {

    /**
     * The controller's company
     */
    private final Company company;

    /**
     * The controller's writeForAFile Object
     */
    private final WriteForAFile writeForAFile;

    /**
     * Instance of Ship
     */
    private Ship ship;

    /**
     * Ship's Mass
     */
    private double vesselMass;

    /**
     * Number of containers in the Ship
     */
    private int containers;

    /**
     * Weight force
     */
    private double weightForce;

    /**
     * Ship's area in contact with the water
     */
    private double immersedArea;

    /**
     * Pressure exerted by the vessel on the water
     */
    private double pressure;

    /**
     * Immersed volume when the ship is empty
     */
    private double immersedVolumeEmpty;

    /**
     * Immersed volume when the ship is loaded
     */
    private double immersedVolumeLoaded;

    /**
     * Draft when the ship is Empty
     */
    private double draftEmpty;

    /**
     * Draft when the ship is loaded
     */
    private double draftLoaded;

    /**
     * Ship Sank Height
     */
    private double shipSankHeight;

    /**
     * An instance of ShipSank
     */
    private ShipSank shipSank;

    /**
     * vesselSankController Constructor
     */
    public VesselSankController(){
        this.company = App.getInstance().getCompany();
        this.writeForAFile = new WriteForAFile();
        this.shipSank = new ShipSank();
    }

    /**
     *
     */
    public void vesselSank(Ship ship, double vesselMass, int containers){
        this.ship = ship;
        this.vesselMass = vesselMass;
        this.containers = containers;
        this.weightForce = shipSank.calculateWeightForce(vesselMass);
        this.immersedArea = shipSank.immersedArea(ship);
        this.pressure = shipSank.calculatePressure(ship,this.weightForce);
        this.immersedVolumeEmpty = shipSank.vesselImmersedVolume(vesselMass,0);
        this.immersedVolumeLoaded = shipSank.vesselImmersedVolume(vesselMass,containers);
        this.draftEmpty = shipSank.calculateDraft(ship,this.immersedVolumeEmpty);
        this.draftLoaded = shipSank.calculateDraft(ship,this.immersedVolumeLoaded);
        this.shipSankHeight = shipSank.shipSankHeight(ship,vesselMass,containers);

    }
    /**
     * @return the Controller's Company
     */
    public Company getCompany(){
        return this.company;
    }


    public void writeForAFile() throws IOException {
        try {
            writeForAFile.writeForAFile(toString(), "US420_" + ship.getMMSI(), new File(".\\outputs\\US420"), false);

        } catch (Exception e){
            e.printStackTrace();
            writeForAFile.writeForAFile("Something went wrong", "" + ship.getMMSI(), new File(".\\outputs\\US420"), false);
        }
    }


    /**
     * Ship Sank Description
     * @return
     */
    @Override
    public String toString() {
        return String.format("Ship Vessel Sink Information \n\n Ship mmsi: %s \n Vessel Mass: %f Kg\n Containers: %d\n\n Weight Force: %f N\n Immersed Area: %f m3\n Pressure: %f Pa\n"
                + " Immersed volume when the ship is not loaded: %f m3\n Immersed volume when the ship is loaded: %f m3\n Empty Draft %f m\n Loaded Draft %f m\n Ship Sank Height: %f m",
                ship.getMMSI(), this.vesselMass, this.containers, this.weightForce, this.immersedArea, this.pressure, this.immersedVolumeEmpty,
                this.immersedVolumeLoaded, this.draftEmpty, this.draftLoaded, this.shipSankHeight);
    }
}
