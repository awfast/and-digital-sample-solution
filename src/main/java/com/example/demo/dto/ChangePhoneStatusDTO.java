package com.example.demo.dto;

/**
 * Created by damyanrusinov on 17/06/2018.
 */
public class ChangePhoneStatusDTO {

    private String name;
    private String customerId;
    private String phoneNumber;
    private boolean status;

    public String getName() {
        return name;
    }

    public ChangePhoneStatusDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public ChangePhoneStatusDTO setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ChangePhoneStatusDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean getStatus() {
        return status;
    }

    public ChangePhoneStatusDTO setStatus(boolean status) {
        this.status = status;
        return this;
    }
}
