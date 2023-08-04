package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CargoManifestTest {

    private CargoManifest cargoManifest;

    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date();
        cargoManifest = new CargoManifest(date, 5, "211331640",CargoManifest.Destination.PORT);
    }

    @Test
    void getDate() {
        assertEquals(date, cargoManifest.getDate());
    }

    @Test
    void getTotalNumberOfContainers() {
        assertEquals(5, cargoManifest.getTotalNumberOfContainers());
    }

    @Test
    void getMmsiCodeShip() {
        assertEquals("211331640", cargoManifest.getMmsiCodeShip());
    }

    @Test
    void getDestination() {
        assertEquals(CargoManifest.Destination.PORT, cargoManifest.getDestination());
    }
}