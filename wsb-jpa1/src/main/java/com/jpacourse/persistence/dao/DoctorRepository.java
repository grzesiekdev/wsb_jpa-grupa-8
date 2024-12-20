package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends Dao<DoctorEntity, Long> {
}
