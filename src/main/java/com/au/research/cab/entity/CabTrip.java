package com.au.research.cab.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cab_trip_data")
public class CabTrip {

    @Id
    private String medallion;

    @Column(name = "pickup_datetime")
    private LocalDateTime pickupDateTime;

    @Column(name = "hack_license")
    private String hackLicense;

    @Column(name = "vendor_id")
    private String vendorId;

    @Column(name = "rate_code")
    private Long rateCode;

    @Column(name = "store_and_fwd_flag")
    private String storeAndFwdFlag;

    @Column(name = "dropoff_datetime")
    private LocalDateTime dropoffDatetime;

    @Column(name = "passenger_count")
    private Long passengerCount;

    @Column(name = "trip_time_in_secs")
    private Long tripTimeInSecs;

    @Column(name = "trip_distance")
    private Double tripDistance;

    @Column(name = "pickup_longitude")
    private Double pickupLongitude;

    @Column(name = "pickup_latitude")
    private Double pickupLatitude;

    @Column(name = "dropoff_longitude")
    private Double dropoffLongitude;

    @Column(name = "dropoff_latitude")
    private Double dropoffLatitude;

    public CabTrip() {
    }

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public String getHackLicense() {
        return hackLicense;
    }

    public void setHackLicense(String hackLicense) {
        this.hackLicense = hackLicense;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Long getRateCode() {
        return rateCode;
    }

    public void setRateCode(Long rateCode) {
        this.rateCode = rateCode;
    }

    public String getStoreAndFwdFlag() {
        return storeAndFwdFlag;
    }

    public void setStoreAndFwdFlag(String storeAndFwdFlag) {
        this.storeAndFwdFlag = storeAndFwdFlag;
    }

    public LocalDateTime getPickupDateTime() {
        return pickupDateTime;
    }

    public void setPickupDateTime(LocalDateTime pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    public LocalDateTime getDropoffDatetime() {
        return dropoffDatetime;
    }

    public void setDropoffDatetime(LocalDateTime dropoffDatetime) {
        this.dropoffDatetime = dropoffDatetime;
    }

    public Long getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Long passengerCount) {
        this.passengerCount = passengerCount;
    }

    public Long getTripTimeInSecs() {
        return tripTimeInSecs;
    }

    public void setTripTimeInSecs(Long tripTimeInSecs) {
        this.tripTimeInSecs = tripTimeInSecs;
    }

    public Double getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(Double tripDistance) {
        this.tripDistance = tripDistance;
    }

    public Double getPickupLongitude() {
        return pickupLongitude;
    }

    public void setPickupLongitude(Double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public Double getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPickupLatitude(Double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public Double getDropoffLongitude() {
        return dropoffLongitude;
    }

    public void setDropoffLongitude(Double dropoffLongitude) {
        this.dropoffLongitude = dropoffLongitude;
    }

    public Double getDropoffLatitude() {
        return dropoffLatitude;
    }

    public void setDropoffLatitude(Double dropoffLatitude) {
        this.dropoffLatitude = dropoffLatitude;
    }

}
