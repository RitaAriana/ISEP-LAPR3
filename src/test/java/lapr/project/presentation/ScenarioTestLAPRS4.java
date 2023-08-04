package lapr.project.presentation;
import lapr.project.data.VesselSankController;
import lapr.project.model.Ship;
import lapr.project.model.ShipLocation;
import lapr.project.model.lapr.ShipSank;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

public class ScenarioTestLAPRS4 {

    private VesselSankController vesselSankController;

    private ShipSank shipSank;

    public ScenarioTestLAPRS4(){
        vesselSankController = new VesselSankController();
    }

    @Test
    public void presentation() throws IOException {
        Date dateToUse = new Date();
        ShipLocation shipl = new ShipLocation("111111111", dateToUse, "56", "88", 32f, 44f, "330", "B");
        shipSank = new ShipSank();
        Ship ship1 = new Ship("111111111", "SHIP1", "IMO1111111", 4, 200.0f, "REEFER", 70, 342f, 42f, "ASD", 10.27f, shipl);
        vesselSankController.vesselSank(ship1,152 * Math.pow(10, 6),20000);
        vesselSankController.writeForAFile();
    }
}
