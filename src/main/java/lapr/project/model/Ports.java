package lapr.project.model;

/**
 * Represents a Port.
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class Ports extends Place{

    /**
     * The port code
     */
    private final int code;

    /**
     * The port name
     */
    private final String portName;


    /**
     * Build an instance of {@code Ports} by receiving the country, the code, the name and the coordinates
     * @param country The port country
     * @param code The port code
     * @param portName The port name
     * @param coordinates The port location
     */
    public Ports(Country country, int code, String portName, PlaceLocation coordinates){
        super(coordinates.getLatitude(),coordinates.getLongitude(),country.getCountryName(),country.getContinent());
        this.code=code;
        this.portName=portName;
    }

    /**
     * Get the port name
     * @return The port name
     */
    public String getPortName(){
        return portName;
    }

    /**
     * Get the port code
     * @return The port code
     */
    public int getCode(){
        return code;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Ports)) return false;
        Ports ports = (Ports) o;
        return ports.getCode() == this.getCode() && ports.getLatitude() == this.getLatitude() && ports.getLongitude() == this.getLongitude();
    }

    /**
     * @return a String representing the Port
     */
    public String toString(){
        return String.format("Port %s with code: %d", portName, code);
    }
}
