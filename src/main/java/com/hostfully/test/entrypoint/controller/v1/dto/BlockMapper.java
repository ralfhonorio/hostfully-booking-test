package com.hostfully.test.entrypoint.controller.v1.dto;

import com.hostfully.test.core.domain.Block;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlockMapper {

    @Mapping(source="propertyId", target = "property.id")
    Block toBlock(BlockRequest request);

    @Mapping(source="property.id", target = "property.id")
    BlockResponse toBlockResponse(Block block);
}
