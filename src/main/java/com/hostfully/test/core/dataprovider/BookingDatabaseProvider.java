package com.hostfully.test.core.dataprovider;

import com.hostfully.test.core.domain.Booking;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingDatabaseProvider {

    Booking save(Booking booking);


    Booking update(Booking booking);


    List<Booking> findAll();


    Optional<Booking> findById(UUID id);


    Booking cancel(Booking booking);

    Optional<Booking> findBookingOverlapping(UUID propertyId, LocalDate startDate, LocalDate endDate);


}
