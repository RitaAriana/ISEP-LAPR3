package lapr.project.controller;

import lapr.project.mapper.dto.PortsDto;
import lapr.project.model.Company;
import lapr.project.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class FindClosestPortControllerTest {

    @Test
    void findClosestPort() throws ParseException, IOException {
        Company company = new Company();
        ImportShipController importShipController = new ImportShipController(company);
        importShipController.importFile("sships.csv");
        importShipController.importShips();
        ImportPortController importPortController = new ImportPortController(company);
        importPortController.importFile("portsTest.csv");
        importPortController.importPorts();
        FindClosestPortController findClosestPortController = new FindClosestPortController(company);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        PortsDto result = findClosestPortController.findClosestPort("WNGW",dateFormatter.parse("31-12-2020 23:26"));
        PortsDto portsDto = new PortsDto("Peru","America",10860,"Matarani",-17,-72.1);
        Assertions.assertEquals(portsDto.getCode(),result.getCode());
    }
}