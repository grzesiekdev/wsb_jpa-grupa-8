package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientRepository;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientRepository {
    public PatientDaoImpl() {
        super(PatientEntity.class);
    }

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        // Fetch the patient and doctor entities
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient == null) {
            throw new EntityNotFoundException(patientId);
        }

        if (doctor == null) {
            throw new EntityNotFoundException(doctorId);
        }

        // Create a new visit entity
        VisitEntity visit = new VisitEntity();
        visit.setVisitDate(visitDate);
        visit.setDoctor(doctor);
        visit.setPatient(patient);

        // Add the visit to the patient
        patient.getVisits().add(visit);

        // Merge the patient to persist the changes
        entityManager.merge(patient);
    }
}
