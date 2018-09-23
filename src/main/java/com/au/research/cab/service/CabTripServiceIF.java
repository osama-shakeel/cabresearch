package com.au.research.cab.service;

import com.au.research.cab.model.CabTripCount;

import java.time.LocalDate;

public interface CabTripServiceIF {

    /**
     * Get cab trip count for a given cab identified by its medallion
     * and pickup day.
     * @param medallion Cab's identifier
     * @param pickupDate Pickup date for the cab
     * @return Cab Trip count.
     */
    CabTripCount getCabDayTripCount(String medallion, LocalDate pickupDate);
}
