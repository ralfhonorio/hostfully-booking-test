package com.hostfully.test.entrypoint.controller.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hostfully.test.core.domain.Status;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public class BlockResponse {

    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotNull
    private PropertyResponse property;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;


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

}
