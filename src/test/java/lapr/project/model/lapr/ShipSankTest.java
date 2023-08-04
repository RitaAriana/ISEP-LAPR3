package lapr.project.model.lapr;

import lapr.project.model.Ship;
import lapr.project.model.ShipLocation;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipSankTest {

    private ShipSank shipSank;

    @Test
    public void pressureExertedByVesselOnWater() {
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double result = shipSank.pressureExertedByVesselOnWater(ship1, 152 * Math.pow(10, 6), 20000);
        System.out.println(result);
        double expected = 102868.20599307946;
        assertEquals(result, expected);
    }

    @Test
    public void calculateWeightForce () {
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double result = shipSank.calculateWeightForce(152 * Math.pow(10, 6));
        double expected = 1.4896E9;
        assertEquals(result, expected);
    }

    @Test
    public void calculatePressure() {
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double result = shipSank.calculatePressure(ship1, shipSank.calculateWeightForce(152 * Math.pow(10, 6)));
        double expected = 96518.31673424739;
        assertEquals(result, expected);
    }

    @Test
    public void shipMass(){
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double result = shipSank.shipMass(152 * Math.pow(10, 6),20000);
        double expected = 1.62E8;
        assertEquals(result, expected);
    }

    @Test
    public void vesselImmersedVolumeWithZeroContainers() {
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double result = shipSank.vesselImmersedVolume(152 * Math.pow(10, 6),0);
        double expected = 147572.81553398058;
        assertEquals(result, expected);
    }

    @Test
    public void vesselImmersedVolumeWith20kContainers() {
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double result = shipSank.vesselImmersedVolume(152 * Math.pow(10, 6),20000);
        double expected = 157281.55339805825;
        assertEquals(result, expected);
    }

    @Test
    public void calculateDraftVesselEmpty(){
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double immersedVolume = shipSank.vesselImmersedVolume(152 * Math.pow(10, 6),0);
        double result = shipSank.calculateDraft(ship1,immersedVolume);
        double expected = 10.273796681563672;
        assertEquals(result, expected);
    }

    @Test
    public void calculateDraftVesselWith20kContainers(){
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double immersedVolume = shipSank.vesselImmersedVolume(152 * Math.pow(10, 6),20000);
        double result = shipSank.calculateDraft(ship1,immersedVolume);
        double expected = 10.949704357982334;
        assertEquals(result, expected);
    }

    @Test
    public void shipSankHeigth(){
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double immersedVolumeEmpty = shipSank.vesselImmersedVolume(152 * Math.pow(10, 6),0);
        double immersedVolumeLoaded = shipSank.vesselImmersedVolume(152 * Math.pow(10, 6),20000);
        double emptyDraft = shipSank.calculateDraft(ship1,immersedVolumeEmpty);
        double loadedDraft = shipSank.calculateDraft(ship1,immersedVolumeLoaded);
        double result = loadedDraft - emptyDraft;
        double expected = 0.675907676418662;
        assertEquals(result, expected);
    }

    @Test
    public void getShipSankHeight() {;
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);
        shipSank.shipSankHeight(ship1,152 * Math.pow(10, 6),20000);
        double result = shipSank.getShipSankHeight();
        double expected = 0.675907676418662;
        assertEquals(result, expected);
    }

    @Test
    public void immersedArea(){
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);

        double result = shipSank.immersedArea(ship1);
        double expected = 15433.340016708438;
        assertEquals(result, expected);
    }
}
