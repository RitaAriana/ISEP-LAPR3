package lapr.project.model.store;

import lapr.project.model.Seadist;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeadistStoreTest {

    @Test
    void getSeadistLst() {
        SeadistStore seadistStore = new SeadistStore();
        boolean seadistStrEmpty = seadistStore.getSeadistLst().isEmpty();
        assertTrue(seadistStrEmpty);
    }

    @Test
    void createSeadist() {
        Seadist seadist = new Seadist(12345, 12346, 23f, "Leixoes", "Oriente", "Portugal", "Portugal");
        SeadistStore seadistStore = new SeadistStore();
        Seadist seadist2 = seadistStore.createSeadist(12345, 12346, 23f, "Leixoes", "Oriente", "Portugal", "Portugal");
        boolean equalSeadists = true;
        try{
            if(seadist.getPortId1() != seadist2.getPortId1())
                throw new IllegalArgumentException();
            if(seadist.getPortId2() != seadist2.getPortId2())
                throw new IllegalArgumentException();
            if(seadist.getSeaDistance() != seadist2.getSeaDistance())
                throw new IllegalArgumentException();
            if(!seadist.getPortName1().equals(seadist2.getPortName1()))
                throw new IllegalArgumentException();
            if(!seadist.getPortName2().equals(seadist2.getPortName2()))
                throw new IllegalArgumentException();
            if(!seadist.getCountryName1().equals(seadist2.getCountryName1()))
                throw new IllegalArgumentException();
            if(!seadist.getCountryName2().equals(seadist2.getCountryName2()))
                throw new IllegalArgumentException();
        } catch (Exception e){
            equalSeadists = false;
        }
        assertTrue(equalSeadists);
    }

    @Test
    void saveSeadist() {
        SeadistStore seadistStr = new SeadistStore();
        Seadist seadist = new Seadist(12345, 12346, 23f, "Leixoes", "Oriente", "Portugal", "Portugal");
        seadistStr.saveSeadist(seadist);

        assertFalse(seadistStr.getSeadistLst().isEmpty());
    }

    @Test
    void getSeadistByID() {
        SeadistStore seadistStr = new SeadistStore();
        Seadist seadist = seadistStr.createSeadist(12345, 12346, 23f, "Leixoes", "Oriente", "Portugal", "Portugal");
        Seadist seadist2 = seadistStr.createSeadist(12344, 12345, 23f, "Leixoes", "Oriente", "Portugal", "Portugal");
        Seadist seadist3 = seadistStr.createSeadist(11111, 22222, 23f, "Leixoes", "Oriente", "Portugal", "Portugal");
        seadistStr.saveSeadist(seadist3);
        seadistStr.saveSeadist(seadist2);
        seadistStr.saveSeadist(seadist);

        List<Seadist> seadistToBeFoundLst = seadistStr.getSeadistsByID(12345);

        boolean seadistExists = seadistToBeFoundLst != null;

        assertTrue(seadistExists);
    }

    @Test
    void getSeadistByIDNull() {
        SeadistStore seadistStr = new SeadistStore();
        Seadist seadist = seadistStr.createSeadist(12345, 12346, 23f, "Leixoes", "Oriente", "Portugal", "Portugal");
        Seadist seadist2 = seadistStr.createSeadist(12344, 12346, 23f, "Leixoes", "Oriente", "Portugal", "Portugal");
        seadistStr.saveSeadist(seadist2);
        seadistStr.saveSeadist(seadist);

        List<Seadist> seadistToBeFoundLst = seadistStr.getSeadistsByID(00000);

        boolean seadistExists = seadistToBeFoundLst != null;

        assertFalse(seadistExists);
    }
}