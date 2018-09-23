package com.au.research.cab.repository;

import com.au.research.cab.entity.CabTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CabTripRepository extends JpaRepository<CabTrip, String> {

    /**
     * Find trip count of a Cab, identified by mediallion, for a given date range of pickup dates.
     * @param medallion Cab's identification
     * @param fromDateTime Start date of pickup.
     * @param toDateTime End date of pickup
     * @return Trip count
     */
//    @Query("select count(*) " +
//            "from CabTrip c " +
//            "where c.medallion = :medallion " +
//            "and c.pickupDateTime >= :fromDateTime " +
//            "and c.pickupDateTime < :toDateTime")
    public long countByMedallionAndPickupDateTimeGreaterThanEqualAndPickupDateTimeBefore(
            @Param("medallion") String medallion,
            @Param("fromDateTime") LocalDateTime fromDateTime,
            @Param("toDateTime") LocalDateTime toDateTime);

//    @Query("from CabTrip c " +
//            "where c.medallion = :medallion " +
//            "and c.pickupDateTime >= :fromDateTime " +
//            "and c.pickupDateTime < :toDateTime")
//    public List<CabTrip> findCabTripsByMediallionAndPickupDateRange(
//            @Param("medallion") String medallion,
//            @Param("fromDateTime") LocalDateTime fromDateTime,
//            @Param("toDateTime") LocalDateTime toDateTime);
}
