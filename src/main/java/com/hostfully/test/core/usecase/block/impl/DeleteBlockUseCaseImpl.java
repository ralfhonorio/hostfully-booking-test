package com.hostfully.test.core.usecase.block.impl;

import com.hostfully.test.core.dataprovider.BlockDatabaseProvider;
import com.hostfully.test.core.usecase.block.DeleteBlockUseCase;

import java.util.UUID;

public class DeleteBlockUseCaseImpl implements DeleteBlockUseCase {

    private final BlockDatabaseProvider blockDatabaseProvider;

    public DeleteBlockUseCaseImpl(BlockDatabaseProvider blockDatabaseProvider) {
        this.blockDatabaseProvider = blockDatabaseProvider;
    }

    @Override
    public void delete(UUID id) {
        blockDatabaseProvider.delete(id);

    }
}
