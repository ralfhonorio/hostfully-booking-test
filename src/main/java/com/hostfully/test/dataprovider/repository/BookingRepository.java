package com.hostfully.test.dataprovider.repository;

import com.hostfully.test.dataprovider.repository.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    @Query("SELECT e FROM BookingEntity e WHERE e.property.id = :propertyId AND e.startDate <= :endDate AND e.endDate >= :startDate AND e.status != 'CANCELED'")
    List<BookingEntity> findBookingsByStartDateAndEndDate(@Param("propertyId") UUID propertyId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
