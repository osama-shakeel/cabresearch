package com.au.research.cab.web.controller;

import com.au.research.cab.model.CabTripCount;
import com.au.research.cab.service.CabTripServiceIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cabTrips")
public class CabTripController {
    private static final Logger LOG = LoggerFactory.getLogger(CabTripController.class);

    @Autowired
    private CabTripServiceIF cabTripService;

    /**
     * Get Cab trip count for the given cab identified by its medallion for a
     * pickup date
     *
     * @param medallion
     * @param pickupDate
     * @return CabTripCount instance.
     */
    // /api/cabTrips/ASHJH65675/count?pickupDate=20180113
    @GetMapping(value = "/{medallion}/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CabTripCount getCabTripCount(
            @PathVariable("medallion") String medallion,
            @RequestParam("pickupDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate pickupDate) {
        LOG.info("Processing Cab Trip Count Request for Cab: {}, for pickup date: {} - ENTRY", medallion, pickupDate);

        CabTripCount cabTripCount = cabTripService.getCabDayTripCount(medallion, pickupDate);

        LOG.info("Processed Cab Trip Count Request for Cab: {}, for pickup date: {} with tripCount: {} - EXIT",
                medallion, pickupDate, cabTripCount.getTripCount());
        return cabTripCount;
    }

    /**
     * Get Cab trip count for the given cab identified by its medallion for a
     * pickup date
     *
     * @param medallion
     * @param pickupDate
     * @return CabTripCount instance.
     */
    // /api/cabTrips/ASHJH65675/count?pickupDate=20180113
    @GetMapping(value = "/{medallion}/count/latest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CabTripCount getLatestCabTripCount(
            @PathVariable("medallion") String medallion,
            @RequestParam("pickupDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate pickupDate) {
        LOG.info("Processing Cab Trip Latest Count Request for Cab: {}, for pickup date: {} - ENTRY", medallion, pickupDate);

        CabTripCount cabTripCount = cabTripService.getLatestCabDayTripCount(medallion, pickupDate);

        LOG.info("Processed Cab Trip Count Request for Cab: {}, for pickup date: {} with tripCount: {} - EXIT",
                medallion, pickupDate, cabTripCount.getTripCount());
        return cabTripCount;
    }

    /**
     * Get cab trip counts for the list of cabs identified by the medallions list
     * for the given pickup date.
     *
     * @param medallions
     * @param pickupDate
     * @return List of CabTripCounts
     */
    // /api/cabTrips/count?medallion=ASHJH65675,BSXH6577&pickupDate=20130113
    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CabTripCount> getCabTripsCounts(
            @RequestParam("medallion") List<String> medallions,
            @RequestParam("pickupDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate pickupDate) {
        LOG.info("Processing Cab Trips Counts Request for Cabs: {}, for pickup date: {} - ENTRY", medallions, pickupDate);

        List<CabTripCount> cabTripCounts = new ArrayList<>();

        if (medallions != null) {
            for (String medallion : medallions) {
                CabTripCount cabTripCount = cabTripService.getCabDayTripCount(medallion, pickupDate);
                cabTripCounts.add(cabTripCount);
            }
        }
        LOG.info("Processed Cab Trips Counts Request: {} - EXIT", cabTripCounts, false);
        return cabTripCounts;
    }

    /**
     * Get cab trip counts for the list of cabs identified by the medallions list
     * for the given pickup date.
     *
     * @param medallions
     * @param pickupDate
     * @return List of CabTripCounts
     */
    // /api/cabTrips/count?medallion=ASHJH65675,BSXH6577&pickupDate=20130113
    @GetMapping(value = "/count/latest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CabTripCount> getLatestCabTripsCounts(
            @RequestParam("medallion") List<String> medallions,
            @RequestParam("pickupDate") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate pickupDate) {
        LOG.info("Processing Cab Trips Counts Request for Cabs: {}, for pickup date: {} - ENTRY", medallions, pickupDate);

        List<CabTripCount> cabTripCounts = new ArrayList<>();

        if (medallions != null) {
            for (String medallion : medallions) {
                CabTripCount cabTripCount = cabTripService.getLatestCabDayTripCount(medallion, pickupDate);
                cabTripCounts.add(cabTripCount);
            }
        }
        LOG.info("Processed Cab Trips Counts Request: {} - EXIT", cabTripCounts, false);
        return cabTripCounts;
    }

    @DeleteMapping("/cache/contents")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCabTripCountCacheContents() {
        LOG.info("Deleting Cab Trips Counts Cache - ENTRY");

        cabTripService.clearAllCacheContents();

        LOG.info("Deleted Cab Trips Counts Cache - EXIT");
    }
}
