package lapr.project.model.store;

import lapr.project.model.CargoManifest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CargoManifestStoreTest {

    private List<CargoManifest> cargoManifestList;

    private CargoManifestStore cargoManifestStore;

    @BeforeEach
    void setUp() {
        cargoManifestList = new ArrayList<>();
        cargoManifestStore = new CargoManifestStore();
    }

    @Test
    void createCargoManifest() {
        CargoManifest cargoManifest = new CargoManifest(new Date(2020, Calendar.NOVEMBER, 19), 30, "210950000", CargoManifest.Destination.PORT);
        assertEquals(cargoManifest, cargoManifestStore.createCargoManifest(new Date(2020, Calendar.NOVEMBER,19), 30, "210950000", CargoManifest.Destination.PORT));
    }

    @Test
    void saveCargoManifestNotExist() {
        CargoManifest cargoManifest = new CargoManifest(new Date(), 30, "210950000", CargoManifest.Destination.PORT);
        assertTrue(cargoManifestStore.saveCargoManifest(cargoManifest));
    }

//    @Test
//    void saveCargoManifestExist() {
//        CargoManifest cargoManifest = new CargoManifest(new Date(), 30, "210950000", CargoManifest.Destination.PORT);
//        cargoManifestStore.saveCargoManifest(cargoManifest);
//        CargoManifest cargoManifest1 = new CargoManifest(new Date(), 30, "210950000", CargoManifest.Destination.PORT);
//        assertFalse(cargoManifestStore.saveCargoManifest(cargoManifest1));
//    }

    @Test
    void getCargoManifestByMmsiCodeExisting() {
        CargoManifest cargoManifest = new CargoManifest(new Date(), 30, "210950000", CargoManifest.Destination.PORT);
        cargoManifestStore.saveCargoManifest(cargoManifest);
        cargoManifestList.add(cargoManifest);
        assertEquals(cargoManifestStore.getCargoManifestByMmsiCode("210950000"), cargoManifestList);
    }

    @Test
    void getCargoManifestByMmsiCodeNotExisting() {
        CargoManifest cargoManifest = new CargoManifest(new Date(), 30, "210950000", CargoManifest.Destination.PORT);
        cargoManifestStore.saveCargoManifest(cargoManifest);
        assertEquals(cargoManifestStore.getCargoManifestByMmsiCode("210950001"), cargoManifestList);
    }

    @Test
    void getMeanContainersPerCargoManifestListSizeZero() {
        assertEquals(cargoManifestStore.getMeanContainersPerCargoManifest(cargoManifestList), 0);
    }
    @Test
    void getMeanContainersPerCargoManifest() {
        CargoManifest cargoManifest = new CargoManifest(new Date(), 30, "210950000", CargoManifest.Destination.PORT);
        CargoManifest cargoManifest1 = new CargoManifest(new Date(), 50, "210950000", CargoManifest.Destination.PORT);
        CargoManifest cargoManifest2 = new CargoManifest(new Date(), 100, "210950000", CargoManifest.Destination.PORT);
        cargoManifestList.add(cargoManifest);
        cargoManifestList.add(cargoManifest1);
        cargoManifestList.add(cargoManifest2);
        assertEquals(cargoManifestStore.getMeanContainersPerCargoManifest(cargoManifestList), 60, 0.1);
    }

    @Test
    void getTheCargoManifestPerYearListSizeZero() {
        assertEquals(cargoManifestStore.getTheCargoManifestPerYear(cargoManifestList, 2020), 0);
    }

    @Test
    void getTheCargoManifestPerYear2020() {
        CargoManifest cargoManifest = new CargoManifest(new Date(), 30, "210950000", CargoManifest.Destination.PORT);
        CargoManifest cargoManifest1 = new CargoManifest(new Date(), 50, "210950000", CargoManifest.Destination.PORT);
        CargoManifest cargoManifest2 = new CargoManifest(new Date(), 100, "210950000", CargoManifest.Destination.PORT);
        cargoManifestList.add(cargoManifest);
        cargoManifestList.add(cargoManifest1);
        cargoManifestList.add(cargoManifest2);
        assertEquals(cargoManifestStore.getTheCargoManifestPerYear(cargoManifestList, 2020), 0);
    }

    @Test
    void getTheCargoManifestPerYear2021() {
        CargoManifest cargoManifest = new CargoManifest(new Date(2021, 11, 19), 30, "210950000", CargoManifest.Destination.PORT);
        CargoManifest cargoManifest1 = new CargoManifest(new Date(2021, 11, 19), 50, "210950000", CargoManifest.Destination.PORT);
        CargoManifest cargoManifest2 = new CargoManifest(new Date(2021, 11, 19), 100, "210950000", CargoManifest.Destination.PORT);
        cargoManifestList.add(cargoManifest);
        cargoManifestList.add(cargoManifest1);
        cargoManifestList.add(cargoManifest2);
        assertEquals(cargoManifestStore.getTheCargoManifestPerYear(cargoManifestList, 2021), 3);
    }

    @Test
    void getTheCargoManifestPerYear() {
        CargoManifest cargoManifest = new CargoManifest(new Date(2019, 11, 19), 30, "210950000", CargoManifest.Destination.PORT);
        CargoManifest cargoManifest1 = new CargoManifest(new Date(2021, 11, 19), 50, "210950000", CargoManifest.Destination.PORT);
        CargoManifest cargoManifest2 = new CargoManifest(new Date(2017, 11, 19), 100, "210950000", CargoManifest.Destination.PORT);
        cargoManifestList.add(cargoManifest);
        cargoManifestList.add(cargoManifest1);
        cargoManifestList.add(cargoManifest2);
        assertEquals(cargoManifestStore.getTheCargoManifestPerYear(cargoManifestList, 2017), 1);
    }
}