package com.hostfully.test.entrypoint.controller.v1;

import com.hostfully.test.core.domain.Booking;
import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;
import com.hostfully.test.core.usecase.booking.CreateBookingUseCase;
import com.hostfully.test.core.usecase.booking.CancelBookingUseCase;
import com.hostfully.test.core.usecase.booking.FindBookingUseCase;
import com.hostfully.test.core.usecase.booking.UpdateBookingUseCase;
import com.hostfully.test.entrypoint.controller.v1.dto.BookingRequest;
import com.hostfully.test.entrypoint.controller.v1.dto.BookingMapper;
import com.hostfully.test.entrypoint.controller.v1.dto.BookingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/bookings")
public class BookingController {

    private final CreateBookingUseCase createBookingUseCase;
    private final UpdateBookingUseCase updateBookingUseCase;
    private final CancelBookingUseCase cancelBookingUseCase;
    private final FindBookingUseCase findBookingUseCase;

    private final BookingMapper bookingMapper;

    public BookingController(CreateBookingUseCase createBookingUseCase, UpdateBookingUseCase updateBookingUseCase, CancelBookingUseCase cancelBookingUseCase, FindBookingUseCase findBookingUseCase, BookingMapper bookingMapper) {
        this.createBookingUseCase = createBookingUseCase;
        this.updateBookingUseCase = updateBookingUseCase;
        this.cancelBookingUseCase = cancelBookingUseCase;
        this.findBookingUseCase = findBookingUseCase;
        this.bookingMapper = bookingMapper;
    }

    @Operation(summary = "Create a new booking")
    @ApiResponse(responseCode = "201", description = "Booking created successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "409", description = "Booked between startDate to endDate")
    @ApiResponse(responseCode = "409", description = "Blocked between startDate to endDate")
    @ApiResponse(responseCode = "404", description = "Property unavailable!")
    @PostMapping
    public ResponseEntity<BookingResponse> create(@RequestBody @Valid BookingRequest request) throws BookingNotAvailableException, DataNotFoundException {

        Booking booking = bookingMapper.toBooking(request);
        Booking createdBooking = createBookingUseCase.save(booking);
        BookingResponse response = bookingMapper.toBookingResponse(createdBooking);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Operation(summary = "Update range of dates and guest data an existing booking ")
    @ApiResponse(responseCode = "200", description = "Booking updated successfully", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Property unavailable!")
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> update(@PathVariable UUID id, @Valid @RequestBody BookingRequest request) throws BookingNotAvailableException, DataNotFoundException {

        Booking booking = bookingMapper.toBooking(request);
        booking.setId(id);


        Booking updatedBooking = updateBookingUseCase.update(booking);
        BookingResponse response = bookingMapper.toBookingResponse(updatedBooking);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Canceled a booking by ID")
    @ApiResponse(responseCode = "204", description = "Booking deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<BookingResponse> cancel(@PathVariable UUID id) throws DataNotFoundException {
        Booking cancelBooking = cancelBookingUseCase.cancel(id);
        BookingResponse response = bookingMapper.toBookingResponse(cancelBooking);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @Operation(summary = "Get all bookings")
    @ApiResponse(responseCode = "200", description = "List of all bookings", content = @Content(mediaType = "application/json"))
    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        List<Booking> bookings = findBookingUseCase.findAll();

        List<BookingResponse> responses = bookings.stream()
                .map(bookingMapper::toBookingResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Find a booking by ID")
    @ApiResponse(responseCode = "200", description = "Booking found", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Booking not found")
    @GetMapping("{id}")
    public ResponseEntity<BookingResponse> findById(@PathVariable UUID id) throws DataNotFoundException {
        Optional<Booking> optionalBooking = findBookingUseCase.findById(id);
        if (optionalBooking.isEmpty()) {
            throw new DataNotFoundException("Booking not found");
        }
        BookingResponse bookingResponse = bookingMapper.toBookingResponse(optionalBooking.get());
        return ResponseEntity.ok(bookingResponse);
    }

}

