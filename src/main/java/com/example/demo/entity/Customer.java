package com.example.demo.entity;

import java.util.List;

/**
 * Created by damyanrusinov on 17/06/2018.
 */
public class Customer {

    private String id;
    private String name;
    private List<Phone> phoneDetails;

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public List<Phone> getPhoneDetails() {
        return phoneDetails;
    }

    public Customer setPhoneDetails(List<Phone> phoneDetails) {
        this.phoneDetails = phoneDetails;
        return this;
    }
}
