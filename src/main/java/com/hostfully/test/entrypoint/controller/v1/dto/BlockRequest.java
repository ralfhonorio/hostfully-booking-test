package com.hostfully.test.entrypoint.controller.v1.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hostfully.test.entrypoint.controller.v1.validation.CustomDateDeserializer;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class BlockRequest {

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @FutureOrPresent
    private LocalDate startDate;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @FutureOrPresent
    private LocalDate endDate;

    @NotNull
    private UUID propertyId;

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }



}
