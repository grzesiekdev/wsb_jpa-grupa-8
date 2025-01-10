package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    List<PatientEntity> findByLastName(String lastName);
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description);
    List<PatientEntity> findPatientsWithMoreThanXVisits(long visitCount);
    List<PatientEntity> findPatientsWithShoeSizeLowerThan(int shoeSize);
}
