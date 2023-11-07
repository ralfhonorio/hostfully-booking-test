package com.hostfully.test.core.usecase.booking;

import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;

import java.util.UUID;

public interface CancelBookingUseCase {

    Booking cancel(UUID id) throws DataNotFoundException;

}
