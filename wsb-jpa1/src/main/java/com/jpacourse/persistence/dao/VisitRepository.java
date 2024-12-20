package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends Dao<VisitEntity, Long> {
    List<VisitEntity> findAllByPatientId(Long patientId);
}