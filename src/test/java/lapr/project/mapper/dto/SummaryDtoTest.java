package lapr.project.mapper.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SummaryDtoTest {

    @Test
    void getMmsiCode() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getMmsiCodeDto(), "210950000");
    }

    @Test
    void getName() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getNameDto(), "VARAMO");
    }

    @Test
    void getStartBaseDate() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getStartBaseDateDto(), new Date(2020, 12, 31, 17, 19));
    }

    @Test
    void getEndBaseDate() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getEndBaseDateDto(), new Date(2020, 12, 24, 14, 30));

    }

    @Test
    void getTotalMovementTime() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getTotalMovementTimeDto(), "02:30:12");
    }

    @Test
    void getTotalMovements() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getTotalMovements(), 23);
    }

    @Test
    void getMaximumSog() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getMaximumSogDto(), 156, 0.0);
    }

    @Test
    void getMeanSog() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getMeanSogDto(), 124, 0.0);
    }

    @Test
    void getMaximumCog() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getMaximumCogDto(), 16, 0.0);
    }

    @Test
    void getMeanCog() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getMeanCogDto(), 12, 0.0);
    }

    @Test
    void getDepartureLatitude() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getDepartureLatitudeDto(), "42.97875");
    }

    @Test
    void getDepartureLongitude() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getDepartureLongitudeDto(), "-66.97001");
    }

    @Test
    void getArrivalLatitude() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getArrivalLatitudeDto(), "42.92236");
    }

    @Test
    void getArrivalLongitude() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getArrivalLongitudeDto(), "-66.97243");
    }

    @Test
    void getTravelledDistance() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getTravelledDistanceDto(), 120, 0.0);
    }

    @Test
    void getDeltaDistance() {
        SummaryDto summaryDto = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        assertEquals(summaryDto.getDeltaDistanceDto(), 50, 0.0);
    }
}