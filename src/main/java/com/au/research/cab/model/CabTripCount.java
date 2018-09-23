package com.au.research.cab.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CabTripCount implements Serializable {
    private static final long serialVersionUID = -8035821471220841280L;

    private String medallion;
    private String pickupDate;
    private long tripCount;

    public CabTripCount() {
    }

    public CabTripCount(String medallion, String pickupDate, long tripCount) {
        this.medallion = medallion;
        this.pickupDate = pickupDate;
        this.tripCount = tripCount;
    }

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public long getTripCount() {
        return tripCount;
    }

    public void setTripCount(long tripCount) {
        this.tripCount = tripCount;
    }

    @Override
    public String toString() {
        return "CabTripCount[" +
                "medallion='" + medallion + '\'' +
                ", pickupDate='" + pickupDate + '\'' +
                ", tripCount=" + tripCount +
                ']';
    }
}
