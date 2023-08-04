package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BriefSummaryTest {

    @Test
    public void getMmsiCode() {
        BriefSummary ship1 = new BriefSummary("211331640",31,2222222, 3333333);
        assertEquals(ship1.getMmsiCode(), "211331640");
    }

    @Test
    public void getTotalNumberOfMovements() {
        BriefSummary ship1 = new BriefSummary("211331640",31,2222222.22, 3333333);
        assertEquals(ship1.getTotalNumberOfMovements(), 31);
    }

    @Test
    public void getDeltaDistance() {
        BriefSummary ship1 = new BriefSummary("211331640",31,2222222.22, 3333333);
        assertEquals(ship1.getDeltaDistance(), 2222222.22,0.0);
    }

    @Test
    public void getTravelledDistance() {
        BriefSummary ship1 = new BriefSummary("211331640",31,2222222.22, 3333333);
        assertEquals(ship1.getTravelledDistance(), 3333333,0.0);
    }
}