package com.hostfully.test.core.usecase.block.impl;

import com.hostfully.test.core.dataprovider.BlockDatabaseProvider;
import com.hostfully.test.core.domain.Block;
import com.hostfully.test.core.usecase.block.UpdateBlockUseCase;

public class UpdateBlockUseCaseImpl implements UpdateBlockUseCase {

    private final BlockDatabaseProvider blockDatabaseProvider;

    public UpdateBlockUseCaseImpl(BlockDatabaseProvider blockDatabaseProvider) {
        this.blockDatabaseProvider = blockDatabaseProvider;
    }

    @Override
    public Block update(Block block) {
        return blockDatabaseProvider.update(block);
    }

}
