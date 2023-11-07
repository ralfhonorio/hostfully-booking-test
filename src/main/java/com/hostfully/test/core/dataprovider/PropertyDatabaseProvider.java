package com.hostfully.test.core.dataprovider;

import com.hostfully.test.core.domain.Property;

import java.util.Optional;
import java.util.UUID;

public interface PropertyDatabaseProvider {

    Optional<Property> findById(UUID id);

    void save(Property property);

}
