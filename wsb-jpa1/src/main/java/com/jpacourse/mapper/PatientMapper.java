package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.stream.Collectors;

public class PatientMapper {

    public static PatientTO toPatientTO(PatientEntity entity) {
        if (entity == null) return null;

        PatientTO patientTO = new PatientTO();
        patientTO.setId(entity.getId());
        patientTO.setFirstName(entity.getFirstName());
        patientTO.setLastName(entity.getLastName());
        patientTO.setDateOfBirth(entity.getDateOfBirth());
        patientTO.setShoeSize(entity.getShoeSize());
        patientTO.setPatientNumber(entity.getPatientNumber());
        patientTO.setTelephoneNumber(entity.getTelephoneNumber());
        patientTO.setAddress(AddressMapper.mapToTO(entity.getAddress())); // Map address
        patientTO.setVisits(entity.getVisits().stream()
                .map(VisitMapper::toVisitTO)
                .collect(Collectors.toList()));

        return patientTO;
    }

    public static PatientEntity toPatientEntity(PatientTO patientTO) {
        if (patientTO == null) return null;

        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setShoeSize(patientTO.getShoeSize());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setAddress(AddressMapper.mapToEntity(patientTO.getAddress())); // Map address

        // Map visits
        patientEntity.setVisits(patientTO.getVisits().stream()
                .map(visitTO -> VisitMapper.toVisitEntity(
                        visitTO,
                        DoctorMapper.toDoctorEntity(visitTO.getDoctor()), // Convert DoctorTO to DoctorEntity
                        patientEntity // Pass the current PatientEntity
                ))
                .collect(Collectors.toList()));

        System.out.println("Patient AddressEntity: " + patientEntity.getAddress());
        return patientEntity;
    }
}
