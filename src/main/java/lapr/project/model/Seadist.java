package lapr.project.model;

/**
 * The Seadist Class, which represents the sea distance between two ports.
 * @author Francisco Redol <1201239@isep.ipp.pt>
 */
public class Seadist {

    /**
     * The first port's ID
     */
    private final Integer portId1;

    /**
     * The second port's ID
     */
    private final Integer portId2;

    /**
     * The distance between the two ports
     */
    private final float seaDistance;

    /**
     * The name of the first Port
     */
    private final String portName1;

    /**
     * The name of the second Port
     */
    private final String portName2;

    /**
     * The name of the first port's Country
     */
    private final String countryName1;

    /**
     * The name of the second port's Country
     */
    private final String countryName2;

    /**
     * The Seadist Constructor
     *
     * @param portId1 first port's ID
     * @param portId2 second port's ID
     * @param seaDistance distance between the two ports
     * @param portName1 name of the first Port
     * @param portName2 name of the second Port
     * @param countryName1 name of the first port's Country
     * @param countryName2 name of the second port's Country
     */
    public Seadist(int portId1, int portId2, float seaDistance, String portName1, String portName2, String countryName1, String countryName2){
        this.portId1 = portId1;
        this.portId2 = portId2;
        this.seaDistance = seaDistance;
        this.portName1 = portName1;
        this.portName2 = portName2;
        this.countryName1 = countryName1;
        this.countryName2 = countryName2;
    }

    /**
     * @return the first port's ID
     */
    public int getPortId1(){
        return this.portId1;
    }

    /**
     * @return the second port's ID
     */
    public int getPortId2(){
        return this.portId2;
    }

    /**
     * @return the Port's Sea distance
     */
    public float getSeaDistance(){
        return this.seaDistance;
    }

    /**
     * @return the first port's name
     */
    public String getPortName1(){
        return this.portName1;
    }

    /**
     * @return the second port's name
     */
    public String getPortName2(){
        return this.portName2;
    }

    /**
     * @return the first port's Country name
     */
    public String getCountryName1(){
        return this.countryName1;
    }

    /**
     * @return the second port's Country name
     */
    public String getCountryName2(){
        return this.countryName2;
    }
}
