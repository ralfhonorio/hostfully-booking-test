package com.hostfully.test.core.dataprovider;

import com.hostfully.test.core.domain.Block;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlockDatabaseProvider {

    Block save(Block block);


    Block update(Block block);


    List<Block> findAll();


    Optional<Block> findById(UUID id);


    void delete(UUID id);

    Optional<Block> findConflictingBlock(UUID propertyId, LocalDate startDate, LocalDate endDate);


}
