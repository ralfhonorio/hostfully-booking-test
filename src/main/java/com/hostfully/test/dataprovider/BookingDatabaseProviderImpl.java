package com.hostfully.test.dataprovider;

import com.hostfully.test.core.dataprovider.BookingDatabaseProvider;
import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.dataprovider.repository.BookingRepository;
import com.hostfully.test.dataprovider.repository.dto.BookingEntityMapper;
import com.hostfully.test.dataprovider.repository.entity.BookingEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookingDatabaseProviderImpl implements BookingDatabaseProvider {

    private final BookingRepository bookingRepository;
    private final BookingEntityMapper bookingEntityMapper;

    public BookingDatabaseProviderImpl(BookingRepository bookingRepository, BookingEntityMapper bookingEntityMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingEntityMapper = bookingEntityMapper;

    }

    @Override
    @Transactional
    public Booking save(Booking booking) {
        BookingEntity bookingEntity = bookingEntityMapper.toBookingEntity(booking);
        BookingEntity bookingEntityStored = bookingRepository.save(bookingEntity);
        return bookingEntityMapper.toBooking(bookingEntityStored);
    }


    @Override
    @Transactional
    public Booking update(Booking booking) {
        BookingEntity bookingEntity = bookingEntityMapper.toBookingEntity(booking);
        BookingEntity bookingEntityStored = bookingRepository.save(bookingEntity);
        return bookingEntityMapper.toBooking(bookingEntityStored);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll().stream().map(bookingEntityMapper::toBooking).toList();
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return bookingRepository.findById(id).map(bookingEntityMapper::toBooking);
    }

    @Transactional
    public Booking cancel(Booking booking) {
        BookingEntity bookingEntity = bookingEntityMapper.toBookingEntity(booking);
        BookingEntity bookingEntityStored = bookingRepository.save(bookingEntity);
        return bookingEntityMapper.toBooking(bookingEntityStored);
    }

    @Override
    public Optional<Booking> findBookingOverlapping(UUID propertyId, LocalDate startDate, LocalDate endDate) {

        List<BookingEntity> bookings = bookingRepository.findBookingsByStartDateAndEndDate(propertyId, startDate, endDate);
        if (bookings.isEmpty()) {
            return Optional.empty();
        } else {
            return bookings.stream().findAny().map(bookingEntityMapper::toBooking);
        }
    }
}
