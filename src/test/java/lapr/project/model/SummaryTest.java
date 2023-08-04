package lapr.project.model;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SummaryTest {

    @Test
    void getMmsiCode() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getMmsiCode(), "210950000");
    }

    @Test
    void getName() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getName(), "VARAMO");
    }

    @Test
    void getStartBaseDate() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getStartBaseDate(), new Date(2020, 12, 31, 17, 19));
    }

    @Test
    void getEndBaseDate() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getEndBaseDate(), new Date(2020, 12, 24, 14, 30));

    }

    @Test
    void getTotalMovementTime() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getTotalMovementTime(), "02:30:12");
    }

    @Test
    void getTotalMovements() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getTotalMovements(), 23);
    }

    @Test
    void getMaximumSog() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getMaximumSog(), 156, 0.0);
    }

    @Test
    void getMeanSog() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getMeanSog(), 124, 0.0);
    }

    @Test
    void getMaximumCog() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getMaximumCog(), 16, 0.0);
    }

    @Test
    void getMeanCog() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getMeanCog(), 12, 0.0);
    }

    @Test
    void getDepartureLatitude() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getDepartureLatitude(), "42.97875");
    }

    @Test
    void getDepartureLongitude() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getDepartureLongitude(), "-66.97001");
    }

    @Test
    void getArrivalLatitude() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getArrivalLatitude(), "42.92236");
    }

    @Test
    void getArrivalLongitude() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getArrivalLongitude(), "-66.97243");
    }

    @Test
    void getTravelledDistance() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getTravelledDistance(), 120, 0.0);
    }

    @Test
    void getDeltaDistance() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summary.getDeltaDistance(), 50, 0.0);
    }

    @Test
    void testEquals() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        Summary summary2 = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);

        assertTrue(summary.equals(summary2));
    }

    @Test
    void testEqualsReferences() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        Summary summary2 = summary;

        assertTrue(summary.equals(summary2));
    }

    @Test
    void testEqualsNull() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        Summary summary2 = null;

        assertFalse(summary.equals(summary2));
    }

    @Test
    void testEqualsObjectOfOtherClass() {
        Summary summary = new Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        Object summary2 = "Summary";

        assertFalse(summary.equals(summary2));
    }


}