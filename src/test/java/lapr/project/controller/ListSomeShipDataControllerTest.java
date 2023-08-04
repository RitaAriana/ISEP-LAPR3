package lapr.project.controller;

import lapr.project.model.BriefSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ListSomeShipDataControllerTest {

    List<BriefSummary> briefSummariesA = new ArrayList<>();

    List<BriefSummary> briefSummariesD = new ArrayList<>();

    @BeforeEach
    public void SetUp() throws IOException {
        ImportShipController impShipCTR = new ImportShipController();
        impShipCTR.importFile("sships.csv");
        impShipCTR.importShips();
        ListSomeShipDataController controller = new ListSomeShipDataController();
        briefSummariesA = controller.organizeByAscendingOrder();
        briefSummariesD = controller.organizeByDescendingOrder();
    }

    @Test
    public void OrganizeByDescendingOrder(){

        boolean flag = true;
        BriefSummary anterior = briefSummariesD.get(0);
        for(int i=1; i<briefSummariesD.size(); i++) {
            BriefSummary atual = briefSummariesD.get(i);
            if (atual.getTravelledDistance()>anterior.getTravelledDistance()) {
                flag = false;
                break;
            }
            atual = anterior;
        }
        assertTrue(flag);
    }

    @Test
    public void CompareOrderDTD(){
        BriefSummary ant = briefSummariesD.get(0);
        BriefSummary atual = briefSummariesD.get(1);
        boolean fl1 = false, fl2 = false;
        if (atual.getTravelledDistance() < ant.getTravelledDistance()) {
            fl1 = true;
        }
        if (ant.getTravelledDistance() > atual.getTravelledDistance()) {
            fl2 = true;
        }
        assertTrue(fl1);
        assertTrue(fl2);
    }

    @Test
    public void OrganizeByAscendingOrder(){

        boolean flag = true;
        BriefSummary anterior = briefSummariesA.get(0);
        for( int i=1; i<briefSummariesA.size(); i++) {
            BriefSummary atual = briefSummariesA.get(i);
            if (atual.getTotalNumberOfMovements()<anterior.getTotalNumberOfMovements()) {
                flag = false;
                break;
            }
            atual = anterior;
        }
        assertTrue(flag);
    }

    /*
    @Test
    public void CompareOrderATNM(){
        BriefSummary ant = briefSummariesD.get(0);
        BriefSummary atual = briefSummariesD.get(1);
        boolean fl1 = false, fl2 = false;
        if (ant.getTotalNumberOfMovements() < atual.getTotalNumberOfMovements()) {
            fl1 = true;
        }
        if (atual.getTotalNumberOfMovements() > ant.getTotalNumberOfMovements()) {
            fl2 = true;
        }
        assertTrue(fl1);
        assertTrue(fl2);
    }*/

}