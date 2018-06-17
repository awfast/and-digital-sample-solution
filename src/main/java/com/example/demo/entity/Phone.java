package com.example.demo.entity;

/**
 * Created by damyanrusinov on 17/06/2018.
 */
public class Phone {

    private boolean active;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Phone setNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Phone setActive(boolean active) {
        this.active = active;
        return this;
    }

}
