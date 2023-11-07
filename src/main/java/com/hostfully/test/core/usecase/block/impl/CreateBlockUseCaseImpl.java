package com.hostfully.test.core.usecase.block.impl;

import com.hostfully.test.core.dataprovider.BlockDatabaseProvider;
import com.hostfully.test.core.dataprovider.PropertyDatabaseProvider;
import com.hostfully.test.core.domain.Block;
import com.hostfully.test.core.domain.Property;
import com.hostfully.test.core.domain.exceptions.BookingNotAvailableException;
import com.hostfully.test.core.domain.exceptions.DataNotFoundException;
import com.hostfully.test.core.usecase.block.CreateBlockUseCase;
import com.hostfully.test.core.usecase.booking.ValidateBookingAvailabilityUseCase;

import java.util.Optional;

public class CreateBlockUseCaseImpl implements CreateBlockUseCase {

    private final BlockDatabaseProvider blockDatabaseProvider;
    private final PropertyDatabaseProvider propertyDatabaseProvider;
    private final ValidateBookingAvailabilityUseCase validateBookingAvailabilityUseCase;

    public CreateBlockUseCaseImpl(BlockDatabaseProvider blockDatabaseProvider, PropertyDatabaseProvider propertyDatabaseProvider, ValidateBookingAvailabilityUseCase validateBookingAvailabilityUseCase) {
        this.blockDatabaseProvider = blockDatabaseProvider;
        this.propertyDatabaseProvider = propertyDatabaseProvider;
        this.validateBookingAvailabilityUseCase = validateBookingAvailabilityUseCase;
    }

    @Override
    public Block save(Block block) throws DataNotFoundException, BookingNotAvailableException {

        validateBookingAvailabilityUseCase.busy(block.getProperty().getId(), block.getStartDate(), block.getEndDate());
        Optional<Property> storedProperty = propertyDatabaseProvider.findById(block.getProperty().getId());

        if (storedProperty.isEmpty()) {
            throw new DataNotFoundException("Property not found");
        }

        Block storedBlock =  blockDatabaseProvider.save(block);
        storedBlock.setProperty(storedProperty.get());
        return storedBlock;
    }
}
