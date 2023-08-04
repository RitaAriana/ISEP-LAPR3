package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container;

    @BeforeEach
    void setUp() {
        container = new Container("CT342",1,2,3, 5, "ISO234", 200f, 100f, 30f, 356f, 123f, 35f, "Recommended", "Sustainable certificate");
    }

    @Test
    void getNumber() {
        assertEquals(container.getNumber(), "CT342");
    }

    @Test
    void getCheckDigit() {
        assertEquals(container.getCheckDigit(), 5);
    }

    @Test
    void getIsoCode() {
        assertEquals(container.getIsoCode(), "ISO234");
    }

    @Test
    void getMaximumWeight() {
        assertEquals(container.getMaximumWeight(), 200f, 0.0);
    }

    @Test
    void getPayload() {
        assertEquals(container.getPayload(), 100f, 0.0);
    }

    @Test
    void getTare() {
        assertEquals(container.getTare(), 30f, 0.0);
    }

    @Test
    void getWeight() {
        assertEquals(container.getWeight(), 356f, 0.0);
    }

    @Test
    void getMaxWeightPacked() {
        assertEquals(container.getMaximumWeight(), 200f, 0.0);
    }

    @Test
    void getMaxVolumePacked() {
        assertEquals(container.getMaxVolumePacked(), 35f);
    }

    @Test
    void getRepairRecommendation() {
        assertEquals(container.getRepairRecommendation(), "Recommended");
    }

    @Test
    void getCertificate() {
        assertEquals(container.getCertificate(), "Sustainable certificate");
    }

    @Test
    void getX(){
        assertEquals(1, container.getX());
    }

    @Test
    void getY(){
        assertEquals(2, container.getY());
    }

    @Test
    void getZ(){
        assertEquals(3, container.getZ());
    }
}