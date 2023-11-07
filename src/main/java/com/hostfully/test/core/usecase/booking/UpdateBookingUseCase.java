package com.hostfully.test.core.usecase.booking;

import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;

public interface UpdateBookingUseCase {

    Booking update(Booking booking) throws BookingNotAvailableException, DataNotFoundException;
}
