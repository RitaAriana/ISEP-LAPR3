package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.controller.ImportShipController;
import lapr.project.controller.ShowPositionalMessagesController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportPortTest {

    Company company;

    ImportPort importPort;

    @BeforeEach
    public void setUp(){
        company = new Company();
        importPort = new ImportPort(company);
    }

    @Test
    void loadFile() {
        String fileName = "bports.csv";
        boolean result = importPort.loadFile(fileName);
        assertTrue(result);
    }

    @Test
    void loadFileInsucess() {
        String fileName = "b.csv";
        boolean result = importPort.loadFile(fileName);
        assertFalse(result);
    }

    @Test
    void convertPorts() {
        String fileName = "bports.csv";
        importPort.loadFile(fileName);
        importPort.convertPorts();
    }

    @Test
    public void ctrlr() {
        ImportPort importPort1 = new ImportPort();
    }

    @Test
    void convertPortsTest() {
        String fileName = "portsTest.csv";
        importPort.loadFile(fileName);
        importPort.convertPorts();
        Assertions.assertEquals(4,importPort.getCountryStore().getCountryLst().size());
    }

    @Test
    void convertRepeatedPortsTest() {
        String fileName = "repeatedPorts.csv";
        importPort.loadFile(fileName);
        importPort.convertPorts();
        Assertions.assertEquals(5,importPort.getLst().size());
    }

}