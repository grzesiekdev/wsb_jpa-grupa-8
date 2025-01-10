package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.MedicalTreatmentEntity;
import java.util.Optional;

public interface MedicalTreatmentDao extends Dao<MedicalTreatmentEntity, Long> {
    Optional<MedicalTreatmentEntity> findById(Long id);
}
