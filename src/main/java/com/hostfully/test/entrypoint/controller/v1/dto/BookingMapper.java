package com.hostfully.test.entrypoint.controller.v1.dto;

import com.hostfully.test.core.domain.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(source="propertyId", target = "property.id")
    Booking toBooking(BookingRequest request);


    @Mapping(source="property.id", target = "property.id")
    BookingResponse toBookingResponse(Booking booking);

}

