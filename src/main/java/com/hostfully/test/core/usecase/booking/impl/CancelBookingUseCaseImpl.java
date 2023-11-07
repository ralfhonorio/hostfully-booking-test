package com.hostfully.test.core.usecase.booking.impl;

import com.hostfully.test.core.dataprovider.BookingDatabaseProvider;
import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.domain.Status;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;
import com.hostfully.test.core.usecase.booking.CancelBookingUseCase;

import java.util.Optional;
import java.util.UUID;

public class CancelBookingUseCaseImpl implements CancelBookingUseCase {

    private final BookingDatabaseProvider bookingDatabaseProvider;


    public CancelBookingUseCaseImpl(BookingDatabaseProvider bookingDatabaseProvider) {
        this.bookingDatabaseProvider = bookingDatabaseProvider;
    }
    @Override
    public Booking cancel(UUID id) throws DataNotFoundException {
        Optional<Booking> storedBooking = bookingDatabaseProvider.findById(id);


        if (storedBooking.isPresent()) {
            storedBooking.get().setStatus(Status.CANCELED);
            return bookingDatabaseProvider.cancel(storedBooking.get());
        } else {
            throw new DataNotFoundException("Booking not found");
        }
    }

}
