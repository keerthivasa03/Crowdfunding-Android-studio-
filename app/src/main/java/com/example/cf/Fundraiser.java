package com.example.cf;

public class Fundraiser {
    private PersonalDetails personalDetails;
    private AmountDetails amountDetails;
    private MedicalDetails medicalDetails;

    // Default constructor required for calls to DataSnapshot.getValue(Fundraiser.class)
    public Fundraiser() {}

    // Add getters and setters here

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public AmountDetails getAmountDetails() {
        return amountDetails;
    }

    public void setAmountDetails(AmountDetails amountDetails) {
        this.amountDetails = amountDetails;
    }

    public MedicalDetails getMedicalDetails() {
        return medicalDetails;
    }

    public void setMedicalDetails(MedicalDetails medicalDetails) {
        this.medicalDetails = medicalDetails;
    }
}

// Define PersonalDetails, AmountDetails, and MedicalDetails classes accordingly
