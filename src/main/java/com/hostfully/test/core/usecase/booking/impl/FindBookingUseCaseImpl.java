package com.hostfully.test.core.usecase.booking.impl;

import com.hostfully.test.core.dataprovider.BookingDatabaseProvider;
import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.usecase.booking.FindBookingUseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindBookingUseCaseImpl implements FindBookingUseCase {

    private final BookingDatabaseProvider bookingDatabaseProvider;

    public FindBookingUseCaseImpl(BookingDatabaseProvider bookingDatabaseProvider) {
        this.bookingDatabaseProvider = bookingDatabaseProvider;
    }
    @Override
    public List<Booking> findAll() {
        return bookingDatabaseProvider.findAll();

    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return bookingDatabaseProvider.findById(id);
    }
}
