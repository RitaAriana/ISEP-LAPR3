package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImportPortControllerTest {

    Company company;
    ImportPortController controller;

    @BeforeEach
    public void setUp(){
        company = new Company();
        controller = new ImportPortController(company);
    }

    @Test
    void importFile() {
        String fileName = "bports.csv";
        boolean result = controller.importFile(fileName);
        assertTrue(result);
    }

    @Test
    public void importFileInsuccess() {
        String fileName = "bports.csa";
        boolean result = controller.importFile(fileName);
        assertFalse(result);
    }

    @Test
    public void ctrlr() {
        ImportPortController ctrlr = new ImportPortController();
    }

    @Test
    void importPortsTest(){
        Company company = new Company();
        ImportPortController importPortController = new ImportPortController(company);
        importPortController.importFile("portsTest.csv");
        importPortController.importPorts();
        Assertions.assertEquals(35.84194,importPortController.getImportPort().getPortStore().getPorts2DTree().root.getX(),0);
        Assertions.assertEquals(34.91666667,importPortController.getImportPort().getPortStore().getPorts2DTree().root.getLeft().getX(),0);
        Assertions.assertEquals(55.7,importPortController.getImportPort().getPortStore().getPorts2DTree().root.getRight().getX(),0);
    }
}