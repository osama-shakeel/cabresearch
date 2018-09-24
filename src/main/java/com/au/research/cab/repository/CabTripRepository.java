package com.au.research.cab.repository;

import com.au.research.cab.entity.CabTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CabTripRepository extends JpaRepository<CabTrip, String> {

    /**
     * Find trip count of a Cab, identified by mediallion, for a given date range of pickup dates.
     * @param medallion Cab's identification
     * @param fromDateTime Start date of pickup.
     * @param toDateTime End date of pickup
     * @return Trip count
     */
    public long countByMedallionAndPickupDateTimeGreaterThanEqualAndPickupDateTimeBefore(
            @Param("medallion") String medallion,
            @Param("fromDateTime") LocalDateTime fromDateTime,
            @Param("toDateTime") LocalDateTime toDateTime);

}
