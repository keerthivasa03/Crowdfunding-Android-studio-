package com.example.cf;


public class User {
    private PersonalDetails personalDetails;
    private AmountDetails amountDetails;
    private MedicalDetails medicalDetails;

    public User(PersonalDetails personalDetails, AmountDetails amountDetails, MedicalDetails medicalDetails) {
        this.personalDetails = personalDetails;
        this.amountDetails = amountDetails;
        this.medicalDetails = medicalDetails;
    }

    // Getters and Setters
    public PersonalDetails getPersonalDetails() { return personalDetails; }
    public void setPersonalDetails(PersonalDetails personalDetails) { this.personalDetails = personalDetails; }

    public AmountDetails getAmountDetails() { return amountDetails; }
    public void setAmountDetails(AmountDetails amountDetails) { this.amountDetails = amountDetails; }

    public MedicalDetails getMedicalDetails() { return medicalDetails; }
    public void setMedicalDetails(MedicalDetails medicalDetails) { this.medicalDetails = medicalDetails; }
}
