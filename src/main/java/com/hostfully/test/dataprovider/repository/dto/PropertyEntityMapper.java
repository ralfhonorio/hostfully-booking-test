package com.hostfully.test.dataprovider.repository.dto;

import com.hostfully.test.core.domain.Property;
import com.hostfully.test.dataprovider.repository.entity.PropertyEntity;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface PropertyEntityMapper {

    Property toProperty(PropertyEntity propertyEntity);

    PropertyEntity toPropertyEntity(Property property);

}


