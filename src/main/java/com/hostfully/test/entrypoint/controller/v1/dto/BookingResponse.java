package com.hostfully.test.entrypoint.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hostfully.test.core.domain.Status;

import java.util.Date;
import java.util.UUID;

public class BookingResponse {
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String guestName;

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

    private String guestEmail;

    private String guestPhone;

    private Status status;

    private PropertyResponse property;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PropertyResponse getProperty() {
        return property;
    }

    public void setProperty(PropertyResponse property) {
        this.property = property;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}
