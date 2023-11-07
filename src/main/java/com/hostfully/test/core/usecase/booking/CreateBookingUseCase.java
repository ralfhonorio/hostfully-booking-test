package com.hostfully.test.core.usecase.booking;

import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;

public interface CreateBookingUseCase {

    Booking save(Booking booking) throws BookingNotAvailableException, DataNotFoundException;
}
