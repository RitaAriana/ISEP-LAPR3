package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeadistTest {

    private Seadist seadistTest;

    @BeforeEach
    void setup(){
        seadistTest = new Seadist(12345, 123456, 330f, "firstPort", "secondPort", "firstCountry", "secondCountry");
    }

    @Test
    void getPortId1() {
        int result = seadistTest.getPortId1();
        assertEquals(12345, result);
    }

    @Test
    void getPortId2() {
        int result = seadistTest.getPortId2();
        assertEquals(123456, result);
    }

    @Test
    void getSeaDistance() {
        float result = seadistTest.getSeaDistance();
        assertEquals(330f, result, 0);
    }

    @Test
    void getPortName1() {
        String result = seadistTest.getPortName1();
        assertEquals("firstPort", result);
    }

    @Test
    void getPortName2() {
        String result = seadistTest.getPortName2();
        assertEquals("secondPort", result);
    }

    @Test
    void getCountryName1() {
        String result = seadistTest.getCountryName1();
        assertEquals("firstCountry", result);
    }

    @Test
    void getCountryName2() {
        String result = seadistTest.getCountryName2();
        assertEquals("secondCountry", result);
    }
}