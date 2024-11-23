package com.example.cf;

import java.io.Serializable;

public class MedicalDetails implements Serializable {
    private String description;
    private String hospital;
    private boolean hasInsurance;
    private String imageUrl;

    // Constructor that accepts all the required details
    public MedicalDetails(String description, String hospital, boolean hasInsurance, String imageUrl) {
        this.description = description;
        this.hospital = hospital;
        this.hasInsurance = hasInsurance;
        this.imageUrl = imageUrl;
    }

    // Getters and setters for all fields (optional, but useful if needed)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
