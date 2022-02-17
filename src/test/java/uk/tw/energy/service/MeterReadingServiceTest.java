package uk.tw.energy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.generator.ElectricityReadingsGenerator;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MeterReadingServiceTest {

    private MeterReadingService meterReadingService;

//    @Autowired
    private ElectricityReadingsGenerator electricityReadingsGenerator;

    @BeforeEach
    public void setUp() {
        meterReadingService = new MeterReadingService(new HashMap<>());
    }

    @Test
    public void givenMeterIdThatDoesNotExistShouldReturnNull() {
        assertThat(meterReadingService.getReadings("unknown-id")).isEqualTo(Optional.empty());
    }

    @Test
    public void givenMeterReadingThatExistsShouldReturnMeterReadings() {
        meterReadingService.storeReadings("random-id", new ArrayList<>());
        assertThat(meterReadingService.getReadings("random-id")).isEqualTo(Optional.of(new ArrayList<>()));
    }

    @Test
    public void givenReadingsShouldBeStoredInCorrectMeterId(){
        ElectricityReadingsGenerator readingsBuilder = new ElectricityReadingsGenerator();
        List<ElectricityReading> electricityReadings = readingsBuilder.generate(20);
        meterReadingService.storeReadings("10101",electricityReadings);
        System.out.println(meterReadingService.getReadings("10101"));
        //assertThat(meterReadingService.getReadings("101")).isEqualTo(electricityReadings);
        assertThat(meterReadingService.getReadings("102")).isNotPresent();
    }
}
