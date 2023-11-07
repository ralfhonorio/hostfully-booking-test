package com.hostfully.test.core.usecase.booking;

import com.hostfully.test.core.domain.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindBookingUseCase {

    List<Booking> findAll();

    Optional<Booking> findById(UUID id);
}
