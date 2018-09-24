package com.au.research.cab.repository;

import com.au.research.cab.entity.CabTrip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class CabTripRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CabTripRepository cabTripRepository;


    /**
     * Tests finding a single Cab Trip.
     * Scenario: For an existing cab and trip a single Cab Trip
     * is expected to be returned.
     */
    @Test
    public void testFindCabTripById() {
        // Prepare Test Data
        String medallion = "D7D598CD99978BD012A87A76A7C891B7";
        LocalDateTime pickupDateTime = LocalDateTime.of(2013, 12, 1, 0, 13, 0);

        Optional <CabTrip> cabTripOptional = cabTripRepository.findById(medallion);
        assertTrue("Cab Trip by ID not found", cabTripOptional.isPresent());
        CabTrip cabTrip = cabTripOptional.get();
        assertEquals("Cab Trip's medallion does not match", cabTrip.getMedallion(), medallion);
    }

    /**
     * Tests finding a Cab's trip count for a given date range.
     * Scenario: For an existing Cab 3 trips are expected to be returned.
     */
    @Test
    public void testFindTripCountByMediallionAndDate_AndReturnTripCount() {
        // Prepare Test Data
        String medallion = "D7D598CD99978BD012A87A76A7C891B7";

        // 01-12-2013 00:00:00
        LocalDateTime fromDateTime = LocalDateTime.of(2013, 12, 1, 0, 0, 0);

        // 02-12-2013 00:00:00
        LocalDateTime toDateTime = fromDateTime.plusDays(1);

        // Call test method
        long tripCount =
                cabTripRepository.countByMedallionAndPickupDateTimeGreaterThanEqualAndPickupDateTimeBefore(
                        medallion, fromDateTime, toDateTime);
        assertTrue(tripCount == 3L);
    }

    /**
     * Tests finding Cab's trips for a given date range.
     * Scenario: For non existing Cab the no cab trip is expected to be returned.
     */
    @Test
    public void testFindCabTripsByMediallionAndDate_AndReturnNoTrip() {
        // Prepare Test Data
        String medallion = "ABC";

        // 01-12-2013 00:00:00
        LocalDateTime fromDateTime = LocalDateTime.of(2013, 12, 1, 0, 0, 0);

        // 02-12-2013 00:00:00
        LocalDateTime toDateTime = fromDateTime.plusDays(1);

        // Call test method
        long tripCount =
                cabTripRepository.countByMedallionAndPickupDateTimeGreaterThanEqualAndPickupDateTimeBefore(
                        medallion, fromDateTime, toDateTime);
        assertTrue(tripCount == 0);
    }
}
