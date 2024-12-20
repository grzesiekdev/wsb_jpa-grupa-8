package com.jpacourse.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientTO {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Integer shoeSize;

    private String patientNumber;

    private String telephoneNumber;

    private List<VisitTO> visits = new ArrayList<>();

    private AddressTO address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(Integer shoeSize) {
        this.shoeSize = shoeSize;
    }

    public List<VisitTO> getVisits() {
        return visits;
    }

    public void setVisits(List<VisitTO> visits) {
        this.visits = visits;
    }

    public AddressTO getAddress() {
        return address;
    }

    public void setAddress(AddressTO address) {
        this.address = address;
    }
}
