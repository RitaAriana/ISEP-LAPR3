package lapr.project.controller;

import lapr.project.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImportShipControllerTest {

    ImportShipController controller;

    Company company;

    @BeforeEach
    public void setUp(){
        company = new Company();
        controller = new ImportShipController();
    }

    @Test
    public void importFileSuccess() {
        String fileName = "sships.csv";
        boolean result = controller.importFile(fileName);
        assertTrue(result);
    }

    @Test
    public void importFileSuccess2() {
        String fileName = "bports.csv";
        boolean result = controller.importFile(fileName);
        assertTrue(result);
    }

    @Test
    public void importFileInsuccess() {
        String fileName = "sships.csa";
        boolean result = controller.importFile(fileName);
        assertFalse(result);
    }

    @Test
    public void importFileInsuccess2() {
        String fileName = "bships.csa";
        boolean result = controller.importFile(fileName);
        assertFalse(result);
    }

    @Test
    public void importShips() {
        String fileName = "sships.csv";
        controller.importFile(fileName);
        assertEquals(0, controller.importShips());
    }

    @Test
    public void importShips2() { //3 lines have their IMO Wrong
        String fileName = "Test2.csv";
        controller.importFile(fileName);
        assertEquals(3, controller.importShips());
    }

    @Test
    public void importShips3() {
        String fileName = "Test3.csv";
        controller.importFile(fileName);
        assertEquals(1, controller.importShips());
    }

    @Test
    public void importShips4() { //3 lines have their IMO Wrong
        String fileName = "Test2.csv";
        controller.importFile(fileName);
        boolean exception = false;
        try{
            controller.importShips();
            controller.importShips();
        } catch (Exception e){
            exception = true;
        }
        assertTrue(exception);
    }

}