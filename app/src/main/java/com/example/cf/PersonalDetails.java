package com.example.cf;

import java.io.Serializable;

public class PersonalDetails implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String state;
    private String pincode;

    // Constructor that accepts all the required details
    public PersonalDetails(String name, String email, String phone, String address, String state, String pincode) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.pincode = pincode;
    }

    // Getters and setters for all fields (optional, but useful if needed)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
