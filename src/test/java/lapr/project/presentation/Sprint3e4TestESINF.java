package lapr.project.presentation;

import lapr.project.controller.ClosenessLocalsController;
import lapr.project.controller.ColorFreightNetworkController;
import lapr.project.controller.CreateFreightNetworkController;
import lapr.project.controller.GetsPortsMoreCriticalController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Sprint3e4TestESINF {

    private CreateFreightNetworkController createFreightNetworkController;

    private ColorFreightNetworkController colorFreightNetworkController;

    private ClosenessLocalsController closenessLocalsController;

    private GetsPortsMoreCriticalController getsPortsMoreCriticalController;

    public Sprint3e4TestESINF(){
        createFreightNetworkController = new CreateFreightNetworkController();
        colorFreightNetworkController = new ColorFreightNetworkController();
        closenessLocalsController = new ClosenessLocalsController();
        getsPortsMoreCriticalController = new GetsPortsMoreCriticalController();
    }

    @Test
    public void presentation() throws IOException {
        createFreightNetworkController.createFreightNetwork(1);
        colorFreightNetworkController.colorNetwork();
        closenessLocalsController.closenessLocals(5);
        getsPortsMoreCriticalController.getsPortsMoreCritical(5);
    }
}
