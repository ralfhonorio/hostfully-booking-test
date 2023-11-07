package com.hostfully.test.dataprovider;

import com.hostfully.test.core.dataprovider.BlockDatabaseProvider;
import com.hostfully.test.core.domain.Block;
import com.hostfully.test.dataprovider.repository.BlockRepository;
import com.hostfully.test.dataprovider.repository.dto.BlockEntityMapper;
import com.hostfully.test.dataprovider.repository.entity.BlockEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BlockDatabaseProviderImpl implements BlockDatabaseProvider {

    private final BlockRepository blockRepository;

    private final BlockEntityMapper blockEntityMapper;

    public BlockDatabaseProviderImpl(BlockRepository blockRepository, BlockEntityMapper blockEntityMapper) {
        this.blockRepository = blockRepository;
        this.blockEntityMapper = blockEntityMapper;
    }

    @Override
    @Transactional
    public Block save(Block block) {
        BlockEntity blockEntity = blockEntityMapper.toBlockEntity(block);
        BlockEntity blockEntityStored = blockRepository.save(blockEntity);
        return blockEntityMapper.toBlock(blockEntityStored);
    }


    @Override
    @Transactional
    public Block update(Block block) {
        BlockEntity blockEntity = blockEntityMapper.toBlockEntity(block);
        BlockEntity blockEntityStored = blockRepository.save(blockEntity);
        return blockEntityMapper.toBlock(blockEntityStored);
    }

    @Override
    public List<Block> findAll() {
        return blockRepository.findAll().stream().map(blockEntityMapper::toBlock).toList();
    }

    @Override
    public Optional<Block> findById(UUID id) {
        return blockRepository.findById(id).map(blockEntityMapper::toBlock);
    }

    @Transactional
    public void delete(UUID id) {
        blockRepository.deleteById(id);
    }

    @Override
    public Optional<Block> findConflictingBlock(UUID propertyId, LocalDate startDate, LocalDate endDate) {
        List<BlockEntity> blocks = blockRepository.findBlocksByStartDateAndEndDate(propertyId, startDate, endDate);
        if (blocks.isEmpty()) {
            return Optional.empty();
        } else {
            return blocks.stream().findAny().map(blockEntityMapper::toBlock);
        }
    }
}
