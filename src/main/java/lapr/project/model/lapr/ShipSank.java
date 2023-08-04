package lapr.project.model.lapr;

import lapr.project.model.Ship;

public class ShipSank {

    private final double DENSITY = 1030.0;
    private final double CONTAINER_MASS = 500.0;
    private final double GRAVITY = 9.8;

    private double vesselImmersedVolumeEmpty;
    private double vesselImmersedVolumeLoaded;
    private double shipSankHeight;
    private double weightForce;

    public ShipSank(){
        this.vesselImmersedVolumeEmpty = 0;
        this.vesselImmersedVolumeLoaded = 0;
        this.shipSankHeight = 0;
        this.weightForce = 0;
    }


    public double shipMass(double vesselMass, int containers){
        return (vesselMass + (containers * CONTAINER_MASS));
    }


    public double vesselImmersedVolume (double vesselMass, int containers){
        double volume;
        volume = (vesselMass + (containers * CONTAINER_MASS))/DENSITY;
        return volume;
    }

    public double calculateDraft (Ship ship, double volume) {
        double draft;
        draft = (volume / (ship.getLength() * ship.getWidth()));
        return draft;
    }

    public double shipSankHeight (Ship ship, double vesselMass, int containers) {
        double vesselImmersedVolume, vesselImmersedVolumeLoaded, emptyDraft, loadedDraft;

        vesselImmersedVolume = vesselImmersedVolume(vesselMass,0);
        this.vesselImmersedVolumeEmpty = vesselImmersedVolume;
        vesselImmersedVolumeLoaded = vesselImmersedVolume(vesselMass,containers);
        this.vesselImmersedVolumeLoaded = vesselImmersedVolumeLoaded;

        emptyDraft = calculateDraft(ship,vesselImmersedVolumeEmpty);
        loadedDraft = calculateDraft(ship,vesselImmersedVolumeLoaded);

        this.shipSankHeight = loadedDraft - emptyDraft;
        return  shipSankHeight;
    }

    public double pressureExertedByVesselOnWater(Ship ship, double vesselMass, int containers){
        double weightForce, pressure;

        weightForce = calculateWeightForce(shipMass(vesselMass,containers));
        pressure = calculatePressure(ship,weightForce);

        return pressure;
    }

    public double calculateWeightForce(double vesselMass){
        double weightForce = ((vesselMass * GRAVITY));
        this.weightForce = weightForce;
        return weightForce;
    }

    public double calculatePressure(Ship ship, double weightForce){
        double pressure = (weightForce / immersedArea(ship) );
        return pressure;
    }

    public double immersedArea(Ship ship){
        double draft = calculateDraft(ship,20000);
        return ((2 * ship.getLength() * draft ) + (2 * ship.getWidth() * draft ) + (ship.getLength() * ship.getWidth()));
    }
    public double getShipSankHeight() {
        return shipSankHeight;
    }










}
