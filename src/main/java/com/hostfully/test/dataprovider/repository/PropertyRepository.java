package com.hostfully.test.dataprovider.repository;

import com.hostfully.test.dataprovider.repository.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository  extends JpaRepository<PropertyEntity, UUID> {

}
