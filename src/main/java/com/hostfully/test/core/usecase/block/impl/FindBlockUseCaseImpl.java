package com.hostfully.test.core.usecase.block.impl;

import com.hostfully.test.core.dataprovider.BlockDatabaseProvider;
import com.hostfully.test.core.domain.Block;
import com.hostfully.test.core.usecase.block.FindBlockUseCase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindBlockUseCaseImpl implements FindBlockUseCase {

    private final BlockDatabaseProvider blockDatabaseProvider;

    public FindBlockUseCaseImpl(BlockDatabaseProvider blockDatabaseProvider) {
        this.blockDatabaseProvider = blockDatabaseProvider;
    }

    @Override
    public List<Block> findAll() {
      return blockDatabaseProvider.findAll();
    }

    @Override
    public Optional<Block> findById(UUID id) {
        return blockDatabaseProvider.findById(id);
    }
}