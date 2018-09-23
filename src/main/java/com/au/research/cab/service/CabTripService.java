package com.au.research.cab.service;

import com.au.research.cab.exception.ApplicationException;
import com.au.research.cab.model.CabTripCount;
import com.au.research.cab.repository.CabTripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class CabTripService implements CabTripServiceIF {
    private static final Logger LOG = LoggerFactory.getLogger(CabTripService.class);

    @Autowired
    private CabTripRepository cabTripRepository;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public CabTripCount getCabDayTripCount(String medallion, LocalDate pickupDate) {
        try {
            LOG.debug("Get trip count for Cab: {}, for pickup date: {}", medallion, pickupDate);
            assert medallion != null;
            assert pickupDate != null;

            LocalDateTime fromDateTime = LocalDateTime.of(pickupDate, LocalTime.MIDNIGHT);
            LocalDateTime toDateTime = fromDateTime.plusDays(1L);
            Long tripCount =
                    cabTripRepository.countByMedallionAndPickupDateTimeGreaterThanEqualAndPickupDateTimeBefore(medallion, fromDateTime, toDateTime);
            CabTripCount cabTripCount = new CabTripCount(medallion, dateTimeFormatter.format(pickupDate), tripCount);

            LOG.info("Get trip count: {} for Cab: {}, for pickup date: {}", cabTripCount.getTripCount(), medallion, pickupDate);
            return cabTripCount;
        } catch (RuntimeException ex) {
            throw new ApplicationException(ex);
        }
    }
}
