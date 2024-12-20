package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientRepository;
import com.jpacourse.persistence.dao.VisitRepository;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VisitDaoImpl extends AbstractDao<VisitEntity, Long> implements VisitRepository {
    public VisitDaoImpl()  {
        super(VisitEntity.class);
    }

    @Override
    public List<VisitEntity> findAllByPatientId(Long patientId) {
        TypedQuery<VisitEntity> query = entityManager.createQuery(
                "SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId", VisitEntity.class);
        query.setParameter("patientId", patientId);
        return query.getResultList();
    }
}
