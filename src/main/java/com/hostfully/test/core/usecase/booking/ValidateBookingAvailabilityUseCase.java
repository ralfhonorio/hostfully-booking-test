package com.hostfully.test.core.usecase.booking;

import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;

import java.time.LocalDate;
import java.util.UUID;

public interface ValidateBookingAvailabilityUseCase {

    void blocked(UUID propertyId, LocalDate startDate, LocalDate endDate) throws BookingNotAvailableException;

    void busy(UUID propertyId, LocalDate startDate, LocalDate endDate) throws BookingNotAvailableException;

}
