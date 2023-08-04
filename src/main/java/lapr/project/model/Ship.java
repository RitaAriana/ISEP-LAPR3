package lapr.project.model;

/**
 *
 * @author Francisco Redol <1201239@isep.ipp.pt>
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class Ship implements Comparable<Ship> {

    /**
     * The ship's MMSI
     */
    private String MMSI;

    /**
     * The ship's name
     */
    private String name;

    /**
     * The ship's ID (IMO code)
     */
    private String shipID;

    /**
     * The ship's number of Energy Generators
     */
    private int energyGenerators;

    /**
     * The ship's Power Output
     */
    private float generatorOutput;

    /**
     * The ship's Call sign
     */
    private String callSign;

    /**
     * The ship's capacity
     */
    private String cargo;

    /**
     * The ship's Vessel Type
     */
    private int vesselType;

    /**
     * The ship's length
     */
    private float length;

    /**
     * The ship's width
     */
    private float width;

    /**
     * The ship's draft
     */
    private float draft;

    /**
     * The ship's cargo
     */
    private float capacity;

    /**
     * The Ship's Locations tree
     */
    private ShipLocationBST shipLocationBST; //Dynamical fields according to the location of the ship, are stored in a dedicated class, called ShipLocation


    /**
     * Empty Constructor
     */
    public Ship(){
        shipLocationBST = new ShipLocationAVL();
    }

    /**
     * Ship Constructor
     *
     * @param MMSI The ship's MMSI
     * @param name The ship's name
     * @param shipID The ship's ID (IMO code)
     * @param energyGenerators The ship's number of Energy Generators
     * @param generatorOutput The ship's Power Output
     * @param callSign The ship's Call sign
     * @param cargo The ship's capacity
     * @param vesselType The ship's Vessel Type
     * @param length The ship's length
     * @param width The ship's width
     * @param draft The ship's draft
     * @param shipLocation The Ship's Locations tree
     */
    public Ship(String MMSI, String name, String shipID, int energyGenerators, float generatorOutput, String callSign,
                int vesselType, float length, float width, String cargo , float draft, ShipLocation shipLocation){
        setMMSI(MMSI);
        setName(name);
        setShipID(shipID);
        setNumberGenerators(energyGenerators);
        setGeneratorOutput(generatorOutput);
        setCallSign(callSign);
        setCargo(cargo);
        setVesselType(vesselType);
        setLength(length);
        setWidth(width);
        setDraft(draft);
        setCapacity();
        shipLocationBST = new ShipLocationAVL();
        shipLocationBST.insert(shipLocation);
    }

    /**
     * Ship constructor
     *
     * @param MMSI The ship's MMSI
     * @param name The ship's name
     * @param shipID The ship's ID (IMO code)
     * @param energyGenerators The ship's number of Energy Generators
     * @param generatorOutput The ship's Power Output
     * @param callSign The ship's Call sign
     * @param cargo The ship's capacity
     * @param vesselType The ship's Vessel Type
     * @param length The ship's length
     * @param width The ship's width
     * @param draft The ship's draft
     * @param shipLocationAVL The Ship's Locations tree
     */
    public Ship(String MMSI, String name, String shipID, int energyGenerators, float generatorOutput, String callSign,
                int vesselType, float length, float width, String cargo , float draft, ShipLocationBST shipLocationAVL){
        setMMSI(MMSI);
        setName(name);
        setShipID(shipID);
        setNumberGenerators(energyGenerators);
        setGeneratorOutput(generatorOutput);
        setCallSign(callSign);
        setCargo(cargo);
        setVesselType(vesselType);
        setLength(length);
        setWidth(width);
        setDraft(draft);
        setCapacity();
        this.shipLocationBST = shipLocationAVL;
    }

    /**
     * Sets the Ship's MMSI according to the defined rules
     *
     * @param MMSI The ship's MMSI
     */
    public void setMMSI(String MMSI){
        if(MMSI == null || MMSI.length() != 9)
            throw new IllegalArgumentException("The ship MMSI code must be 9-digit long.");
        else
            this.MMSI = MMSI;
    }

    /**
     * Sets the Ship's name according to the defined rules
     *
     * @param name The ship's name
     */
    public void setName(String name){
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("The Ship name cannot be empty.");
        else
            this.name = name;
    }

    /**
     * Sets the Ship's shipID according to the defined rules
     *
     * @param shipID The ship's ID (IMO code)
     */
    public void setShipID(String shipID){
        if(shipID == null || shipID.length() != 10 || !shipID.startsWith("IMO"))
            throw new IllegalArgumentException("The shipID code must be 7-digit long.");
        else
            this.shipID = shipID;
    }

    /**
     * Sets the Ship's number of Energy Generators according to the defined rules
     *
     * @param energyGenerators The ship's number of Energy Generators
     */
    public void setNumberGenerators(int energyGenerators){
        if(energyGenerators < 0)
            throw new IllegalArgumentException("The ship cannot have less than 0 generators.");
        else this.energyGenerators = energyGenerators;
    }

    /**
     * Sets the Ship's generator output according to the defined rules
     *
     * @param generatorOutput The ship's Power Output
     */
    public void setGeneratorOutput(float generatorOutput){
        if(generatorOutput < 0)
            throw new IllegalArgumentException("The ship cannot have a power output lower than 0");
        else this.generatorOutput = generatorOutput;
    }

    /**
     * Sets the Ship's Call Sign according to the defined rules
     *
     * @param callSign The ship's Call sign
     */
    public void setCallSign (String callSign){
        if(callSign == null || callSign.isEmpty())
            throw new IllegalArgumentException("The Ship call sign cannot be empty.");
        else
            this.callSign = callSign;
    }

    /**
     * Sets the Ship's cargo according to the defined rules
     *
     * @param cargo ship's cargo
     */
    public void setCargo(String cargo){
        if(cargo == null || cargo.isEmpty())
            throw new IllegalArgumentException("The Ship's cargo shall not be empty.");
        else
            this.cargo = cargo;
    }

    /**
     * Sets the Ship's length according to the defined rules
     *
     * @param length Ship's length
     */
    public void setLength (float length){
        if(length <= 0)
            throw new IllegalArgumentException("A Ship must have a length bigger than 0.");
        else
            this.length = length;
    }

    /**
     * Sets the Ship's width according to the defined rules
     *
     * @param width ship's width
     */
    public void setWidth(float width){
        if(width <= 0)
            throw new IllegalArgumentException("A Ship must have a width bigger than 0.");
        else
            this.width = width;
    }

    /**
     * Sets the Ship's draft according to the defined rules
     *
     * @param draft ship's draft
     */
    public void setDraft(float draft){
        if(draft < 0)
            throw new IllegalArgumentException("A ship cannot have a draft lower than 0. Otherwise, you will end up with a submarine.\n:)");
        else
            this.draft = draft;
    }

    /**
     * Set's the ship's Capacity
     */
    public void setCapacity(){
        this.capacity = width * length;
    }

    /**
     * Sets the Ship's vessel type
     *
     * @param vesselType ship's vessel type
     */
    public void setVesselType(int vesselType){
        this.vesselType = vesselType;
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
     * @return ship's number of energy Generators
     */
    public int getEnergyGenerators(){
        return energyGenerators;
    }

    /**
     * @return ship's number of Generators' output
     */
    public float getGeneratorOutput(){
        return generatorOutput;
    }

    /**
     * @return the ship's call sign
     */
    public String getCallSign(){
        return callSign;
    }

    /**
     * @return the ship's Vessel Type
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
     * @return the ship's capacity
     */
    public String getCargo(){
        return cargo;
    }

    /**
     * @return the ship's draft
     */
    public float getDraft(){
        return draft;
    }

    /**
     * @return the ship's capacity
     */
    public float getCapacity(){
        return capacity;
    }

    /**
     * This method allows to obtain the BST of the different locations of the boat over time
     * @return BST with the various locations of the ship
     */
    public ShipLocationBST getShipPosition(){
        return shipLocationBST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        Ship ship = (Ship) o;
        return getEnergyGenerators() == ship.getEnergyGenerators() && Float.compare(ship.getGeneratorOutput(), getGeneratorOutput()) == 0 && getMMSI().equals(ship.getMMSI()) && getName().equals(ship.getName()) && getShipID().equals(ship.getShipID()) && getCallSign().equals(ship.getCallSign()) && getCargo().equals(ship.getCargo()) && getVesselType()==ship.getVesselType() && shipLocationBST.equals(ship.shipLocationBST);
    }


    /**
     * @param o Other ship to compare
     *
     * Compares ships by MMSI
     *
     * @return the compareTo result of the MMSI
     */
    @Override
    public int compareTo(Ship o) {
        return this.MMSI.compareTo(o.getMMSI());
    }

    /**
     * @return information about a certain ship
     */
    @Override
    public String toString(){
        return String.format("MMSI: %s\nName: %s\nshipID: %s\nEnergy Generators: %d\nGenerator Output: %.2f\nCall Sign: %s\nVessel Type: %d\nLength: %.2f\n" +
                "Width: %.2f\nCapacity: %s\nDraft: %.2f\n", MMSI, name, shipID, energyGenerators, generatorOutput, callSign, getVesselType(), getLength(), getWidth(), getCargo(), getDraft());
    }
}