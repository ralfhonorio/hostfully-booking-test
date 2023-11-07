package com.hostfully.test.core.usecase.booking.impl;

import com.hostfully.test.core.dataprovider.BookingDatabaseProvider;
import com.hostfully.test.core.dataprovider.PropertyDatabaseProvider;
import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.domain.Property;
import com.hostfully.test.core.domain.Status;
import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;
import com.hostfully.test.core.usecase.booking.CreateBookingUseCase;
import com.hostfully.test.core.usecase.booking.ValidateBookingAvailabilityUseCase;

import java.util.Optional;

public class CreateBookingUseCaseImpl implements CreateBookingUseCase {
    private final BookingDatabaseProvider bookingDatabaseProvider;

    private final PropertyDatabaseProvider propertyDatabaseProvider;

    private final ValidateBookingAvailabilityUseCase validateBookingAvailabilityUseCase;

    public CreateBookingUseCaseImpl(BookingDatabaseProvider bookingDatabaseProvider, PropertyDatabaseProvider propertyDatabaseProvider, ValidateBookingAvailabilityUseCase validateBookingAvailabilityUseCase) {
        this.bookingDatabaseProvider = bookingDatabaseProvider;
        this.propertyDatabaseProvider = propertyDatabaseProvider;
        this.validateBookingAvailabilityUseCase = validateBookingAvailabilityUseCase;
    }


    @Override
    public Booking save(Booking booking) throws BookingNotAvailableException, DataNotFoundException {

        Optional<Property> storedProperty = propertyDatabaseProvider.findById(booking.getProperty().getId());

        if (storedProperty.isEmpty()) {
            throw new DataNotFoundException("Property not found");
        }

        validateBookingAvailabilityUseCase.blocked(booking.getProperty().getId(), booking.getStartDate(), booking.getEndDate());
        validateBookingAvailabilityUseCase.busy(booking.getProperty().getId(), booking.getStartDate(), booking.getEndDate());
        booking.setStatus(Status.BOOKED);
        Booking storedBooking = bookingDatabaseProvider.save(booking);
        storedBooking.setProperty(storedProperty.get());
        return storedBooking;


    }
}
