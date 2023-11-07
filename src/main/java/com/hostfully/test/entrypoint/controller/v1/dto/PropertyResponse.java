package com.hostfully.test.entrypoint.controller.v1.dto;


import java.util.UUID;

public class PropertyResponse {

    private UUID id;

    private String name;

    private String address;

    private String phone;

    private Long valuePerDay;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getValuePerDay() {
        return valuePerDay;
    }

    public void setValuePerDay(Long valuePerDay) {
        this.valuePerDay = valuePerDay;
    }
}

