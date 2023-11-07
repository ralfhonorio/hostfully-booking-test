package com.hostfully.test.config;

import com.hostfully.test.core.dataprovider.BlockDatabaseProvider;
import com.hostfully.test.core.dataprovider.PropertyDatabaseProvider;
import com.hostfully.test.core.usecase.block.CreateBlockUseCase;
import com.hostfully.test.core.usecase.block.DeleteBlockUseCase;
import com.hostfully.test.core.usecase.block.FindBlockUseCase;
import com.hostfully.test.core.usecase.block.UpdateBlockUseCase;
import com.hostfully.test.core.usecase.block.impl.CreateBlockUseCaseImpl;
import com.hostfully.test.core.usecase.block.impl.DeleteBlockUseCaseImpl;
import com.hostfully.test.core.usecase.block.impl.FindBlockUseCaseImpl;
import com.hostfully.test.core.usecase.block.impl.UpdateBlockUseCaseImpl;
import com.hostfully.test.core.usecase.booking.ValidateBookingAvailabilityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockBeanConfig {

    @Bean
    public CreateBlockUseCase createBlockUseCase(BlockDatabaseProvider blockDatabaseProvider, PropertyDatabaseProvider propertyDatabaseProvider, ValidateBookingAvailabilityUseCase validateBookingAvailabilityUseCase) {
        return new CreateBlockUseCaseImpl(blockDatabaseProvider, propertyDatabaseProvider, validateBookingAvailabilityUseCase);
    }

    @Bean
    public UpdateBlockUseCase updateBlockUseCase(BlockDatabaseProvider blockDatabaseProvider) {
        return new UpdateBlockUseCaseImpl(blockDatabaseProvider) ;
    }


    @Bean
    public DeleteBlockUseCase deleteBlockUseCase(BlockDatabaseProvider blockDatabaseProvider) {
        return new DeleteBlockUseCaseImpl(blockDatabaseProvider) ;
    }

    @Bean
    public FindBlockUseCase findBlockUseCase(BlockDatabaseProvider blockDatabaseProvider) {
        return new FindBlockUseCaseImpl(blockDatabaseProvider);
    }

}