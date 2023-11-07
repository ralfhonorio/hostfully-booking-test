package com.hostfully.test.config;

import com.hostfully.test.core.dataprovider.BlockDatabaseProvider;
import com.hostfully.test.core.dataprovider.BookingDatabaseProvider;
import com.hostfully.test.core.dataprovider.PropertyDatabaseProvider;
import com.hostfully.test.core.usecase.booking.*;
import com.hostfully.test.core.usecase.booking.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingBeanConfig {

    @Bean
    public CreateBookingUseCase createBookingUseCase(BookingDatabaseProvider bookingDatabaseProvider, PropertyDatabaseProvider propertyDatabaseProvider, ValidateBookingAvailabilityUseCase validateBookingAvailabilityUseCase) {
        return new CreateBookingUseCaseImpl(bookingDatabaseProvider, propertyDatabaseProvider, validateBookingAvailabilityUseCase);
    }

    @Bean
    public UpdateBookingUseCase updateBookingUseCase(BookingDatabaseProvider bookingDatabaseProvider,  PropertyDatabaseProvider propertyDatabaseProvider) {
        return new UpdateBookingUseCaseImpl(bookingDatabaseProvider, propertyDatabaseProvider);
    }


    @Bean
    public CancelBookingUseCase deleteBookingUseCase(BookingDatabaseProvider bookingDatabaseProvider) {
        return new CancelBookingUseCaseImpl(bookingDatabaseProvider);
    }

    @Bean
    public FindBookingUseCase findBookingUseCase(BookingDatabaseProvider bookingDatabaseProvider) {
        return new FindBookingUseCaseImpl(bookingDatabaseProvider);
    }

    @Bean
    public ValidateBookingAvailabilityUseCase validateBookingAvailabilityUseCase(BookingDatabaseProvider bookingDatabaseProvider, BlockDatabaseProvider blockDatabaseProvider) {
        return new ValidateBookingAvailabilityUseCaseUseCaseImpl(bookingDatabaseProvider, blockDatabaseProvider);
    }

}
