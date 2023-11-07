package com.hostfully.test.core.usecase.booking.impl;

import com.hostfully.test.core.dataprovider.BookingDatabaseProvider;
import com.hostfully.test.core.dataprovider.PropertyDatabaseProvider;
import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.domain.Status;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;
import com.hostfully.test.core.usecase.booking.UpdateBookingUseCase;

public class UpdateBookingUseCaseImpl implements UpdateBookingUseCase {
    private final BookingDatabaseProvider bookingDatabaseProvider;

    private final PropertyDatabaseProvider propertyDatabaseProvider;


    public UpdateBookingUseCaseImpl(BookingDatabaseProvider bookingDatabaseProvider, PropertyDatabaseProvider propertyDatabaseProvider) {
        this.bookingDatabaseProvider = bookingDatabaseProvider;
        this.propertyDatabaseProvider = propertyDatabaseProvider;
    }

    @Override
    public Booking update(Booking booking) throws DataNotFoundException {
        Booking storedBooking = bookingDatabaseProvider.findById(booking.getId())
                .orElseThrow(() -> new DataNotFoundException("Booking not found"));

        propertyDatabaseProvider.findById(booking.getProperty().getId())
                .orElseThrow(() -> new DataNotFoundException("Property not found"));

        booking.setCreateAt(storedBooking.getCreateAt());
        booking.setStatus(Status.REBOOKED);

        return bookingDatabaseProvider.update(booking);
    }

}
