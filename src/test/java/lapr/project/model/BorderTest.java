package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BorderTest {

    private Border borderTest;

    @BeforeEach
    void setUp(){
        borderTest = new Border("Portugal", "Espanha");
    }

    @Test
    void getCountryName() {

        String result = borderTest.getCountryName();
        assertEquals("Portugal", result);
    }

    @Test
    void getCountryName2() {
        String result = borderTest.getCountryName2();
        assertEquals("Espanha", result);
    }
}