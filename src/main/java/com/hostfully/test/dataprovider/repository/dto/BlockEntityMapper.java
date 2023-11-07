package com.hostfully.test.dataprovider.repository.dto;

import com.hostfully.test.core.domain.Block;
import com.hostfully.test.dataprovider.repository.entity.BlockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring")
public interface BlockEntityMapper {

    @Mapping(source ="property.id", target ="property.id")
    Block toBlock(BlockEntity blockEntity);
    @Mapping(source ="property.id", target ="property.id")
    BlockEntity toBlockEntity(Block block);

}


