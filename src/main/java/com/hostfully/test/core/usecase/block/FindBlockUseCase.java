package com.hostfully.test.core.usecase.block;

import com.hostfully.test.core.domain.Block;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindBlockUseCase {

    List<Block> findAll();

    Optional<Block> findById(UUID id);
}
