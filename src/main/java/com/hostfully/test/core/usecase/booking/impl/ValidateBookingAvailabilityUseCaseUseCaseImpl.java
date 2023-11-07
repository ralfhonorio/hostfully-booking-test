package com.hostfully.test.core.usecase.booking.impl;

import com.hostfully.test.core.dataprovider.BlockDatabaseProvider;
import com.hostfully.test.core.dataprovider.BookingDatabaseProvider;
import com.hostfully.test.core.domain.Block;
import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;
import com.hostfully.test.core.usecase.booking.ValidateBookingAvailabilityUseCase;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class ValidateBookingAvailabilityUseCaseUseCaseImpl implements ValidateBookingAvailabilityUseCase {

    private final BookingDatabaseProvider bookingDatabaseProvider;

    private final BlockDatabaseProvider blockDatabaseProvider;


    public ValidateBookingAvailabilityUseCaseUseCaseImpl(BookingDatabaseProvider bookingDatabaseProvider, BlockDatabaseProvider blockDatabaseProvider) {
        this.bookingDatabaseProvider = bookingDatabaseProvider;
        this.blockDatabaseProvider = blockDatabaseProvider;
    }

    private boolean isUnavailableDate(LocalDate startDate, LocalDate endDate, LocalDate unavailableStartDate, LocalDate unavailableEndDate) {
        return !startDate.isAfter(unavailableEndDate) && !unavailableStartDate.isAfter(endDate);
    }

    @Override
    public void blocked(UUID propertyId, LocalDate startDate, LocalDate endDate) throws BookingNotAvailableException {
        Optional<Block> blocked = blockDatabaseProvider.findConflictingBlock(propertyId, startDate, endDate);
        if (blocked.isPresent()) {
            if (isUnavailableDate(startDate, endDate, blocked.get().getStartDate(), blocked.get().getEndDate())) {
                throw new BookingNotAvailableException(
                        String.format("Blocked between %s to %s",
                                blocked.get().getStartDate(),
                                blocked.get().getEndDate())
                );
            }
        }
    }

    @Override
    public void busy(UUID propertyId, LocalDate startDate, LocalDate endDate) throws BookingNotAvailableException {
        Optional<Booking> booked = bookingDatabaseProvider.findBookingOverlapping(propertyId, startDate, endDate);
        if (booked.isPresent()) {
            if (isUnavailableDate(startDate, endDate, booked.get().getStartDate(), booked.get().getEndDate())) {
                throw new BookingNotAvailableException(
                        String.format("Booked between %s to %s",
                                booked.get().getStartDate(),
                                booked.get().getEndDate())
                );
            }
        }
    }
}
