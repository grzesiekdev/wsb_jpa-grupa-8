package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitDao extends Dao<VisitEntity, Long> {
    List<VisitEntity> findAllByPatientId(Long patientId);
}
