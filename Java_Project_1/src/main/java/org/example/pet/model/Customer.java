package org.example.pet.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String phone;
    private String ownerName;
    private String petName;
    private String address;
    private String species;
    private int birthYear;
    private List<MedicalRecord> medicalRecords;

    public Customer() {

    }

    public Customer(String phone, String ownerName, String petName, String address, String species, int birthYear) {
        this.phone = phone;
        this.ownerName = ownerName;
        this.petName = petName;
        this.address = address;
        this.species = species;
        this.birthYear = birthYear;
        this.medicalRecords = new ArrayList<>();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void addMedicalRecords(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
    }
}
