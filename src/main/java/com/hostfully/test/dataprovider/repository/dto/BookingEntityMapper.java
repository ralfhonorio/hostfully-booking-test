package com.hostfully.test.dataprovider.repository.dto;

import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.dataprovider.repository.entity.BookingEntity;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface BookingEntityMapper {

    Booking toBooking(BookingEntity bookingEntity);

    BookingEntity toBookingEntity(Booking booking);
}
