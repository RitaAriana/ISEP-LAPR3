package lapr.project.mapper.dto;


/**
 * Represents a data transfer object of ports.
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class PortsDto {
    /**
     * The name of the country
     */
    private final String countryName;

    /**
     * The continent to which the country belongs
     */
    private final String continent;

    /**
     * The data transfer object code
     */
    private final int code;

    /**
     * The data transfer object name
     */
    private final String portName;

    /**
     * The latitude of the data transfer object
     */
    private final double latitude;

    /**
     * The longitude of the data transfer object
     */
    private final double longitude;

    /**
     * Creates a new instance of PortsDto
     * @param countryName The name of the country
     * @param continent The continent to which the country belongs
     * @param code The data transfer object code
     * @param portName The data transfer object name
     * @param latitude The latitude of the data transfer object
     * @param longitude The longitude of the data transfer object
     */
    public PortsDto(String countryName,String continent,int code,String portName, double latitude, double longitude){
        this.countryName=countryName;
        this.continent=continent;
        this.code=code;
        this.portName=portName;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getCountryName(){
        return countryName;
    }

    public String getContinent(){
        return continent;
    }

    public int getCode(){
        return code;
    }

    public String getPortName(){
        return portName;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    @Override
    public String toString(){
        return String.format("Port: %s Code: %d Country Name: %s",portName,code,countryName);
    }

}
