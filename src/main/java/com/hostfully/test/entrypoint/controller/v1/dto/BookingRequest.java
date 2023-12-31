package com.hostfully.test.entrypoint.controller.v1.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hostfully.test.entrypoint.controller.v1.validation.CustomDateDeserializer;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class BookingRequest {

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @FutureOrPresent
    private LocalDate startDate;

    @FutureOrPresent
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate endDate;

    @NotBlank
    private String guestName;

    @NotBlank
    private String guestEmail;

    @NotBlank
    private String guestPhone;

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

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }

}
