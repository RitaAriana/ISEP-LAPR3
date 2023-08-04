package lapr.project.model.store;

import lapr.project.model.Border;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorderStoreTest {

    @Test
    void getBorderLst() {
        BorderStore borderStr = new BorderStore();
        boolean borderStrEmpty = borderStr.getBorderLst().isEmpty();
        assertTrue(borderStrEmpty);
    }

    @Test
    void createBorder() {
        Border border = new Border("Portugal", "Espanha");
        BorderStore borderStr = new BorderStore();
        Border border2 = borderStr.createBorder("Portugal", "Espanha");
        boolean equalBorders = true;
        try{
            if(!border.getCountryName().equals(border2.getCountryName()))
                throw new IllegalArgumentException();
            if(!border.getCountryName2().equals(border2.getCountryName2()))
                throw new IllegalArgumentException();
        } catch (Exception e){
            equalBorders = false;
        }
        assertTrue(equalBorders);
    }

    @Test
    void saveBorder() {
        BorderStore borderStr = new BorderStore();
        Border border = borderStr.createBorder("Portugal", "Espanha");
        borderStr.saveBorder(border);

        assertFalse(borderStr.getBorderLst().isEmpty());
    }

    @Test
    void getBorderByName() {
        BorderStore borderStr = new BorderStore();
        Border border = borderStr.createBorder("Espanha", "Portugal");
        Border border2 = borderStr.createBorder("França", "Espanha");

        borderStr.saveBorder(border2);
        borderStr.saveBorder(border);

        List<Border> borderLst = borderStr.getBorderByName("Espanha");

        assertFalse(borderLst.isEmpty());
    }

    @Test
    void getBorderByNameNull() {
        BorderStore borderStr = new BorderStore();
        Border border = borderStr.createBorder("Espanha", "Portugal");

        borderStr.saveBorder(border);

        List<Border> borderLst = borderStr.getBorderByName("Itália");

        boolean borderListEmpty = borderLst == null;

        assertTrue(borderListEmpty);
    }
}