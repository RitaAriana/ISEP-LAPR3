package lapr.project.mapper;

import lapr.project.mapper.dto.SummaryDto;
import lapr.project.model.Summary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class SummaryMapperTest {


    @Test
    void toDto() {
        Summary summary = new lapr.project.model.Summary("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        SummaryMapper summaryMapper = new SummaryMapper();
        SummaryDto summaryDtoDto1 = new SummaryDto("210950000", "VARAMO", new Date(2020, 12, 31, 17, 19), new Date(2020, 12, 24, 14, 30), "02:30:12", 23, 156, 124, 16, 12, "42.97875", "-66.97001", "42.92236", "-66.97243" , 120, 50);
        SummaryDto summaryDtoDto2 = summaryMapper.toDto(summary);

        Assertions.assertEquals(summaryDtoDto2.toString(), summaryDtoDto1.toString());

    }
}