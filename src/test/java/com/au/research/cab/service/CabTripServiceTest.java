package com.au.research.cab.service;

import com.au.research.cab.model.CabTripCount;
import com.au.research.cab.repository.CabTripRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CabTripServiceTest {

    @InjectMocks
    private CabTripService cabTripService;

    @Mock
    private CabTripRepository cabTripRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(cabTripService, "cabTripRepository", cabTripRepository);
    }

    /**
     * Test getting cab trip count for a cab on a pickup date.
     * Scenario: The cab exists with 3 cab trips for a certain pickup date.
     */
    @Test
    public void testGetCabTripCountForAMedallionAndPickupDate() {
        // Prepare Test Data
        String medallion = "D7D598CD99978BD012A87A76A7C891B7";
        String pickupDate = "2013-12-01";
        LocalDate localDate = LocalDate.from(formatter.parse(pickupDate));
        LocalDateTime pickupDateTime = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
        long tripCount = 3L;

        when(cabTripRepository.countByMedallionAndPickupDateTimeGreaterThanEqualAndPickupDateTimeBefore(medallion, pickupDateTime, pickupDateTime.plusDays(1L)))
                .thenReturn(tripCount);

        CabTripCount cabDayTripCount = cabTripService.getCabDayTripCount(medallion, localDate);
        assertNotNull("Cab trip count cannot be null", cabDayTripCount);
        assertEquals(cabDayTripCount.getMedallion(), medallion);
        assertEquals(cabDayTripCount.getPickupDate(), pickupDate);
        assertTrue(cabDayTripCount.getTripCount() == tripCount);
    }

    /**
     * Test getting cab trip count for a cab on a pickup date.
     * Scenario: The cab exists with no cab trips for a certain pickup date.
     */
    @Test
    public void testGetCabTripCountForAMedallionAndNonExistantPickupDate() {
        // Prepare Test Data
        String medallion = "D7D598CD99978BD012A87A76A7C891B7";
        String pickupDate = "1980-12-01";
        LocalDate localDate = LocalDate.from(formatter.parse(pickupDate));
        LocalDateTime pickupDateTime = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
        Long tripCount = 0L;

        when(cabTripRepository.countByMedallionAndPickupDateTimeGreaterThanEqualAndPickupDateTimeBefore(medallion, pickupDateTime, pickupDateTime.plusDays(1L)))
                .thenReturn(tripCount);

        CabTripCount cabDayTripCount = cabTripService.getCabDayTripCount(medallion, localDate);
        assertNotNull("Cab trip count cannot be null", cabDayTripCount);
        assertEquals(cabDayTripCount.getMedallion(), medallion);
        assertEquals(cabDayTripCount.getPickupDate(), pickupDate);
        assertTrue(cabDayTripCount.getTripCount() == tripCount);
    }

    /**
     * Test getting cab trip count for a cab on a pickup date.
     * Scenario: The cab does not exist. No trip count is expected.
     */
    @Test
    public void testGetCabTripCountForNonExistantMedallionAndPickupDate() {
        // Prepare Test Data
        String medallion = "ABC";
        String pickupDate = "2013-12-01";
        LocalDate localDate = LocalDate.from(formatter.parse(pickupDate));
        LocalDateTime pickupDateTime = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
        Long tripCount = 0L;

        when(cabTripRepository.countByMedallionAndPickupDateTimeGreaterThanEqualAndPickupDateTimeBefore(medallion, pickupDateTime, pickupDateTime.plusDays(1L)))
                .thenReturn(tripCount);

        CabTripCount cabDayTripCount = cabTripService.getCabDayTripCount(medallion, localDate);
        assertNotNull("Cab trip count cannot be null", cabDayTripCount);
        assertEquals(cabDayTripCount.getMedallion(), medallion);
        assertEquals(cabDayTripCount.getPickupDate(), pickupDate);
        assertTrue(cabDayTripCount.getTripCount() == tripCount);
    }

}
