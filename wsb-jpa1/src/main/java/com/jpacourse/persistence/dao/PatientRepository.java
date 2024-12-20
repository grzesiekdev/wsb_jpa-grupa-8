package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PatientRepository extends Dao<PatientEntity, Long> {
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description);
}
