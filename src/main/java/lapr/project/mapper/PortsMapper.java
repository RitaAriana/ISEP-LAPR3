package lapr.project.mapper;

import lapr.project.mapper.dto.PortsDto;
import lapr.project.model.Ports;

/**
 * Transform objects of type Ports into objects of type PortsDto
 *
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class PortsMapper {

    /**
     * Transforms an object of type Ports into an object of type PortsDto.
     * @param ports An Ports object.
     * @return An instance of PortsDto.
     */
    public PortsDto toDto(Ports ports) {
        return new PortsDto(ports.getCountryName(), ports.getContinent(), ports.getCode(), ports.getPortName(), ports.getLatitude(), ports.getLatitude());
    }

}
