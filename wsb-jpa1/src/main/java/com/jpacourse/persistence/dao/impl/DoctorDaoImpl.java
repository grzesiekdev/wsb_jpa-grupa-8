package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorRepository;
import com.jpacourse.persistence.dao.PatientRepository;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorRepository {
    public DoctorDaoImpl() {
        super(DoctorEntity.class);
    }
}
