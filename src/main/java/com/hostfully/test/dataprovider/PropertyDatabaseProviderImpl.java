package com.hostfully.test.dataprovider;

import com.hostfully.test.core.dataprovider.PropertyDatabaseProvider;
import com.hostfully.test.core.domain.Property;
import com.hostfully.test.dataprovider.repository.PropertyRepository;
import com.hostfully.test.dataprovider.repository.dto.PropertyEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PropertyDatabaseProviderImpl implements PropertyDatabaseProvider {

    private final PropertyRepository propertyRepository;

    private final PropertyEntityMapper propertyEntityMapper;

    public PropertyDatabaseProviderImpl(PropertyRepository propertyRepository, PropertyEntityMapper propertyEntityMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyEntityMapper = propertyEntityMapper;
    }

    @Override
    public Optional<Property> findById(UUID id) {
        return propertyRepository.findById(id).map(propertyEntityMapper::toProperty);
    }

}
