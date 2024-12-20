package com.jpacourse.dto;

import java.time.LocalDateTime;
import java.util.List;

public class VisitTO {

    private Long id; // Corresponds to VisitEntity.id
    private String description; // Corresponds to VisitEntity.description
    private LocalDateTime visitDate; // Corresponds to VisitEntity.visitDate
    private String doctorFirstName;
    private String doctorLastName;

    private DoctorTO doctor; // Full reference to DoctorTO
    private Long patientId; // ID reference for the patient

    private List<String> treatmentTypes; // Names or descriptions of treatments

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public DoctorTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorTO doctor) {
        this.doctor = doctor;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public List<String> getTreatmentTypes() {
        return treatmentTypes;
    }

    public void setTreatmentTypes(List<String> treatmentTypes) {
        this.treatmentTypes = treatmentTypes;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }
}
